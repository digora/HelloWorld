package hellow.mobapde.com.helloworld.Wrapper;

import android.graphics.Color;
import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;

import java.util.HashMap;
import java.util.List;

/**
 * Created by patricktobias on 14/03/2017.
 */

public class PathWrapper {

    private LatLng origin;
    private LatLng destination;

    private Polyline polyline;

    public String url;
    public int pathColor; // hex 0xFF000000
    public int lineWidth;

    public String distance;

    public List<List<HashMap<String, String>>> routes; // TO BE SET DURING ASYNCTASK

    public PathWrapper(int color, int lineWidth) {
        url = null;
        pathColor = color;
        this.lineWidth = lineWidth;
    }

    public PathWrapper(LatLng origin, LatLng destination, int color, int lineWidth) {

        this.origin = origin;
        this.destination = destination;

        url = null;
        pathColor = color;
        this.lineWidth = lineWidth;
    }

    public LatLng getOrigin() {
        return origin;
    }

    public void setOrigin(LatLng origin) {
        this.origin = origin;
    }

    public LatLng getDestination() {
        return destination;
    }

    public void setDestination(LatLng destination) {
        this.destination = destination;
    }

    public Polyline getPolyline() {
        return polyline;
    }

    public void setPolyline(Polyline polyline) {
        this.polyline = polyline;
    }

    public boolean isAssociatedWithOrigin (LatLng latLng) {
        return (origin.latitude == latLng.latitude) && (origin.longitude == latLng.longitude);
    }

    public boolean isAssociatedWithDestination (LatLng latLng) {
        return (destination.latitude == latLng.latitude) && (destination.longitude == latLng.longitude);
    }

    public void removePolyline() {
        polyline.remove();
    }

    public float getDistance() {
        float totalDistance = 0;

        for(int i = 1; i < polyline.getPoints().size(); i++) {
            Location currLocation = new Location("this");
            currLocation.setLatitude(polyline.getPoints().get(i).latitude);
            currLocation.setLongitude(polyline.getPoints().get(i).longitude);

            Location lastLocation = new Location("this");
            lastLocation.setLatitude(polyline.getPoints().get(i-1).latitude);
            lastLocation.setLongitude(polyline.getPoints().get(i-1).longitude);

            totalDistance += lastLocation.distanceTo(currLocation);
        }

        return totalDistance;
    }
}
