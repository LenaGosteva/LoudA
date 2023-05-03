package com.example.loudalarm.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loudalarm.App;
import com.example.loudalarm.AuthController.AuthController;
import com.example.loudalarm.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(App.getThemes()[App.getDatabaseSP().getIndexOfTheme()]);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        AuthController authController = new AuthController();
        new Handler().postDelayed(() -> {
            if (App.getDatabaseSP().getNumberOfInstance()) {
                App.databaseSP.saveNumberOfInstance(false);
                startActivity(new Intent(getBaseContext(), InfoActivity.class));
            } else if (authController.getUser() == null) {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            } else {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
            finish();
        }, 700);
    }
}