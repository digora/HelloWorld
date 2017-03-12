package hellow.mobapde.com.helloworld;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import hellow.mobapde.com.helloworld.Beans.Adventure;
import hellow.mobapde.com.helloworld.Beans.Stop;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FloatingActionButton dashboardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        dashboardButton = (FloatingActionButton) findViewById(R.id.btn_dashboard);

        dashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getBaseContext(), ProfileActivity.class);

                startActivity(intent);
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker ne ar Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        /*Adventure adventure = new Adventure(); // FOR TESTING

        Stop stop1 = new Stop();
        LatLng stop1Coord = new LatLng(-36, 157);
        stop1.setMarkerOptions(new MarkerOptions().position(stop1Coord).title("Stop 1"));

        Stop stop2 = new Stop();
        LatLng stop2Coord = new LatLng(-38, 157);
        stop2.setMarkerOptions(new MarkerOptions().position(stop2Coord).title("Stop 2"));

        Stop stop3 = new Stop();
        LatLng stop3Coord = new LatLng(-40, 157);
        stop3.setMarkerOptions(new MarkerOptions().position(stop3Coord).title("Stop 3 !!!"));

        adventure.addStop(stop1);
        adventure.addStop(stop2);
        adventure.addStop(stop3);

        addAdventureToMap(adventure, mMap);

        moveCameraToStop (stop1, mMap);*/

    }

    private void moveCameraToStop (Stop stop, GoogleMap map) {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(stop.getLatLng())
                .zoom(6) // TODO optimize to see all points (including your position)
                .bearing(20) // TODO optimize to see all points (including your position)
                .tilt(0)
                .build();

        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private void addAdventureToMap (Adventure adventure, GoogleMap map) {
        for (int i = 0; i < adventure.getNumberOfStops(); i++) {
            Log.i("Stop added", i+"");

            addStopToMap(adventure.getStop(i), map);
        }
    }

    private void addStopToMap (Stop stop, GoogleMap map) {
        map.addMarker(stop.getMarkerOptions());
    }
}
