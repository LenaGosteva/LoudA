package com.example.loudalarm.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.loudalarm.App;
import com.example.loudalarm.R;
import com.example.loudalarm.databinding.ActivityProfileBinding;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {
    ActivityProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(App.getThemes()[App.getDatabaseSP().getIndexOfTheme()]);

        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        binding.cancel.setOnClickListener(cancel->{
            finish();
        });
        binding.outAuth.setOnClickListener(outAuth->{
            FirebaseAuth.getInstance().signOut();
        });




    }
}