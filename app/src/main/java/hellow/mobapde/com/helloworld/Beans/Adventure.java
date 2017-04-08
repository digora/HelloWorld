package hellow.mobapde.com.helloworld.Beans;

import android.graphics.Bitmap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Patrick on 3/3/2017.
 */

public class Adventure {

    public static final String CASUAL_DIFFICULTY = "casual";
    public static final String INTERMEDIATE_DIFFICULTY = "intermediate";
    public static final String ADVENTUROUS_DIFFICULTY = "adventurous";

    private String name;
    private String details;
    private String date;
    private int timesStarted;
    private String difficulty;
    @JsonIgnore
    private String status;
    @JsonIgnore
    private String key;
    @JsonIgnore
    private Bitmap picture;
    @JsonIgnore
    private Map<String, Stop> stops;


    /* Uncomment this out. */
    /*public Adventure(String name, String details, String status, ArrayList<Stop> stops) {
        this.name = name;
        this.details = details;
        this.status = status;
        this.stops = stops;
    }*/

    /* For temporary data */

    public Adventure() {
        this.status = "Not Started";

        stops = new LinkedHashMap<>();
    }

    public Adventure(String name, String details,String date,String difficulty) {
        this.name = name;
        this.details = details;
        this.date = date;
    }

    public Adventure(String name, String details, String status, Bitmap picture) {
        this.name = name;
        this.details = details;
        this.status = status;
        this.picture = picture;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTimesStarted() {
        return timesStarted;
    }

    public void setTimesStarted(int timesStarted) {
        this.timesStarted = timesStarted;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Map<String, Stop> getStops() {
        return stops;
    }

    public void setStops(Map<String, Stop> stops) {
        this.stops = stops;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Stop getStop (String key) {
        return stops.get(key);
    }

    public int getNumberOfStops () {
        return stops.size();
    }

    public void addStop (String key, Stop stop) {
        stops.put(key, stop);
    }

    public LatLng getLatLngOfStop (String key) {
        return stops.get(key).getLatLng();
    }
}
