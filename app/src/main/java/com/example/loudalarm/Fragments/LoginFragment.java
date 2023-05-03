package com.example.loudalarm.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.loudalarm.Activities.MainActivity;
import com.example.loudalarm.App;
import com.example.loudalarm.AuthController.AuthController;
import com.example.loudalarm.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {
    FragmentLoginBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AuthController authController = new AuthController();
        binding.buttonEnter.setOnClickListener(enter->{
            String email = binding.inputEmail.getInputText();
            String password = binding.inputPassword.getInputText();
            authController.enterUser(email, password, task->{
                if(task.isSuccessful()){
                    startActivity(new Intent(App.getInstance(), MainActivity.class));
                }else{
                    Toast.makeText(App.getInstance(), "...", Toast.LENGTH_SHORT).show();
                }

            });
        });
    }
}