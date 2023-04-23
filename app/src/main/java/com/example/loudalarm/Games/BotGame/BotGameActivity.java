package com.example.loudalarm.Games.BotGame;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.loudalarm.Activities.MainActivity;
import com.example.loudalarm.App;
import com.example.loudalarm.R;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class BotGameActivity extends AppCompatActivity
        implements Game.ResultsCallback, ButtonClass.MyOnClickListener, View.OnTouchListener {
    private static final int MATRIX_SIZE = 10;// можете ставить от 2 до 20))
    private static int COUNT = 0;
    long startTime = System.nanoTime();
    GridLayout mGridLayout;
    //ui
    private TextView mUpText, mLowText;
    private ButtonClass[][] mButtons;

    private Game game;
    Ringtone musicPlay;
    long timeOfPass = System.nanoTime();
    long stopTime;
    AtomicInteger howManyPassed = new AtomicInteger(0);
    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(App.getThemes()[App.getIndexOfTheme()]);
        setVolumeControlStream(AudioManager.STREAM_DTMF);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot_game);

        mGridLayout = findViewById(R.id.my_grid);


        TextView text = findViewById(R.id.winns);
        text.setText("Выигрыши: " + COUNT);
        mGridLayout.setColumnCount(MATRIX_SIZE);
        mGridLayout.setRowCount(MATRIX_SIZE);
        mButtons = new ButtonClass[MATRIX_SIZE][MATRIX_SIZE];//5 строк и 5 рядов

        //создаем кнопки для цифр
        for (int yPos = 0; yPos < MATRIX_SIZE; yPos++) {
            for (int xPos = 0; xPos < MATRIX_SIZE; xPos++) {
                ButtonClass mBut = new ButtonClass(this, xPos, yPos);

                mBut.setTextSize(30 - MATRIX_SIZE);
                Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
                mBut.setTypeface(boldTypeface);
                mBut.setTextColor(ContextCompat.getColor(this, R.color.white));
                mBut.setOnClickListener(this);
                mBut.setPadding(1, 1, 1, 1); //так цифры будут адаптироваться под размер

                mBut.setAlpha(1);
                mBut.setClickable(false);
                mBut.setBackgroundResource(R.drawable.bg_grey);

                mButtons[yPos][xPos] = mBut;
                mGridLayout.addView(mBut);
            }
        }

        mUpText = findViewById(R.id.upper_scoreboard);
        mLowText = findViewById(R.id.lower_scoreboard);

        //расположим кнопки с цифрами равномерно внутри mGridLayout
        mGridLayout.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        setButtonsSize();
                        //нам больше не понадобится OnGlobalLayoutListener
                        mGridLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });

        game = new Game(this, MATRIX_SIZE); //создаем класс игры
        game.startGame(); //и запускаем ее


        if (COUNT > 2 || System.nanoTime() - startTime > 120_000_000_000L) {
            new AlertDialog.Builder(this)
                    .setMessage("Вы можете выйти из игры. Хотите?")
                    .setPositiveButton("ДА", (dialog, id) -> {
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    })
                    .setNegativeButton("НЕТ", (dialog, id) -> {
                        dialog.dismiss();
                    })
                    .create().show();
        }

        musicPlay = RingtoneManager.getRingtone(this, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));

        AtomicBoolean isMusicPlay = new AtomicBoolean(false);
        AtomicBoolean d = new AtomicBoolean(true);

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            if (System.nanoTime() - timeOfPass >= 20 * 1_000_000_000L) {
                if (!isMusicPlay.get() && d.get()) {
                    musicPlay.play();
                    isMusicPlay.set(true);
                    d.set(false);
                } else if ((howManyPassed.get()) > 3) {
                    musicPlay.stop();
                    isMusicPlay.set(false);
                    d.set(true);
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }//onCreate

    private void setButtonsSize() {
        int pLength;
        final int MARGIN = 6;

        int pWidth = mGridLayout.getWidth();
        int pHeight = mGridLayout.getHeight();
        int numOfCol = MATRIX_SIZE;
        int numOfRow = MATRIX_SIZE;

        //сделаем mGridLayout квадратом
        if (pWidth >= pHeight) pLength = pHeight;
        else pLength = pWidth;
        ViewGroup.LayoutParams pParams = mGridLayout.getLayoutParams();
        pParams.width = pLength;
        pParams.height = pLength;
        mGridLayout.setLayoutParams(pParams);

        int w = pLength / numOfCol;
        int h = pLength / numOfRow;

        for (int yPos = 0; yPos < MATRIX_SIZE; yPos++) {
            for (int xPos = 0; xPos < MATRIX_SIZE; xPos++) {
                GridLayout.LayoutParams params = (GridLayout.LayoutParams)
                        mButtons[yPos][xPos].getLayoutParams();
                params.width = w - 2 * MARGIN;
                params.height = h - 2 * MARGIN;
                params.setMargins(MARGIN, MARGIN, MARGIN, MARGIN);
                mButtons[yPos][xPos].setLayoutParams(params);
                //Log.w(TAG, "process goes in customizeMatrixSize");
            }
        }
    }

    //ButtonClass.MyOnClickListener интерфейс
//*************************************************************************
    @Override
    public void OnTouchDigit(ButtonClass v) {
        game.OnUserTouchDigit(v.getIdY(), v.getIdX());
        int g = howManyPassed.get() + 1;
        howManyPassed.set(g);
    }

    //Game.ResultsCallback интерфейс
//*************************************************************************
    @Override
    public void changeLabel(boolean upLabel, int points) {
        if (upLabel) mUpText.setText(String.format("Бот: %d", points));
        else mLowText.setText(String.format("Вы: %d", points));
    }

    @Override
    public void changeButtonBg(int y, int x, boolean row, boolean active) {

        if (active) {
            if (row) mButtons[y][x].setBackgroundResource(R.drawable.bg_blue);
            else mButtons[y][x].setBackgroundResource(R.drawable.bg_red);

        } else {
            mButtons[y][x].setBackgroundResource(R.drawable.bg_grey);
        }
    }

    @Override
    public void setButtonText(int y, int x, int text) {
        mButtons[y][x].setText(String.valueOf(text));
    }

    @Override
    public void changeButtonClickable(int y, int x, boolean clickable) {
        mButtons[y][x].setClickable(clickable);
    }

    @Override
    public void onResult(int playerOnePoints, int playerTwoPoints) {

        String text;
        if (playerOnePoints > playerTwoPoints) {
            COUNT += 1;

            text = "Вы победили";
        } else if (playerOnePoints < playerTwoPoints) {
            text = "Бот победил";
        } else text = "Ничья";

        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();

        //через 1500 миллисекунд выполним метод run
        //начать новую игру — пересоздать класс MainActivity
        new Handler().postDelayed(this::recreate, 1500);
    }

    @Override
    public void recreate() {
//COUNT+=1;
        super.recreate();
    }

    @Override
    public void onClick(final int y, final int x, final boolean flyDown) {

        final Button currentBut = mButtons[y][x];

        currentBut.setAlpha(0.7f);
        currentBut.setClickable(false);

        AnimationSet sets = new AnimationSet(false);
        int direction = flyDown ? 400 : -400;
        TranslateAnimation animTr = new TranslateAnimation(0, 0, 0, direction);
        animTr.setDuration(810);
        AlphaAnimation animAl = new AlphaAnimation(0.4f, 0f);
        animAl.setDuration(810);
        sets.addAnimation(animTr);
        sets.addAnimation(animAl);
        currentBut.startAnimation(sets);

        new Handler().postDelayed(() -> {

            currentBut.clearAnimation();
            currentBut.setAlpha(0);
        }, 800);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)
        {
            return false;
        }
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP)
        {
            return false;
        }
        if (keyCode == KeyEvent.KEYCODE_HOME)
        {
            return false;
        }
        return true;
    }
}
        