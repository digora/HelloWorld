package hellow.mobapde.com.helloworld.Beans;

import android.graphics.Picture;

import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Patrick on 3/3/2017.
 */

public class Stop {
    private int stopId;
    private String description;
    private MarkerOptions markerOptions;
    private Picture picture;
    private CircleOptions circleOptions;


    public Stop() {

    }

    public int getStopId() {
        return stopId;
    }

    public void setStopId(int stopId) {
        this.stopId = stopId;
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
        return markerOptions.getPosition().latitude;
    }

    public double getLongitude() {
        return markerOptions.getPosition().longitude;
    }

    public LatLng getLatLng() {
        return markerOptions.getPosition();
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

}
