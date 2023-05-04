package com.example.loudalarm.Activities;

import static com.example.loudalarm.App.SAVE_IMAGE;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.loudalarm.App;
import com.example.loudalarm.AuthController.AuthController;
import com.example.loudalarm.R;
import com.example.loudalarm.databinding.ActivityProfileBinding;
import com.squareup.picasso.Picasso;

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
            binding.nameOfProfile.setText(controller.getUser().getDisplayName() != null ? controller.getUser().getDisplayName() : controller.getUser().getEmail());
            try {
                Log.e("ARGTGTGTG", String.valueOf(controller.getUser().getPhotoUrl()));
                Picasso.with(this).load(controller.getUser()
                                .getPhotoUrl())
                        .fit()
                        .into(binding.iconOfProfile);
            } catch (Exception e) {
                binding.iconOfProfile.setImageDrawable(getDrawable(R.drawable.kitty));
            }

        } else {
            binding.outAuth.setText("Войти");
            out = "Войти";
            binding.iconOfProfile.setImageDrawable(getDrawable(R.drawable.kitty));
            binding.nameOfProfile.setText("Unknown");
            binding.editName.setVisibility(View.GONE);
        }


        binding.buttonIcon.setOnClickListener(icon -> {
//            Intent intent_upload = new Intent();
//            intent_upload.setType("image/*");
//            intent_upload.setAction(Intent.ACTION_GET_CONTENT);
//            startActivityForResult(intent_upload, SAVE_IMAGE);

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Новая картинка").setMessage("Вставьте ссылку на новую картинку");

            final EditText input = new EditText(this);
            input.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));
            alertDialog.setView(input)
                    .setPositiveButton("Изменить",
                            (dialog, which) -> {
                                String url = input.getText().toString();
                                controller.updateIcon(Uri.parse(url));
                                dialog.cancel();
                                Picasso.with(this).load(url).resize(50, 50).centerCrop().into(binding.iconOfProfile);

                            })
                    .setNegativeButton("Закрыть",
                            (dialog, which) -> dialog.cancel())
                    .show();
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
                                binding.nameOfProfile.setText(input.getText().toString());
                                dialog.dismiss();
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

    private void setName(String displayName) {
        binding.nameOfProfile.setText(controller.getUser().getDisplayName());
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