package com.example.loudalarm.Adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loudalarm.Games.MainDescriptionGameClass;
import com.example.loudalarm.R;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {

    public final Activity activity;
    List<MainDescriptionGameClass> list;

    public GameAdapter(List<MainDescriptionGameClass> list, Activity activity) {
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_for_list_of_games, parent, false);
        view.setLongClickable(true);
        return new GameAdapter.GameViewHolder(view);
    }

    Dialog dialog;


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class GameViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public GameViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_of_game);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        MainDescriptionGameClass game = list.get(position);

        holder.name.setText(game.getName());


        holder.itemView.setOnClickListener(click -> {

            showDialogInfo(activity, game.getName(), game.getDescription());
        });
    }

    private void showDialogInfo(Context context, String title, String info) {

        dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_info);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView textView = dialog.findViewById(R.id.info_text);
        textView.setText(info);

        TextView title_view = dialog.findViewById(R.id.alert_title);
        title_view.setText(title);


        dialog.findViewById(R.id.button_can).setOnClickListener(not_ -> {
            dialog.dismiss();
        });


        dialog.show();
    }
}
