package com.example.loudalarm.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
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

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        MainDescriptionGameClass game = list.get(position);

        holder.name.setText(game.getName());


        holder.itemView.setOnClickListener(click -> {
            new AlertDialog.Builder(activity).setTitle(game.getName())
                    .setIcon(game.getImage())
                    .setMessage(game.getDescription())
                    .setPositiveButton("Отлично)", (dialog, id) -> {
                        dialog.dismiss();
                    }).create().show();
        });
    }


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
}
