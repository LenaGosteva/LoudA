package com.example.loudalarm.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.loudalarm.Adapters.EnterPagerAdapter;
import com.example.loudalarm.App;
import com.example.loudalarm.R;
import com.example.loudalarm.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

        ActivityLoginBinding binding;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            this.setTheme(App.getThemes()[App.getDatabaseSP().getIndexOfTheme()]);
            super.onCreate(savedInstanceState);
            binding = ActivityLoginBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
            new Thread(() -> {
                try {
                    Thread.sleep(1500);
                    runOnUiThread(() -> {
                        binding.mainIcon.setVisibility(View.VISIBLE);
                    });
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
            binding.pager.setAdapter(new EnterPagerAdapter(getSupportFragmentManager()));
            binding.tabs.setupWithViewPager(binding.pager);
            binding.skipButton.setOnClickListener(v -> {
                new AlertDialog.Builder(this).setTitle("Внимание!")
                        .setIcon(this.getResources().getDrawable(R.drawable.info))
                        .setMessage("Пропустить авторизацию? Это не позволит Вам сохранть данные")
                        .setPositiveButton("Да", (dialog, id) -> {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }).setNegativeButton("Нет", (dialog, id) -> {
                            dialog.dismiss();
                        })
                .create().show();

            });
        }
    }