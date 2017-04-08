package hellow.mobapde.com.helloworld.Settings;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;

import hellow.mobapde.com.helloworld.R;

/**
 * Created by patricktobias on 01/04/2017.
 */

public class MarkerSettings {

    public static BitmapDescriptor getActivePin () {
        return BitmapDescriptorFactory.fromResource(R.drawable.red_pin);
    }

    public static BitmapDescriptor getInactivePin () {
        return BitmapDescriptorFactory.fromResource(R.drawable.gray_pin);
    }

}
