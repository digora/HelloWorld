package hellow.mobapde.com.helloworld.Beans;

import android.graphics.Bitmap;

/**
 * Created by Patrick on 3/3/2017.
 */

public class Stamp {
    private String name;
    private String description;
    private Bitmap picture;
    private String date;

    public Stamp(String name, String description, String date, Bitmap picture) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.picture = picture;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
