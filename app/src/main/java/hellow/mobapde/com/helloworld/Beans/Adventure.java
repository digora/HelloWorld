package hellow.mobapde.com.helloworld.Beans;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Patrick on 3/3/2017.
 */

public class Adventure {
    private String name;
    private String details;
    private String status;
    private Bitmap picture;
    private ArrayList<Stop> stops;
    private boolean visited;


    /* Uncomment this out. */
    /*public Adventure(String name, String details, String status, ArrayList<Stop> stops) {
        this.name = name;
        this.details = details;
        this.status = status;
        this.stops = stops;
    }*/

    /* For temporary data */

    public Adventure () {
        stops = new ArrayList<Stop>();
        visited = false;
    }

    public Adventure(String name, String details, String status, Bitmap picture) {
        this.name = name;
        this.details = details;
        this.status = status;
        this.picture = picture;
        stops = new ArrayList<Stop>();
    }

    public Adventure(String name, String details, String status, Bitmap picture, boolean visited) {
        this.name = name;
        this.details = details;
        this.status = status;
        this.picture = picture;
        this.stops = new ArrayList<Stop>();
        this.visited = visited;
    }

    public Adventure(String name, String details, String status, Bitmap picture, ArrayList<Stop> stops, boolean visited) {
        this.name = name;
        this.details = details;
        this.status = status;
        this.picture = picture;
        this.stops = stops;
        this.visited = visited;
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

    public ArrayList<Stop> getStops() {
        return stops;
    }

    public void setStops(ArrayList<Stop> stops) {
        this.stops = stops;
    }

    public void addStop (Stop stop) {
        stops.add(stop);
    }

    public Stop getStop (int index) {
        return stops.get(index);
    }

    public int getNumberOfStops () {
        return stops.size();
    }

    public LatLng getLatLngOfStop (int index) {
        return stops.get(index).getLatLng();
    }
}
