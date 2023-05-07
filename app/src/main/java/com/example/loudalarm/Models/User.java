package com.example.loudalarm.Models;

import com.example.loudalarm.App;
import com.example.loudalarm.Room.AlarmEntity;

import java.util.List;

public class User {
    List<AlarmEntity> alarmEntities;
    String email;
    String nickname;
    String url = "";


    public User(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
        new Thread(() -> {
            this.alarmEntities = App.getDatabase().alarmDAO().getAll();
        }).start();

    }

    public User() {
        this.email = " ";
        this.nickname = " ";
        new Thread(() -> {
            this.alarmEntities = App.getDatabase().alarmDAO().getAll();
        }).start();

    }

    public List<AlarmEntity> getAlarmEntities() {
        return alarmEntities;
    }

    public void setAlarmEntities(List<AlarmEntity> alarmEntities) {
        this.alarmEntities = alarmEntities;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
