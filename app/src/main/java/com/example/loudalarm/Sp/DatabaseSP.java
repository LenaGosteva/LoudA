package com.example.loudalarm.Sp;

import static com.example.loudalarm.App.BOT_SP_NAME;
import static com.example.loudalarm.App.INSTANCE_SP_NAME;
import static com.example.loudalarm.App.THEME_SP_NAME;
import static com.example.loudalarm.App.URI_SP_NAME;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;

public class DatabaseSP implements SPDatabase {
    private final SharedPreferences storage;

    public DatabaseSP(Context context) {
        storage = context.getSharedPreferences("storage", Context.MODE_PRIVATE);
    }
    public void saveUri(Uri uri) {
        SharedPreferences.Editor editor = storage.edit();
        editor.putString(URI_SP_NAME, uri.toString());
        editor.apply();
    }

    public Uri getUri() {
        return Uri.parse(storage.getString(URI_SP_NAME, String.valueOf(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALL))));
    }

    public boolean getNumberOfInstance() {
        return storage.getBoolean(INSTANCE_SP_NAME, true);
    }

    public void saveNumberOfInstance(boolean bool) {
        SharedPreferences.Editor editor = storage.edit();
        editor.putBoolean(INSTANCE_SP_NAME, bool);
        editor.apply();
    }
    public boolean getBotFirstInfo() {
        return storage.getBoolean(BOT_SP_NAME, true);
    }

    public void saveBotFirstInfo(boolean bool) {
        SharedPreferences.Editor editor = storage.edit();
        editor.putBoolean(BOT_SP_NAME, bool);
        editor.apply();
    }
    @Override
    public int getIndexOfTheme() {
        return storage.getInt(THEME_SP_NAME, 3);
    }

    @Override
    public void saveIndexOfTheme(int index) {
        SharedPreferences.Editor editor = storage.edit();
        editor.putInt(THEME_SP_NAME, index);
        editor.apply();
    }

}