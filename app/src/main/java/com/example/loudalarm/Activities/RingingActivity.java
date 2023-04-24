package com.example.loudalarm.Activities;

import static com.example.loudalarm.App.ID_IDENTIFICATION;

import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loudalarm.App;
import com.example.loudalarm.Games.BotGame.BotGameActivity;
import com.example.loudalarm.Games.MathTrainerGame.MathTrainerActivity;
import com.example.loudalarm.Room.AlarmEntity;
import com.example.loudalarm.databinding.ActivityRingingBinding;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RingingActivity extends AppCompatActivity  {

    private ActivityRingingBinding binding;

    // для того, чтобы музыка могла играть
    private AudioManager audioManager;

    // создаем будильник с мелодией по умолчанию
    private AlarmEntity alarm = new AlarmEntity(App.getDefaultMusicUri() + "");

    // для запуска звуковых эффектов
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(App.getThemes()[App.getDatabaseSP().getIndexOfTheme()]);
        setVolumeControlStream(AudioManager.STREAM_DTMF);
        super.onCreate(savedInstanceState);
        binding = ActivityRingingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // достаем id будильника, чтобы достать все его настройки
        int id = getTaskId();

        // инициализируеем audioManager
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        new Thread(() -> {
            // берем из бд все будильники, сохрняем их в список
            List<AlarmEntity> list = App.getDatabase().alarmDAO().getAll();
            for (AlarmEntity a : list) {
                if (id == a.id) { // сравниваем id - шники
                    this.alarm = a;
                    Log.d("TRUE", "this.alarm = a");
                    break;
                }
            }
            Log.e("LAST_ID_ADB", String.valueOf(App.getDatabase().alarmDAO().getAll().size()));
            Log.e("IS_NULL", String.valueOf(alarm == null));
            Log.e("ID_ALARM", alarm.id + "");
            binding.messageOfAlarm.setText(alarm.getTextMessage()); // ставим текст будильника
            startPlayMusic(alarm); // будильник начинает звонить
        }).start();

        binding.offAlarm.setOnClickListener(off -> {
            stopPlayRingtone(mediaPlayer); // остановка музыки
            int r = getRandom(0, 20);
            switch (r%2) { // рандомно выбирается игра
                case 1:
                    startActivity(new Intent(this, MathTrainerActivity.class));
                    break;
                case 0:
                    startActivity(new Intent(this, BotGameActivity.class));
                    break;
            }
            finish();
        });
    }

    // рандомный выбор
    private int getRandom(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }

    private void startPlayMusic(AlarmEntity alarm) {
        if (!alarm.music.startsWith("content://media")) alarm.setMusic("content://media" + alarm.music); // устанавливаем музыку
        Uri ringtoneUri = Uri.parse(alarm.music); // сохраняем uri
        Log.e("URI_IN_RINGING", alarm.music); // логируем
        try {
            mediaPlayer = MediaPlayer.create(this, ringtoneUri);
            mediaPlayer.setLooping(true);
            mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                            .setLegacyStreamType(AudioManager.STREAM_MUSIC)
                    .build());

            mediaPlayer.start();
        }catch (Exception e){

        }

        if (alarm.isVib()) audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, alarm.vol, AudioManager.FLAG_VIBRATE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, alarm.vol, AudioManager.FLAG_ALLOW_RINGER_MODES);
        if (alarm.isAlarmAdjustVolume) {
            Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() ->
                    audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND), 0, 5, TimeUnit.SECONDS);
        }
    }

    // остановка mediaplayer
    public void stopPlayRingtone(MediaPlayer ringtone) {
        try {
            ringtone.stop();
        }catch (Exception e){

        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)
        {
            return false;
        }
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP)
        {
            return false;
        }
        return keyCode != KeyEvent.KEYCODE_HOME;
    }
}