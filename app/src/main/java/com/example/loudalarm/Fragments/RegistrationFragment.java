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
import com.example.loudalarm.AuthController.DBController;
import com.example.loudalarm.databinding.FragmentRegistrationBinding;

import java.util.Objects;


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
        DBController authController = new DBController();

        binding.buttonEnter.setOnClickListener(enter -> {
            String password = binding.inputPassword.getInputText() != null ? binding.inputPassword.getInputText() : " ";
            String email = binding.inputEmail.getInputText() != null ? binding.inputEmail.getInputText() : " ";
            String name = binding.inputName.getInputText() != null ? binding.inputName.getInputText() : "Unknown";

            String finalEmail = email;
            String finalName = name;
            if (!email.isEmpty() && !password.isEmpty()) {
                authController.registerUser(email, password, authResult -> {
                    if (authResult.isSuccessful()) {
                        authController.updateName(finalName);
                        authController.addUserToDb(finalEmail, finalName, unused -> {
                            if (unused.isSuccessful())
                                startActivity(new Intent(getContext(), MainActivity.class));
                        });
                        authController.addIconToDb();
                    } else {
                        Toast.makeText(this.getContext(), Objects.requireNonNull(authResult.getException()).getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(this.getContext(), "Заполните обязательные поля!", Toast.LENGTH_SHORT).show();
            }
        });

    }

}