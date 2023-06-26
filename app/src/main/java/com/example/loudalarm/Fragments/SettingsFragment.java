package com.example.loudalarm.Fragments;

import static android.app.Activity.RESULT_OK;
import static com.example.loudalarm.App.SAVE_THEME;
import static com.example.loudalarm.App.SAVE_URI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.loudalarm.Activities.ThemeActivity;
import com.example.loudalarm.Adapters.GameAdapter;
import com.example.loudalarm.App;
import com.example.loudalarm.Games.MainDescriptionGameClass;
import com.example.loudalarm.R;
import com.example.loudalarm.databinding.FragmentSettingsBinding;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class SettingsFragment extends Fragment {


    Uri uriOfMusic;
    private FragmentSettingsBinding binding;

    public SettingsFragment(Uri uriOfMusic) {
        this.uriOfMusic = uriOfMusic;
    }

    public static SettingsFragment newInstance(Uri uriOfMusic) {
        return new SettingsFragment(uriOfMusic);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        binding.nameOfCheckedMusic.setText(uriOfMusic.getPath());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.nameOfChangedTheme.setText(App.getNamesOfThemes().get(App.getDatabaseSP().getIndexOfTheme()));
        List<MainDescriptionGameClass> games = Arrays.asList(new MainDescriptionGameClass("MathTrainer", this.getContext().getResources().getDrawable(R.drawable.icon_add), this.getContext().getResources().getString(R.string.description_for_game_MathTrainer))
                , new MainDescriptionGameClass("Game with Bot", this.getContext().getResources().getDrawable(R.drawable.icon_add), this.getContext().getResources().getString(R.string.description_for_game_Bot)));
        binding.gamesList.setAdapter(new GameAdapter(games, getActivity()));


        binding.ringtone.setOnClickListener(b -> {
            Intent intent_upload = new Intent();
            intent_upload.setType("audio/*");
            intent_upload.setAction(Intent.ACTION_GET_CONTENT);
            uriOfMusic = intent_upload.getData();
            startActivityForResult(intent_upload, SAVE_URI);
        });
        binding.changeTheme.setOnClickListener(ct -> {
            Intent intent = new Intent(App.getInstance(), ThemeActivity.class);
            startActivity(intent);
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK) {
            if (requestCode == SAVE_THEME) {

            } else if (requestCode == SAVE_URI) {
                uriOfMusic = intent.getData();
                File tempFile = new File(uriOfMusic.getPath().trim());
                binding.nameOfCheckedMusic.setText(tempFile.getName() + "");
                App.setDefaultMusicUri(uriOfMusic);
            }
        }


    }
}