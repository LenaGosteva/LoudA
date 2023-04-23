package com.example.loudalarm.Sp;

import android.net.Uri;

public interface SPDatabase {
    void saveUri(Uri uri);

    Uri getUri();

    boolean getNumberOfInstance();

    void saveNumberOfInstance(boolean bool);

    int getIndexOfTheme();

    void saveIndexOfTheme(int index);

}
