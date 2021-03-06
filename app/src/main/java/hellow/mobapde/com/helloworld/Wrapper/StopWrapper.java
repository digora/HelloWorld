package hellow.mobapde.com.helloworld.Wrapper;

import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import hellow.mobapde.com.helloworld.Beans.Stop;
import hellow.mobapde.com.helloworld.Settings.CircleSettings;
import hellow.mobapde.com.helloworld.Settings.MarkerSettings;

/**
 * Created by patricktobias on 28/03/2017.
 */


public class StopWrapper {
    private String adventureKey;

    private Stop stop;
    private Marker marker;
    private Circle circle;

    public StopWrapper(Stop stop, Marker marker, Circle circle) {
        this.stop = stop;
        this.marker = marker;
        this.circle = circle;
    }

    public Stop getStop() {
        return stop;
    }

    public void setStop(Stop stop) {
        this.stop = stop;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public String getAdventureKey() {
        return adventureKey;
    }

    public void setAdventureKey(String adventureKey) {
        this.adventureKey = adventureKey;
    }

    public boolean isAssociated(Stop stop) {
        return this.stop.equals(stop);
    }

    public boolean isAssociated(Marker marker) {
        LatLng inputLatLng = marker.getPosition();
        LatLng thisLatLng = this.stop.getLatLng();

        return (thisLatLng.longitude == inputLatLng.longitude) && (thisLatLng.latitude == inputLatLng.latitude);
    }

    public void removeMarkerFromMap () {
        marker.remove();
    }

    public void removeCircleFromMap () {
        circle.remove();
    }

    public void removeMarkerAndCircle () {
        removeMarkerFromMap();
        removeCircleFromMap();
    }

    public void hideInfoWindow () {
        marker.hideInfoWindow();
    }

    public void showInfoWindow () {
        marker.showInfoWindow();
    }

    public void turnInactive () {
        getMarker().setIcon(MarkerSettings.getInactivePin());
        getCircle().setStrokeColor(CircleSettings.getInactiveStrokeColor());
    }

    public void turnActive () {
        getMarker().setIcon(MarkerSettings.getActivePin());
        getCircle().setStrokeColor(CircleSettings.getStrokeColor());
    }
}