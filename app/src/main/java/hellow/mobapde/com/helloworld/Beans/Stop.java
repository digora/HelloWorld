package hellow.mobapde.com.helloworld.Beans;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Patrick on 3/3/2017.
 */

public class Stop {
    private String description;
    //picture
    private LatLng latLng;
    //the circle thingy


    public Stop() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latLng.latitude;
    }

    public double getLongitude() {
        return latLng.longitude;
    }

    public void setLatitude(double latitude) {
        latLng = new LatLng(latitude, latLng.longitude);
    }

    public void setLongitude(double longitude) {
        latLng = new LatLng(latLng.latitude, longitude);
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }
}
