package com.example.loudalarm.Games.MathTrainerGame;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loudalarm.Activities.MainActivity;
import com.example.loudalarm.App;
import com.example.loudalarm.R;
import com.example.loudalarm.databinding.ActivityMathTrainerBinding;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class MathTrainerActivity extends AppCompatActivity {
    private final Problem problem = new Problem();
    int level ;
    private static int how_many_true_generations = 0;
    Ringtone musicPlay;
    ActivityMathTrainerBinding binding;
    boolean isAnswerTrue = false;
    long timeOfPass = System.nanoTime();
    long stopTime;
    long startTime = System.nanoTime();
    private Dialog dialog;

    private void showDialogInfo(Context context) {

        dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_info_game_level);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        dialog.findViewById(R.id.button_level1).setOnClickListener(not_ -> {
            level = 1;
            dialog.dismiss();
        });
        dialog.findViewById(R.id.button_level2).setOnClickListener(not_ -> {
            level = 2;
            dialog.dismiss();
        });


        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(App.getThemes()[App.getIndexOfTheme()]);
        super.onCreate(savedInstanceState);
        binding = ActivityMathTrainerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        showDialogInfo(this );

        generation_of_task();
        click click = new click();
        binding.text.setOnClickListener(click);
        binding.text1.setOnClickListener(click);
        binding.text2.setOnClickListener(click);

        musicPlay = RingtoneManager.getRingtone(this, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALL));

        AtomicBoolean isMusicPlay = new AtomicBoolean(false);
        AtomicBoolean d = new AtomicBoolean(true);
        AtomicInteger howManyPassed = new AtomicInteger();
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            if (System.nanoTime() - timeOfPass >= 20 * 1_000_000_000L) {
                if (!isMusicPlay.get() && d.get()) {
                    musicPlay.play();
                    isMusicPlay.set(true);
                    howManyPassed.set(how_many_true_generations);
                    d.set(false);
                } else if ((how_many_true_generations - howManyPassed.get()) > 2) {
                    musicPlay.stop();
                    isMusicPlay.set(false);
                    d.set(true);
                }
            }
        }, 0, 1, TimeUnit.SECONDS);

        binding.next.setOnClickListener(v -> {
            timeOfPass = System.nanoTime();

            stopTime = System.nanoTime();
            long deltaTime = stopTime - startTime;
            if (isAnswerTrue) {
                set_next_normal();
                if (deltaTime >= 120_000_000_000L || how_many_true_generations >= 10) {
                    new AlertDialog.Builder(this)
                            .setMessage("Вы можете выйти из игры. Хотите?")
                            .setPositiveButton("ДА", (dialog, id) -> {
                                startActivity(new Intent(this, MainActivity.class));
                                finish();
                            })
                            .setNegativeButton("НЕТ", (dialog, id) -> {
                                dialog.dismiss();
                                set_next_normal();
                                generation_of_task();
                            })
                            .create().show();
                } else {
                    generation_of_task();
                }
            } else {
                new AlertDialog.Builder(this)
                        .setMessage("Вы не можете нажать эту кнопку!")
                        .setNeutralButton("OK", (dialog, id) -> dialog.dismiss())
                        .create().show();
            }
        });
    }

    //
//    НЕ ПОРТЬ ЖИЗНЬ ПОКА! ПОТОМ РАЗБЛОКИРУЮ
//
//    public void onBackPressed() {
//        stopTime = System.nanoTime();
//        long deltaTime = stopTime - startTime;
//        startTime = System.nanoTime();
//        if (deltaTime > 120_000_000_000L && how_many_true_generations>=30){
//            finish();
//        }
//    }
    private void generation_of_task() {
        isAnswerTrue = false;
        unblock(findViewById(R.id.text1), findViewById(R.id.text), findViewById(R.id.text2));
        how_many_true_generations += 1;
        int pos = problem.getRandom(1, 4);
        if (level == 1){
            binding.problem.setText(problem.getProblem(Problem.getRandom(-50, 50), Problem.getRandom(-50, 50)));
        }else{
            binding.problem.setText(problem.getProblem(Problem.getRandom(-50, 50), Problem.getRandom(-50, 50), Problem.getRandom(-50, 50)));
        }

        int a = problem.getNotResult();
        int b = problem.getNotResult();
        if (a != problem.getResult() && a == b) {
            b = a + problem.getRandom(-10, 0);
            if (b == problem.getResult()) b = b + problem.getRandom(12, 15);
        }
        if (a == problem.getResult() && a == b) {
            a = a + problem.getRandom(-10, -1);
            b = b + problem.getRandom(12, 15);
        }

        switch (pos) {
            case 1:
                binding.text1.setText(String.format("%", problem.getResult()));
                binding.text.setText(String.format("%", a));
                binding.text2.setText(String.format("%", b));
                break;
            case 2:

                binding.text2.setText(String.format("%.2f", problem.getResult()));
                binding.text1.setText(String.format("%.2f", b));
                binding.text.setText(String.format("%.2f", a));
                break;
            case 3:
                binding.text.setText(String.format("%.2f", problem.getResult()));
                binding.text1.setText(String.format("%.2f", a));
                binding.text2.setText(String.format("%.2f", b));
                break;
        }


    }

    private void set_next_true() {
        findViewById(R.id.line0).setBackgroundColor(getResources().getColor(R.color.true_button));
        findViewById(R.id.line2).setBackgroundColor(getResources().getColor(R.color.true_button));
    }

    @Override
    public void onBackPressed() {

    }

    public void block(View... view) {
        for (View v : view) {
            v.setClickable(false);
        }

    }

    public void unblock(View... view) {
        for (View v : view) {
            v.setClickable(true);
        }

    }
    private void set_next_normal() {
        findViewById(R.id.line0).setBackgroundColor(getResources().getColor(R.color.main_text_and_icons_color));
        findViewById(R.id.line2).setBackgroundColor(getResources().getColor(R.color.main_text_and_icons_color));
    }

    class click implements View.OnClickListener {

        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.text:
                case R.id.text1:
                case R.id.text2:
                    String text = ((TextView) view).getText().toString();
                    if (text.equals(String.format("%.2f", problem.getResult()))) {
                        set_next_true();
                        block(findViewById(R.id.text1), findViewById(R.id.text), findViewById(R.id.text2));
                        isAnswerTrue = true;
                    } else {
//                        set_next_false();

                        new AlertDialog.Builder(MathTrainerActivity.this)
                                .setMessage("Упс! Кажется, неверно(( ")
                                .setNeutralButton("OK", (dialog, id) -> {
                                    dialog.dismiss();
                                    how_many_true_generations = 0;
                                    generation_of_task();
                                })
                                .create().show();
                    }
                    break;
            }
        }
    }
}