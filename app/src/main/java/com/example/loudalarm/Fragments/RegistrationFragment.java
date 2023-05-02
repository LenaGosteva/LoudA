package com.example.loudalarm.Fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.loudalarm.Activities.InfoActivity;
import com.example.loudalarm.Activities.MainActivity;
import com.example.loudalarm.App;
import com.example.loudalarm.AuthController.AuthController;
import com.example.loudalarm.R;
import com.example.loudalarm.databinding.FragmentAddBinding;
import com.example.loudalarm.databinding.FragmentRegistrationBinding;
import com.example.loudalarm.databinding.FragmentSettingsBinding;


public class RegistrationFragment extends Fragment {
    FragmentRegistrationBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentRegistrationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        AuthController authController = new AuthController();
        binding.buttonEnter.setOnClickListener(enter->{
            String email = binding.inputEmail.getInputText();
            String password = binding.inputPassword.getInputText();
            String nickname = binding.nickname.getInputText();
            authController.registerUser(email, password, task->{
                if(task.isSuccessful()){
                    startActivity(new Intent(App.getInstance(), InfoActivity.class));
                }else{
                    Toast.makeText(App.getInstance(), "LKa", Toast.LENGTH_SHORT).show();
                }

                });
        });
    }

}