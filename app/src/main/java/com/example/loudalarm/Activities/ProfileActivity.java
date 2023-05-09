package com.example.loudalarm.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.loudalarm.App;
import com.example.loudalarm.AuthController.DBController;
import com.example.loudalarm.Models.User;
import com.example.loudalarm.R;
import com.example.loudalarm.databinding.ActivityProfileBinding;

import java.io.File;

public class ProfileActivity extends AppCompatActivity {
    private final int gal_id = 686;
    ActivityProfileBinding binding;
    String out;
    User user;
    DBController controller = new DBController();
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(App.getThemes()[App.getDatabaseSP().getIndexOfTheme()]);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        if (controller.getUser() != null) {
            out = "Выйти";
            binding.emailOfProfileDesc.setVisibility(View.VISIBLE);
            binding.emailOfProfile.setText(controller.getUser().getEmail());
            binding.nameOfProfile.setText(controller.getUser().getDisplayName());


            controller.getUserFromDb(task -> {
                if (!task.isSuccessful()) {
                } else {
                    User user = task.getResult().getValue(User.class);
                    binding.nameOfProfile.setText(user.getNickname());

                    controller.getStorage().child("images/" + controller.getUser().getUid())
                            .getDownloadUrl()
                            .addOnSuccessListener(uri -> {
                                Glide.with(this).load(uri).fitCenter().into(binding.iconOfProfile);

                            }).addOnFailureListener(exception -> {
                                binding.iconOfProfile.setImageDrawable(getResources().getDrawable(R.drawable.kitty));
                            });
                }
            });
            binding.buttonIcon.setOnClickListener(icon -> {
                showDialogEditIcon(this);
            });

            binding.editName.setOnClickListener(edit -> {
                showDialogEditName(this);


            });

            binding.cancel.setOnClickListener(cancel -> {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            });

        } else {
            binding.outAuth.setText("Войти");
            out = "Войти";
            binding.emailOfProfile.setVisibility(View.GONE);
            binding.emailOfProfileDesc.setVisibility(View.GONE);
            binding.iconOfProfile.setImageDrawable(getDrawable(R.drawable.kitty));
            binding.nameOfProfile.setText("Unknown");
            binding.editName.setVisibility(View.GONE);
        }

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

    private Bitmap getBitmapImage(String path) {
        File mSaveBit = new File(path);
        String filePath = mSaveBit.getPath();
        return BitmapFactory.decodeFile(filePath);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK) {
            if (requestCode == gal_id) {
                Uri url = intent.getData();
                controller.updateIcon(url);
                Glide.with(this).load(url).fitCenter().into(binding.iconOfProfile);


            }
        }

    }


    private void showDialogEditIcon(Context context) {

        dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_two_select);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView select_1 = dialog.findViewById(R.id.select_1);
        TextView select_2 = dialog.findViewById(R.id.select_2);

        select_2.setOnClickListener(v -> {
            dialog.dismiss();
            startDirectoryImages();
        });
        select_1.setText(" ");
        dialog.show();
    }

    private void showDialogEditName(Context context) {

        dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_edit_smth);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText name = dialog.findViewById(R.id.edit_text);
        Button ok = dialog.findViewById(R.id.button_ok), not = dialog.findViewById(R.id.button_not);


        not.setOnClickListener(not_ -> {
                    dialog.dismiss();
                }
        );
        ok.setOnClickListener(not_ -> {
                    controller.updateName(name.getText().toString());
                    binding.nameOfProfile.setText(name.getText().toString());
                    dialog.dismiss();
                }
        );

        dialog.show();
    }

    private void startDirectoryImages() {
        Intent intent_file = new Intent();
        intent_file.setType("image/*");
        intent_file.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(Intent.createChooser(intent_file, "Task image_file"), gal_id);
    }

    @Override
    public void recreate() {
        super.recreate();
    }
}