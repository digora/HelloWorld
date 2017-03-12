package hellow.mobapde.com.helloworld;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import hellow.mobapde.com.helloworld.Beans.Adventure;
import hellow.mobapde.com.helloworld.Beans.Stop;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private GoogleMap mMap;
    private FloatingActionButton dashboardButton;

    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        googleApiClient.connect();

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

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

    }

    private void moveCameraToStop(Stop stop, GoogleMap map) {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(stop.getLatLng())
                .zoom(6) // TODO optimize to see all points (including your position)
                .bearing(20) // TODO optimize to see all points (including your position)
                .tilt(0)
                .build();

        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private void moveCameraToLocation(Location location, GoogleMap map) {
        LatLng targetLoc = new LatLng(location.getLatitude(), location.getLongitude());

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(targetLoc)
                .zoom(17) // TODO optimize to see all points (including your position)
                .bearing(20) // TODO optimize to see all points (including your position)
                .tilt(0)
                .build();

        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private void addAdventureToMap(Adventure adventure, GoogleMap map) {
        for (int i = 0; i < adventure.getNumberOfStops(); i++) {
            Log.i("Stop added", i + "");

            addStopToMap(adventure.getStop(i), map);
        }
    }

    private void addStopToMap(Stop stop, GoogleMap map) {
        map.addMarker(stop.getMarkerOptions());
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();

        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i("Location", "changed");

        moveCameraToLocation(location, mMap);

        if (googleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        }
    }

    /*protected synchronized void buildGoogleApiClient() {
        mGoogle
    }*/
}
