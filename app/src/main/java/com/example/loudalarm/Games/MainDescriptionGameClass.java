package com.example.loudalarm.Games;

import android.graphics.drawable.Drawable;

public class MainDescriptionGameClass {
    public String name;
    public Drawable image;
    public String description;

    public MainDescriptionGameClass(String name, Drawable image, String description) {
        this.name = name;
        this.image = image;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
