package hellow.mobapde.com.helloworld.Wrapper;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import hellow.mobapde.com.helloworld.Beans.Stop;

/**
 * Created by patricktobias on 28/03/2017.
 */

public class PathWrapperList {

    private ArrayList<PathWrapper> pathWrappers;

    public PathWrapperList() {
        pathWrappers = new ArrayList<PathWrapper>();
    }

    public PathWrapperList(ArrayList<PathWrapper> pathWrappers) {
        this.pathWrappers = pathWrappers;
    }

    public void add (PathWrapper pathWrapper) {
        pathWrappers.add(pathWrapper);
    }

    public PathWrapper get (int index) {
        return pathWrappers.get(index);
    }

    public int size () {
        return pathWrappers.size();
    }

    public PathWrapper remove (int index) {
        return pathWrappers.remove(index);
    }

    public boolean removePathAssociatedWithOrigin (LatLng latLng) {
        for (int i = 0; i < pathWrappers.size(); i++) {
            if (pathWrappers.get(i).isAssociatedWithOrigin(latLng)) {
                pathWrappers.get(i).removePolyline();

                return true;
            }
        }

        return false;
    }

    public boolean removePathAssociatedWithDestination (LatLng latLng) {
        for (int i = 0; i < pathWrappers.size(); i++) {
            if (pathWrappers.get(i).isAssociatedWithDestination(latLng)) {
                pathWrappers.get(i).removePolyline();

                return true;
            }
        }

        return false;
    }

}
