package hellow.mobapde.com.helloworld.Beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Patrick on 3/3/2017.
 */

public class Profile {

    private String key;
    private String name;
    private String gender;
    private String currAdKey;
    @JsonIgnore
    private Map<String, String> adventureLog;
    @Exclude
    private ArrayList<Stamp> stamps;


    public Profile() {
    }

    public Profile(String name, ArrayList<Stamp> stamps, String gender) {
        this.name = name;
        this.stamps = stamps;
        this.gender = gender;
    }

    public Profile(String name, String gender, Map<String, String> adventureLog) {
        this.name = name;
        this.gender = gender;
        this.adventureLog = adventureLog;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Map<String, String> getAdventureLog() {
        return adventureLog;
    }

    public void setAdventureLog(Map<String, String> adventureLog) {
        this.adventureLog = adventureLog;
    }

    public String getCurrAdKey() {
        return currAdKey;
    }

    public void setCurrAdKey(String currAdKey) {
        this.currAdKey = currAdKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
