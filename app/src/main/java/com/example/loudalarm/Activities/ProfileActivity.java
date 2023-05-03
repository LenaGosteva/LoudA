package com.example.loudalarm.Activities;

import static com.example.loudalarm.App.ICON_PROFILE;
import static com.example.loudalarm.App.SAVE_IMAGE;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.loudalarm.App;
import com.example.loudalarm.AuthController.AuthController;
import com.example.loudalarm.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {
    AuthController controller = new AuthController();
    ActivityProfileBinding binding;
    String out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(App.getThemes()[App.getDatabaseSP().getIndexOfTheme()]);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());


        if (controller.getUser() != null) {
            out = "Выйти";
            binding.nameOfProfile.setText(controller.getUser().getDisplayName());
            try {
                binding.iconOfProfile.setImageURI(controller.getUser().getPhotoUrl());
            } catch (Exception e) {
                binding.iconOfProfile.setImageURI(Uri.parse(ICON_PROFILE));
            }

        } else {
            binding.outAuth.setText("Войти");
            out = "Войти";
            binding.iconOfProfile.setImageURI(Uri.parse(ICON_PROFILE));
            binding.nameOfProfile.setText("Unknown");
            binding.editName.setVisibility(View.GONE);
        }


        binding.buttonIcon.setOnClickListener(icon -> {
            Intent intent_upload = new Intent();
            intent_upload.setType("image/*");
            intent_upload.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent_upload, SAVE_IMAGE);
        });

        binding.editName.setOnClickListener(edit -> {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Новое имя").setMessage("                 ");

            final EditText input = new EditText(this);
            input.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));
            alertDialog.setView(input)
                    .setPositiveButton("Изменить",
                            (dialog, which) -> {
                                controller.updateName(input.getText().toString());
                                binding.nameOfProfile.setText(controller.getUser().getDisplayName());
                                dialog.cancel();
                            })
                    .setNegativeButton("Закрыть",
                            (dialog, which) -> dialog.cancel())
                    .show();

        });

        binding.cancel.setOnClickListener(cancel -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
        binding.outAuth.setOnClickListener(outAuth -> {
            switch (out) {
                case "Войти":
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
                    break;
                case "Выйти":
                    controller.singOut();
                    recreate();
                    break;
            }

        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK) {
            if (requestCode == SAVE_IMAGE) {
                controller.updateIcon(intent.getData());
                binding.iconOfProfile.setImageURI(intent.getData());
            }
        }


    }

    @Override
    public void recreate() {
        super.recreate();

    }
}