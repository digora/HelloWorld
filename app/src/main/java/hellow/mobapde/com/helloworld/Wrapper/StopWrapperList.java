package hellow.mobapde.com.helloworld.Wrapper;

import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

import hellow.mobapde.com.helloworld.Beans.Stop;

/**
 * Created by patricktobias on 28/03/2017.
 */

public class StopWrapperList {

    private ArrayList<StopWrapper> stopWrappers;

    public StopWrapperList() {
        stopWrappers = new ArrayList<StopWrapper>();
    }

    public StopWrapperList(ArrayList<StopWrapper> stopWrappers) {
        this.stopWrappers = stopWrappers;
    }

    public void add (StopWrapper stopWrapper) {
        stopWrappers.add(stopWrapper);
    }

    public StopWrapper get (int index) {
        return stopWrappers.get(index);
    }

    public int size () {
        return stopWrappers.size();
    }

    public StopWrapper remove (int index) {
        return stopWrappers.remove(index);
    }

    public boolean removeMarkerOfAssociatedStop (Stop stop) {
        for (int i = 0; i < stopWrappers.size(); i++) {
            if (stopWrappers.get(i).isAssociated(stop)) {
                stopWrappers.get(i).removeMarkerFromMap();

                return true;
            }
        }

        return false;
    }

    public boolean removeCircleOfAssociatedStop (Stop stop) {
        for (int i = 0; i < stopWrappers.size(); i++) {
            if (stopWrappers.get(i).isAssociated(stop)) {
                stopWrappers.get(i).removeCircleFromMap();

                return true;
            }
        }

        return false;
    }

    public boolean removeMarkerAndCircleOfAssociatedStop (Stop stop) {
        return removeMarkerOfAssociatedStop(stop) && removeCircleOfAssociatedStop(stop);
    }

    public boolean hideInfoWindowOf (Stop stop) {
        for (int i = 0; i < stopWrappers.size(); i++) {
            if (stopWrappers.get(i).isAssociated(stop)) {
                stopWrappers.get(i).hideInfoWindow();

                return true;
            }
        }

        return false;
    }

    public void hideAllInfoWindows () {
        for (int i = 0; i < stopWrappers.size(); i++) {
            stopWrappers.get(i).hideInfoWindow();
        }
    }

    public boolean showInfoWindowOf (Stop stop) {
        for (int i = 0; i < stopWrappers.size(); i++) {
            if (stopWrappers.get(i).isAssociated(stop)) {
                stopWrappers.get(i).showInfoWindow();

                return true;
            }
        }

        return false;
    }

    public void showAllInfoWindows () {
        for (int i = 0; i < stopWrappers.size(); i++) {
            stopWrappers.get(i).showInfoWindow();
        }
    }

    public Stop getAssociatedStop (Marker marker) {
        for (int i = 0; i < stopWrappers.size(); i++) {
            if (stopWrappers.get(i).isAssociated(marker))
                return stopWrappers.get(i).getStop();
        }

        return null;
    }

    public Stop getAssociatedStop (Stop stop) {
        for (int i = 0; i < stopWrappers.size(); i++) {
            if (stopWrappers.get(i).isAssociated(stop))
                return stopWrappers.get(i).getStop();
        }

        return null;
    }

    public StopWrapper getAssociatedStopWrapper (Marker marker) {
        for (int i = 0; i < stopWrappers.size(); i++) {
            if (stopWrappers.get(i).isAssociated(marker))
                return stopWrappers.get(i);
        }

        return null;
    }
}
