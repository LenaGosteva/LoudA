package com.example.loudalarm.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.loudalarm.Activities.MainActivity;
import com.example.loudalarm.App;
import com.example.loudalarm.AuthController.DBController;
import com.example.loudalarm.Room.AlarmDAO;
import com.example.loudalarm.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {
    FragmentLoginBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    AlarmDAO alarmDatabaseDAO;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DBController authController = new DBController();
        binding.buttonEnter.setOnClickListener(enter->{
            String email = binding.inputEmail.getInputText();
            String password = binding.inputPassword.getInputText();
            authController.enterUser(email, password, task -> {
                if (task.isSuccessful()) {
                    startActivity(new Intent(App.getInstance(), MainActivity.class));
                } else {
                    Toast.makeText(App.getInstance(), "Что-то пошло не так...", Toast.LENGTH_SHORT).show();
                }

            });

        });

        binding.textForgotPassword.setOnClickListener(pass -> {


            androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(getContext());
            alertDialog.setTitle("Изменение пароля").setMessage("Введите почту");

            final EditText input = new EditText(getContext());
            input.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));
            alertDialog.setView(input)
                    .setPositiveButton("Изменить",
                            (dialog, which) -> {
                                authController.sendMailWithNewPassword(input.getText().toString(), task -> {
                                    if (task.isSuccessful())
                                        Toast.makeText(App.getInstance().getApplicationContext(), "Письмо успешно отправлено", Toast.LENGTH_LONG).show();
                                    else if (task.isCanceled())
                                        Toast.makeText(App.getInstance().getApplicationContext(), "Упс! Кажется, что-то пошло не так...", Toast.LENGTH_LONG).show();
                                });
                                dialog.dismiss();
                            })
                    .setNegativeButton("Закрыть",
                            (dialog, which) -> dialog.cancel())
                    .show();


        });


    }

}
