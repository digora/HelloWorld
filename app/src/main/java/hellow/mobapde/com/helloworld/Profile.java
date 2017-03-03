package hellow.mobapde.com.helloworld;

import java.util.ArrayList;

/**
 * Created by Patrick on 3/3/2017.
 */

public class Profile {
    private String name;
    private ArrayList<Adventure> completed_adventures;
    private ArrayList<Stamp> stamps;
    private String gender;

    public Profile() {
    }

    public Profile(String name, ArrayList<Adventure> completed_adventures, ArrayList<Stamp> stamps, String gender) {
        this.name = name;
        this.completed_adventures = completed_adventures;
        this.stamps = stamps;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Adventure> getCompleted_adventures() {
        return completed_adventures;
    }

    public void setCompleted_adventures(ArrayList<Adventure> completed_adventures) {
        this.completed_adventures = completed_adventures;
    }

    public ArrayList<Stamp> getStamps() {
        return stamps;
    }

    public void setStamps(ArrayList<Stamp> stamps) {
        this.stamps = stamps;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
