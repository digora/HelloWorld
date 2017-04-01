package hellow.mobapde.com.helloworld.Beans;

import android.graphics.Color;
import android.graphics.Picture;
import android.util.Log;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Patrick on 3/3/2017.
 */

public class Stop {
    @JsonIgnore
    private String key;
    private String latitude;
    private String longitude;
    private String description;
    private MarkerOptions markerOptions;
    private Picture picture;
    private CircleOptions circleOptions;
    private boolean visited;


    public Stop() {
        visited = false;
    }

    public Stop(String latitude, String longitude, String description, MarkerOptions markerOptions, Picture picture, CircleOptions circleOptions, boolean visited) {

        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.markerOptions = markerOptions;
        this.picture = picture;
        this.circleOptions = circleOptions;
        this.visited = visited;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public MarkerOptions getMarkerOptions() {
        return markerOptions;
    }

    public void setMarkerOptions(MarkerOptions markerOptions) {
        this.markerOptions = markerOptions;
    }

    public CircleOptions getCircleOptions() {
        return circleOptions;
    }

    public void setCircleOptions(CircleOptions circleOptions) {
        this.circleOptions = circleOptions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return Double.parseDouble(latitude);
    }

    public double getLongitude() {
        return Double.parseDouble(longitude);
    }

    public LatLng getLatLng() {
        return new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
    }

    public void setLatLng(LatLng latLng) {
        markerOptions.position(latLng);
        circleOptions.center(latLng);
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public void setTitle(String title) {
        markerOptions.title(title);
    }

    public void setRadius(double radius) {
        circleOptions.radius(radius);
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void toggleVisited () {
        visited = !visited;
    }
}
