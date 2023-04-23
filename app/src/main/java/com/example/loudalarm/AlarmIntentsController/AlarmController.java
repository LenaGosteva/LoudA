package com.example.loudalarm.AlarmIntentsController;

import static com.example.loudalarm.App.ID_IDENTIFICATION;
import static com.example.loudalarm.App.getInstance;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import com.example.loudalarm.Activities.RingingActivity;
import com.example.loudalarm.App;
import com.example.loudalarm.Room.AlarmEntity;

import java.util.Calendar;
import java.util.Date;

public class AlarmController implements AlarmIntentsControllable {
    public AlarmEntity alarm;
    public long time;
    public long start_time;
    int id;
    Calendar calendar = new Calendar() {
        @Override
        protected void computeTime() {

        }

        @Override
        protected void computeFields() {

        }

        @Override
        public void add(int field, int amount) {

        }

        @Override
        public void roll(int field, boolean up) {

        }

        @Override
        public int getMinimum(int field) {
            return 0;
        }

        @Override
        public int getMaximum(int field) {
            return 0;
        }

        @Override
        public int getGreatestMinimum(int field) {
            return 0;
        }

        @Override
        public int getLeastMaximum(int field) {
            return 0;
        }
    };

    public AlarmController(AlarmEntity alarm) {
        calendar.setTimeInMillis(alarm.time);
        this.alarm = alarm;
        this.time = alarm.time;
        this.id = alarm.id;
        this.start_time = alarm.time;
    }

    @Override
    public PendingIntent getAlarmActionPendingIntent() {
        Intent intent = new Intent(App.getInstance(), RingingActivity.class);
//        intent.putExtra(TIME_IDENTIFICATION, time);
//        intent.putExtra(URI_IDENTIFICATION, alarm.music);
        intent.putExtra(ID_IDENTIFICATION, id);
        Log.e("URI_IN_CONTROLLER", alarm.music);
        Log.d("ID_IN_PENDING", id + "");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        return PendingIntent.getActivity(getInstance().getApplicationContext(), id, intent, PendingIntent.FLAG_IMMUTABLE);
    }


    @Override
    public void setRepeatingAlarm(int number_of_week) {
        long period = 7 * 24 * 60 * 60 * 1000L;
        Calendar add_calendar = Calendar.getInstance();
        add_calendar.set(Calendar.HOUR_OF_DAY, alarm.hours);
        add_calendar.set(Calendar.MINUTE, alarm.minutes);
        add_calendar.set(Calendar.DAY_OF_WEEK, number_of_week);
        Date date = new Date(add_calendar.getTimeInMillis());
        App.getAlarmManager().setInexactRepeating(AlarmManager.RTC_WAKEUP, add_calendar.getTimeInMillis(), period, getAlarmActionPendingIntent());

    }

    @Override
    public void setAlarm() {

        App.getAlarmManager().set(AlarmManager.RTC_WAKEUP, start_time, getAlarmActionPendingIntent());

    }

    @Override
    public void deleteIntent() {
        Intent intent = new Intent(App.getInstance(), RingingActivity.class);
        Log.e("DELETE", alarm.id + " " + id);
        PendingIntent pendingIntent = PendingIntent.getActivity(App.getInstance(), id, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        App.getAlarmManager().cancel(pendingIntent);
    }


    public void setFull() {

        if ((alarm.days.contains(", ") && alarm.time >= Calendar.getInstance().getTimeInMillis()))
            this.setAlarm();
        if (alarm.monday) {
            this.setRepeatingAlarm(Calendar.MONDAY);
        }
        if (alarm.tuesday) {
            this.setRepeatingAlarm(Calendar.TUESDAY);
        }
        if (alarm.wednesday) {
            this.setRepeatingAlarm(Calendar.WEDNESDAY);
        }
        if (alarm.thursday) {
            this.setRepeatingAlarm(Calendar.THURSDAY);
        }
        if (alarm.friday) {
            this.setRepeatingAlarm(Calendar.FRIDAY);
        }
        if (alarm.saturday) {
            this.setRepeatingAlarm(Calendar.SATURDAY);
        }
        if (alarm.sunday) {
            this.setRepeatingAlarm(Calendar.SUNDAY);

        }
    }
}
