package com.example.loudalarm.Room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

@Entity
public class AlarmEntity implements Serializable {


    @PrimaryKey(autoGenerate = true)
    public int id;

    public boolean isAlarmAdjustVolume;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setVol(int vol) {
        this.vol = vol;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isVib() {
        return vib;
    }

    public void setVib(boolean vib) {
        this.vib = vib;
    }


    public void setOn(boolean on) {
        this.on = on;
    }

    public void setToday(boolean today) {
        this.today = today;
    }

    public void setAlarmAdjustVolume(boolean alarmAdjustVolume) {
        this.isAlarmAdjustVolume = alarmAdjustVolume;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public int vol, hours, minutes;

    public void setTime_on_clock_in_hours_and_minutes(String time_on_clock_in_hours_and_minutes) {
        this.time_on_clock_in_hours_and_minutes = time_on_clock_in_hours_and_minutes;
    }

    public String time_on_clock_in_hours_and_minutes = " ", days;
    public long time;
    public boolean vib;
    public boolean on;

    public boolean today;
    public boolean monday;
    public boolean tuesday;
    public boolean wednesday;
    public boolean thursday;
    public boolean friday, saturday, sunday;
    public String music;
    public String textMessage;
    public boolean alarmCanPlay;

    public void setAlarmCanPlay(boolean alarmCanPlay) {
        this.alarmCanPlay = alarmCanPlay;
    }


    public AlarmEntity(String music) {
        this.alarmCanPlay = false;
        this.hours = Calendar.getInstance().getTime().getHours();
        this.minutes = Calendar.getInstance().getTime().getMinutes();
        this.textMessage = " ";
        this.days = "";
        this.saturday = false;
        this.time_on_clock_in_hours_and_minutes = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(Calendar.getInstance().getTime());
        this.thursday = false;
        this.monday = false;
        this.wednesday = false;
        this.sunday = false;
        this.friday = false;
        this.tuesday = false;
        this.on = true;
        this.music = music;
        this.vol = 10;
        this.time = System.currentTimeMillis();
    }
//    public AlarmEntity() {
//        this.alarmCanPlay = false;
//        this.hours = Calendar.getInstance().getTime().getHours();
//        this.minutes = Calendar.getInstance().getTime().getMinutes();
//        this.textMessage = " ";
//        this.days = "";
//        this.saturday = false;
//        this.time_on_clock_in_hours_and_minutes = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(Calendar.getInstance().getTime());
//        this.thursday = false;
//        this.monday = false;
//        this.wednesday = false;
//        this.sunday = false;
//        this.friday = false;
//        this.tuesday = false;
//        this.on = true;
//        this.music = App.getDefaultMusicUri().getPath();
//        this.vol = 10;
//        this.time = System.currentTimeMillis();
//    }
}

