package com.example.loudalarm.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loudalarm.AlarmIntentsController.AlarmController;
import com.example.loudalarm.App;
import com.example.loudalarm.AuthController.AuthController;
import com.example.loudalarm.Fragments.AddFragment;
import com.example.loudalarm.R;
import com.example.loudalarm.Room.AlarmDAO;
import com.example.loudalarm.Room.AlarmEntity;
import com.example.loudalarm.TouchHelper.ItemTouchHelperAdapter;
import com.example.loudalarm.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> implements ItemTouchHelperAdapter {
    // todo list будильников
    public List<AlarmEntity> list;

    // todo List удаленных будильников
    public List<AlarmEntity> listOfDeleted = new ArrayList<>();

    public final Activity activity;
    AuthController authController = new AuthController();
    private final FragmentManager fragmentManager;
    private final AlarmDAO alarmDAO;
    public  AlarmViewHolder holderPB;

    // todo fragmentHomeBinding
    private final FragmentHomeBinding binding;

    public AlarmAdapter(FragmentManager fragmentManager, List<AlarmEntity> news, Activity activity, AlarmDAO alarmDAO, FragmentHomeBinding binding) {
        this.fragmentManager = fragmentManager;
        this.list = news;
        this.activity = activity;
        this.alarmDAO= alarmDAO;
        this.binding = binding;
    }

    @NonNull
    @Override
    public AlarmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        view.setLongClickable(true); // todo правим баг
        return new AlarmViewHolder(view);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(AlarmViewHolder holder, int position) {

        // todo правим баг
        holder.itemView.setLongClickable(true);
        holderPB = holder;
        // todo сортируем в порядке времени
        list.sort(Comparator.comparingLong(o -> o.time));

        AlarmEntity alarm = list.get(position);
        AlarmController controller = new AlarmController(alarm);

        holder.time.setText(alarm.time_on_clock_in_hours_and_minutes);
        holder.on_off.setChecked(alarm.on);
        holder.message.setText(alarm.textMessage);
        holder.days.setText(alarm.days);

        if (holder.on_off.isChecked()) setUp(holder);
        else setDown(holder);

        if (listOfDeleted.contains(alarm)) ifChecked(holder);
        else ifNotChecked(holder);

        holder.itemView.setOnLongClickListener(longer -> {
            ifChecked(holder);
            listOfDeleted.add(list.get(position));
            return true;
        });

        holder.on_off.setOnClickListener(b -> {
            if (holder.on_off.isChecked()) {
                list.get(position).setOn(true);
                setUp(holder);
                controller.setFull();
            } else {
                list.get(position).setOn(false);
                setDown(holder);
                controller.deleteIntent();
            }

            new Thread(() -> {
                alarmDAO.update(list.get(position));
                authController.addAlarmsToDb();
            }).start();
            notifyItemChanged(position);

        });
        holder.checked_layout.setOnClickListener(ch -> {
            holder.itemView.setLongClickable(true);
            listOfDeleted.remove(list.get(position));
            if (listOfDeleted.isEmpty()) {
                ifNotChecked(holder);
                setUp(holder);
            } else {
                holder.checked_layout.setVisibility(View.GONE);
                holder.on_off.setEnabled(true);
                holder.itemView.setEnabled(true);
                setUp(holder);
            }
        });

        holder.itemView.setOnClickListener(v -> {
            controller.deleteIntent();
            fragmentManager.beginTransaction().replace(R.id.fl_content, AddFragment.newInstance(alarm)).commit();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onItemDismiss(int position) {

        AlarmController controller = new AlarmController(list.get(position));
        controller.deleteIntent();
        new Thread(() -> {
            alarmDAO.clear();
            alarmDAO.saveAll(list);
            authController.clearDb();
            authController.addAlarmsToDb();

        }).start();

        list.remove(position);
        notifyItemRemoved(position);
        Toast.makeText(activity, "Будильник удалён", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(list, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    public void setDown(AlarmViewHolder holder) {
        holder.time.setTextColor(activity.getColor(R.color.put_off));
        holder.message.setTextColor(activity.getColor(R.color.put_off));
        holder.days.setTextColor(activity.getColor(R.color.put_off));
        holder.on_off.setTrackDrawable(App.getInstance().getDrawable(R.drawable.icon_switch_track_down));
    }

    public void setUp(AlarmViewHolder holder) {
        holder.time.setTextColor(activity.getColor(R.color.main_text_and_icons_color));
        holder.on_off.setTrackDrawable(App.getInstance().getDrawable(R.drawable.icon_switch_track_up));
        holder.message.setTextColor(activity.getColor(R.color.main_text_and_icons_color));
        holder.days.setTextColor(activity.getColor(R.color.main_text_and_icons_color));
    }

    public void ifChecked(AlarmViewHolder holder) {
        binding.deleteAny.setVisibility(View.VISIBLE);
        binding.putOn.setVisibility(View.VISIBLE);
        setDown(holder);
        holder.checked_layout.setVisibility(View.VISIBLE);
        holder.on_off.setEnabled(false);
        holder.itemView.setEnabled(false);
        holder.checked_layout.setEnabled(true);
    }

    public void ifNotChecked(AlarmViewHolder holder) {
        binding.deleteAny.setVisibility(View.GONE);
        binding.putOn.setVisibility(View.GONE);
        holder.checked_layout.setVisibility(View.GONE);
        holder.on_off.setEnabled(true);
        holder.itemView.setEnabled(true);
        holder.itemView.setLongClickable(true);
        setUp(holder);
    }

    public class AlarmViewHolder extends RecyclerView.ViewHolder {
        private final TextView time;
        private final TextView days;
        private final TextView message;
        private final Switch on_off;
        private final LinearLayout checked_layout;
        private final LinearLayout switch_layout;
        private final LinearLayout info_layout;

        public AlarmViewHolder(View itemView) {
            super(itemView);
            checked_layout = itemView.findViewById(R.id.checked);
            switch_layout = itemView.findViewById(R.id.switch_layout);
            info_layout = itemView.findViewById(R.id.all_info);
            on_off = itemView.findViewById(R.id.OnOff);
            time = itemView.findViewById(R.id.time);
            message = itemView.findViewById(R.id.textM);
            days = itemView.findViewById(R.id.days);

        }
    }
}