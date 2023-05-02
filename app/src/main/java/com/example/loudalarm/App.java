package com.example.loudalarm;

import android.app.AlarmManager;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.example.loudalarm.Room.Database;
import com.example.loudalarm.Sp.DatabaseSP;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


public class App extends Application {

    FirebaseAuth auth;
    public static String ID_IDENTIFICATION = "id";
    public static String URI_SP_NAME = "uri";
    public static String INSTANCE_SP_NAME = "instanse";
    public static String BOT_SP_NAME = "bot";
    public static String THEME_SP_NAME = "theme";
    public static List<String> names_of_themes = new ArrayList<>();
    public static int SAVE_URI = 1234;
    public static int SAVE_THEME = 12934;
    public static int indexOfTheme;
    public static int[] themes = {
            R.style.Background,
            R.style.Background_1,
            R.style.Background_light,
            R.style.Background_light_3,
            R.style.Background_light_2,
            R.style.Background_violet_dark,
    };

    public static int[] getThemes() {
        return themes;
    }

    private static App instance;
    public static Uri defaultMusicUri;
    public static AlarmManager alarmManager;

    private static Database database;

    public static DatabaseSP databaseSP;

    public static Uri getDefaultMusicUri() {
        return databaseSP.getUri();
    }
    public static void setDefaultMusicUri(Uri uri) {
        defaultMusicUri = uri;
        databaseSP.saveUri(defaultMusicUri);
    }

    public static App getInstance() {
        return instance;
    }

    public static Database getDatabase() {
        return database;
    }

    public static DatabaseSP getDatabaseSP() {
        return databaseSP;
    }


    public static AlarmManager getAlarmManager() {
        return alarmManager;
    }

    public static List<String> getNamesOfThemes() {
        return names_of_themes;
    }

    public static int getIndexOfTheme() {
        return indexOfTheme;
    }


    @Override
    public void onCreate() {
        instance = this;
        auth = FirebaseAuth.getInstance();
        databaseSP = new DatabaseSP(instance);
        databaseSP.saveNumberOfInstance(true);
        names_of_themes.add("Dark theme №1");
        names_of_themes.add("Dark theme №2");
        names_of_themes.add("Dark theme №3");
        names_of_themes.add("Light theme №2");
        names_of_themes.add("Light theme №3");
        names_of_themes.add("Violet theme №1");
        indexOfTheme = databaseSP.getIndexOfTheme();
        database = Room.databaseBuilder(this, Database.class, "database.db").fallbackToDestructiveMigration().build();
        alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        this.setTheme(App.getThemes()[App.getIndexOfTheme()]);
        super.onCreate();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
