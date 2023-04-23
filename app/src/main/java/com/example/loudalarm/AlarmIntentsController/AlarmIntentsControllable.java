package com.example.loudalarm.AlarmIntentsController;

import android.app.PendingIntent;

public interface AlarmIntentsControllable {
    PendingIntent getAlarmActionPendingIntent();

    void setRepeatingAlarm(int number_of_week);

    void setAlarm();

    void deleteIntent();
}
