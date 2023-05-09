package com.example.loudalarm.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;

import com.example.loudalarm.Activities.ProfileActivity;
import com.example.loudalarm.Adapters.AlarmAdapter;
import com.example.loudalarm.AlarmIntentsController.AlarmController;
import com.example.loudalarm.App;
import com.example.loudalarm.AuthController.DBController;
import com.example.loudalarm.Room.AlarmDAO;
import com.example.loudalarm.Room.AlarmEntity;
import com.example.loudalarm.TouchHelper.SimpleItemTouchHelperCallback;
import com.example.loudalarm.databinding.FragmentHomeBinding;

import java.util.Date;
import java.util.List;

public class HomeFragment extends Fragment {

    private ItemTouchHelper mItemTouchHelper;

    private FragmentHomeBinding binding;
    int click = 0;
    AlarmDAO alarmDatabaseDAO;

    List<AlarmEntity> alarms;
    DBController authController = new DBController();
    public HomeFragment(List<AlarmEntity> alarms) {
        this.alarms = alarms;
    }

    public static HomeFragment newInstance(List<AlarmEntity> alarms) {
        return new HomeFragment(alarms);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        Date date = new Date();
        binding.date.setText(date.toString().substring(4, 10) + ", " + date.toString().substring(0, 3));
//        new Thread(() -> {
//            alarmDatabaseDAO = App.getDatabase().alarmDAO();
//            if (alarms.size() <= alarmDatabaseDAO.getAll().size()) {
//                alarmDatabaseDAO.updateAll(alarms);
//
//            } else {
//                alarmDatabaseDAO.deleteAll(alarmDatabaseDAO.getAll());
//                alarmDatabaseDAO.saveAll(alarms);
//            }
//
//        }).start();
        return binding.getRoot();

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AlarmAdapter adapter = new AlarmAdapter(getActivity().getSupportFragmentManager(), alarms, getActivity(), binding);
        binding.recyclerView.setAdapter(adapter);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);

        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(binding.recyclerView);


        binding.checkAll.setOnClickListener(check_all -> {
            if (click % 2 == 0) {
                adapter.listOfDeleted.clear();
                adapter.listOfDeleted.addAll(adapter.list);
                adapter.notifyDataSetChanged();

            } else {
                adapter.listOfDeleted.clear();
                adapter.notifyDataSetChanged();
            }
            click += 1;
        });

        binding.putOn.setOnClickListener(put_on -> {
            for (AlarmEntity alarm : adapter.listOfDeleted) {
                if (!alarm.on) {
                    new AlarmController(alarm).setFull();
                }
                alarm.setOn(true);
                adapter.setUp(adapter.holderPB);
adapter.notifyDataSetChanged();
            }
            Toast.makeText(getContext(), "Выбранные будильники включены", Toast.LENGTH_SHORT).show();

            new Thread(() -> {
                alarmDatabaseDAO = App.getDatabase().alarmDAO();
                alarmDatabaseDAO.updateAll(adapter.listOfDeleted);
                alarms = alarmDatabaseDAO.getAll();
                adapter.list = alarms;
                authController.clearAlarmsDB();
                authController.addAlarmsToDb();
                adapter.listOfDeleted.clear();
            }).start();
            adapter.notifyDataSetChanged();

            binding.deleteAny.setVisibility(View.GONE);
            binding.putOn.setVisibility(View.GONE);
        });
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        binding.deleteAny.setOnClickListener(delete -> {

            for (AlarmEntity alarm : adapter.listOfDeleted) {
                AlarmController controller = new AlarmController(alarm);
                controller.deleteIntent();
            }
            new Thread(() -> {
                alarmDatabaseDAO = App.getDatabase().alarmDAO();
                alarmDatabaseDAO.deleteAll(adapter.listOfDeleted);
                alarms = alarmDatabaseDAO.getAll();
                authController.clearAlarmsDB();
                authController.addAlarmsToDb();
            }).start();
            Toast.makeText(getContext(), "Выбранные будильники удалены", Toast.LENGTH_SHORT).show();
            adapter.list.removeAll(adapter.listOfDeleted);
            adapter.notifyDataSetChanged();
            binding.deleteAny.setVisibility(View.GONE);
            binding.putOn.setVisibility(View.GONE);

        });
        binding.profile.setOnClickListener(pr->{
            Intent intent = new Intent(App.getInstance(), ProfileActivity.class);
            startActivity(intent);
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}