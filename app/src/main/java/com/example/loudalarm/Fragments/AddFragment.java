package com.example.loudalarm.Fragments;

import static android.app.Activity.RESULT_OK;

import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.loudalarm.AlarmIntentsController.AlarmController;
import com.example.loudalarm.App;
import com.example.loudalarm.AuthController.DBController;
import com.example.loudalarm.R;
import com.example.loudalarm.Room.AlarmDAO;
import com.example.loudalarm.Room.AlarmEntity;
import com.example.loudalarm.databinding.FragmentAddBinding;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddFragment extends Fragment {
    private final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
    public String checkedMusicStringNameUri;
    public boolean canPlay;
    public String days = "";
    public AudioManager audioManager;
    private AlarmDAO alarmDAO;
    public AlarmManager alarmManager;
    private final AlarmEntity alarm;
    private List<AlarmEntity> alarms = new ArrayList<>();
    private MaterialTimePicker materialTimePicker;
    private Calendar calendar;
    private FragmentAddBinding binding;

    public AddFragment(AlarmEntity alarm) {
        this.alarm = alarm;
    }

    public static AddFragment newInstance(AlarmEntity alarm) {
        return new AddFragment(alarm);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_add, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentAddBinding.bind(view);
        binding.alarmButton.setText(alarm.time_on_clock_in_hours_and_minutes);
        binding.today.setVisibility(View.VISIBLE);

        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, alarm.hours);
        calendar.set(Calendar.MINUTE, alarm.minutes);



        binding.volumeControl.setProgress(alarm.vol);
        binding.moreLoud.setChecked(alarm.isAlarmAdjustVolume);


        canPlay = alarm.alarmCanPlay;
        binding.vibration.setChecked(alarm.vib);
        binding.setOnToday.setChecked(alarm.today);

        if (!alarm.textMessage.equals(" ")) binding.textMessage.setText(alarm.textMessage);
        binding.nameOfCheckedMusic.setText(alarm.music);


        binding.monday.setChecked(alarm.monday);
        binding.tuesday.setChecked(alarm.tuesday);
        binding.wednesday.setChecked(alarm.wednesday);
        binding.thuesday.setChecked(alarm.thursday);
        binding.friday.setChecked(alarm.friday);
        binding.saturday.setChecked(alarm.saturday);
        binding.sunday.setChecked(alarm.sunday);


        final int id = alarm.id;
        checkedMusicStringNameUri = alarm.music;


        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        alarmManager = App.getAlarmManager();


        binding.ringtone.setOnClickListener(h -> {
            Intent intent_upload = new Intent();
            intent_upload.setType("audio/*");
            intent_upload.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent_upload, 1);
        });




        binding.volumeControl.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        binding.volumeControl.setProgress(alarm.vol);
        binding.volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                binding.volumeControl.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        binding.moreLoud.setChecked(alarm.isAlarmAdjustVolume);

        canPlay = alarm.alarmCanPlay;
        binding.vibration.setChecked(alarm.vib);


        binding.alarmButton.setOnClickListener(n -> {
            materialTimePicker = new MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .setHour(alarm.hours)
                    .setMinute(alarm.minutes)
                    .setTitleText("Выберите время для будильника")
                    .build();

            materialTimePicker.addOnPositiveButtonClickListener(v -> {

                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                calendar.set(Calendar.MINUTE, materialTimePicker.getMinute());
                calendar.set(Calendar.HOUR_OF_DAY, materialTimePicker.getHour());

                alarm.hours = materialTimePicker.getHour();
                alarm.minutes = materialTimePicker.getMinute();

                binding.alarmButton.setTextSize(90);
                binding.alarmButton.setText(sdf.format(calendar.getTime()));
                alarm.setTime_on_clock_in_hours_and_minutes(binding.alarmButton.getText().toString());
            });
            materialTimePicker.show(getActivity().getSupportFragmentManager(), "tag_picker");
        });


        binding.monday.setOnClickListener(c -> {
            alarm.monday = binding.monday.isChecked();
            setVisibilityToWeekDayToggles();
        });
        binding.tuesday.setOnClickListener(c -> {
            alarm.tuesday = binding.tuesday.isChecked();
            setVisibilityToWeekDayToggles();
        });
        binding.wednesday.setOnClickListener(c -> {
            alarm.wednesday = binding.wednesday.isChecked();
            setVisibilityToWeekDayToggles();
        });
        binding.thuesday.setOnClickListener(c -> {
            alarm.thursday = binding.thuesday.isChecked();
            setVisibilityToWeekDayToggles();
        });
        binding.friday.setOnClickListener(c -> {
            alarm.friday = binding.friday.isChecked();
            setVisibilityToWeekDayToggles();
        });
        binding.saturday.setOnClickListener(c -> {
            alarm.saturday = binding.saturday.isChecked();
            setVisibilityToWeekDayToggles();
        });
        binding.sunday.setOnClickListener(c -> {
            alarm.sunday = binding.sunday.isChecked();
            setVisibilityToWeekDayToggles();
        });
        binding.setOnToday.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (binding.setOnToday.isChecked()) {
                binding.repeat.setVisibility(View.GONE);
                binding.mtrlCalendarDaysOfWeek.setVisibility(View.GONE);
                setAllDaysWidgetsChecked(false, binding.friday, binding.monday, binding.thuesday, binding.saturday, binding.tuesday, binding.wednesday, binding.sunday);
                setAllDaysChecked(false, alarm.monday, alarm.saturday,
                        alarm.friday, alarm.thursday, alarm.tuesday, alarm.wednesday, alarm.sunday);
            } else {
                binding.repeat.setVisibility(View.VISIBLE);
                binding.mtrlCalendarDaysOfWeek.setVisibility(View.VISIBLE);
            }
        });


        binding.createNewAlarm.setOnClickListener(v -> {
            if (binding.monday.isChecked() && !days.contains("M ")) days += "M ";
            if (binding.tuesday.isChecked() && !days.contains("TU ")) days += "TU ";
            if (binding.wednesday.isChecked() && !days.contains("W ")) days += "W ";
            if (binding.thuesday.isChecked() && !days.contains("TH ")) days += "TH ";
            if (binding.friday.isChecked() && !days.contains("F ")) days += "F ";
            if (binding.saturday.isChecked() && !days.contains("SA ")) days += "SA ";
            if (binding.sunday.isChecked() && !days.contains("SU ")) days += "SU ";

            if (!binding.setOnToday.isChecked() || calendar.getTime().before(Calendar.getInstance().getTime())) {

                calendar.setTimeInMillis(calendar.getTimeInMillis() + 1000 * 60 * 60 * 24);
            }
            Date date = new Date(calendar.getTimeInMillis());
            if (days.isEmpty())
                days = date.toString().substring(4, 10) + ", " + date.toString().substring(0, 3);


            alarm.setAlarmCanPlay(canPlay);
            alarm.setDays(days);
            alarm.setVol(binding.volumeControl.getProgress());
            alarm.setVib(binding.vibration.isChecked());
            alarm.setId(id);
            alarm.setToday(binding.setOnToday.isChecked());
            alarm.setTime(calendar.getTimeInMillis());
            alarm.setAlarmAdjustVolume(binding.moreLoud.isChecked());
            alarm.setTextMessage(binding.textMessage.getText().toString());
            alarm.setMusic(checkedMusicStringNameUri);


            new Thread(() -> {
                alarmDAO = App.getDatabase().alarmDAO();

                alarms = alarmDAO.getAll();
                int foundedAlarmIndex = -1;
                for (int i = 0; i < alarms.size(); i++) {
                    if (alarms.get(i).id == alarm.id) {
                        foundedAlarmIndex = i;
                        break;
                    }
                }
                if (foundedAlarmIndex == -1) {
                    alarms.add(alarm);
                    foundedAlarmIndex = alarms.size() - 1;
                } else {
                    alarms.set(foundedAlarmIndex, alarm);
                }
                alarms.sort(Comparator.comparingLong(o -> o.time));
                alarmDAO.clear();
                alarmDAO.saveAll(alarms);

                new DBController().addAlarmsToDb();

                AlarmController controller = new AlarmController(alarm);
                controller.setFull();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_content, HomeFragment.newInstance(
                                alarmDAO.getAll())).commit();
            }).start();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setVisibilityToWeekDayToggles() {

        if (alarm.sunday || alarm.saturday || alarm.friday || alarm.thursday || alarm.wednesday || alarm.tuesday || alarm.monday) {
            binding.setOnToday.setVisibility(View.GONE);
            binding.today.setVisibility(View.GONE);
            alarm.today = false;
        } else {
            binding.setOnToday.setVisibility(View.VISIBLE);
            binding.today.setVisibility(View.VISIBLE);

        }
    }


    private void setAllDaysWidgetsChecked(boolean b, ToggleButton... views) {
        for (ToggleButton v : views) {
            v.setChecked(b);
        }
    }

    private void setAllDaysChecked(boolean b, boolean... days) {
        for (boolean v : days) {
            v = b;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Uri uri = intent.getData();
                checkedMusicStringNameUri = uri.getPath();


                File tempFile = new File(checkedMusicStringNameUri.trim());
                String checkedMusicStringName = tempFile.getName();
                binding.nameOfCheckedMusic.setText(checkedMusicStringName);
            }
        } else {
            binding.nameOfCheckedMusic.setText(checkedMusicStringNameUri);

        }
    }
}