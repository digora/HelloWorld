package hellow.mobapde.com.helloworld;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hellow.mobapde.com.helloworld.Converter.MapToArrayListConverter;
import hellow.mobapde.com.helloworld.Beans.Adventure;
import hellow.mobapde.com.helloworld.Beans.Stop;
import hellow.mobapde.com.helloworld.Firebase.FirebaseHelper;
import hellow.mobapde.com.helloworld.GoogleMapParser.DataParser;
import hellow.mobapde.com.helloworld.Wrapper.PathWrapper;
import hellow.mobapde.com.helloworld.Wrapper.PathWrapperList;
import hellow.mobapde.com.helloworld.Wrapper.StopWrapper;
import hellow.mobapde.com.helloworld.Wrapper.StopWrapperList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private GoogleMap mMap;
    private FloatingActionButton dashboardButton;
    private Button btnViewAdventures;

    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;

    private Adventure currentAdventure;
    private Stop targetStop;
    private PathWrapper pathToTargetStop;

    private StopWrapperList stopWrappers;
    private PathWrapperList pathWrappers;

    private TextView tvCurrentAdventureName;

    private Location currentLocation;

    private FirebaseHelper firebaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        firebaseHelper = new FirebaseHelper();

        stopWrappers = new StopWrapperList();

        tvCurrentAdventureName = (TextView) findViewById(R.id.tv_current_adventure_title);

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
        btnViewAdventures = (Button) findViewById(R.id.btn_view_adventures);

        initListeners();

    }

    public void initListeners(){
        dashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getBaseContext(), ProfileActivity.class);

                startActivity(intent);
            }
        });

        btnViewAdventures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewAdventureIntent = new Intent();
                viewAdventureIntent.setClass(getBaseContext(), AdventureActivity.class);

                startActivity(viewAdventureIntent);
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

        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        mMap.getUiSettings().setCompassEnabled(false);
        mMap.getUiSettings().setMapToolbarEnabled(false);

        Adventure adventure = new Adventure(); // FOR TESTING

        adventure.setName("A Trip to the Rice Fields");

        Stop stop1 = new Stop();
        LatLng stop1Coord = new LatLng(14.4671549, 121.0084754);
        stop1.setMarkerOptions(new MarkerOptions()
                .position(stop1Coord)
                .title("House"));
        stop1.setCircleOptions(new CircleOptions()
                .center(stop1Coord)
                .fillColor(0x66888888)
                .strokeColor(Color.DKGRAY)
                .radius(20));

        targetStop = stop1;

        Stop stop2 = new Stop();
        LatLng stop2Coord = new LatLng(14.4662063, 121.0098848);
        stop2.setMarkerOptions(new MarkerOptions()
                .position(stop2Coord)
                .title("Japanese Resto"));
        stop2.setCircleOptions(new CircleOptions()
                .center(stop2Coord)
                .fillColor(0x66888888)
                .strokeColor(Color.DKGRAY)
                .radius(20));

        Stop stop3 = new Stop();
        LatLng stop3Coord = new LatLng(14.583308949994862, 121.05645965784788);
        stop3.setMarkerOptions(new MarkerOptions()
                .position(stop3Coord)
                .title("Mega Mol"));

        adventure.addStop("testStop1", stop1);
        adventure.addStop("testStop2", stop2);

        /*Adventure adventure = firebaseHelper.getAdventure("-Kg7iwO2x-7kwHkgmEbQ"); // sample

        while (!firebaseHelper.isInstantiated())
            adventure = firebaseHelper.getAdventure("-Kg7iwO2x-7kwHkgmEbQ"); // sample

        Log.i("Retrieved adventure", adventure.getName());*/

        //Log.i("Retrieved adventure", adventure.getKey());
        //Log.i("Retrieved stops in ad", adventure.getNumberOfStops() + "");

        setCurrentAdventure(adventure);
        addAdventureToMap(adventure, mMap, new PathWrapper(0x66FF0000, 12));

        moveCameraToStop (targetStop, mMap);

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

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Log.i("Clicked on", latLng.toString());
            }
        });
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

    private void addAdventureToMap(Adventure adventure, GoogleMap map, PathWrapper pathWrapperSettings) {

        ArrayList<Stop> stopsOfAdventure;

        stopsOfAdventure = MapToArrayListConverter.convertMapToStopArray(adventure.getStops());

        for (int i = 0; i < stopsOfAdventure.size(); i++) {
            stopWrappers.add(addStopToMap(stopsOfAdventure.get(i), map));
        }

        // START OF ROUTING POINT TO POINT
        int i = 0;
        while (i < stopsOfAdventure.size() - 1) {
            // Getting URL to the Google Directions API

            PathWrapper pathWrapperSettingsForURL =
                    new PathWrapper(stopsOfAdventure.get(i).getLatLng(),
                            stopsOfAdventure.get(i+1).getLatLng(),
                            pathWrapperSettings.pathColor,
                            pathWrapperSettings.lineWidth);

            pathWrappers.add(pathWrapperSettingsForURL);

            String url = getUrl(stopsOfAdventure.get(i++).getLatLng(), stopsOfAdventure.get(i).getLatLng());

            Log.i("Generated URL", url);

            pathWrapperSettingsForURL.url = url;

            Log.i("SET URL", pathWrapperSettingsForURL.url);

            FetchUrl fetchUrl = new FetchUrl();

            // Start downloading json data from Google Directions API
            fetchUrl.execute(pathWrapperSettingsForURL);
        }
        // END OF ROUTING POINT TO POINT
    }

    private StopWrapper addStopToMap(Stop stop, GoogleMap map) {
        Marker marker = map.addMarker(stop.getMarkerOptions());
        Circle circle = map.addCircle(stop.getCircleOptions());

        return new StopWrapper(stop, marker, circle);
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

    public void setCurrentAdventure(Adventure adventure) {
        this.currentAdventure = adventure;

        tvCurrentAdventureName.setText(adventure.getName());
    }

    public Adventure getCurrentAdventure() {
        return this.currentAdventure;
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

        currentLocation = location;

        if (targetStop != null) {
            if (locationIsInStop(location, targetStop)) { // if current location is already in the target stop
                Log.i("STATUS UPDATE", "you are at the target stop");
                stopWrappers.removeMarkerAndCircleOfAssociatedStop(targetStop); // TODO not remove but to color a different shade
            }
        }
    }


    private boolean locationIsInStop (Location location, Stop stop) {
        CircleOptions circleOptionsOfStop = stop.getCircleOptions();

        float[] distance = new float[2];

        Location.distanceBetween(location.getLatitude(),
                location.getLongitude(), circleOptionsOfStop.getCenter().latitude,
                circleOptionsOfStop.getCenter().longitude, distance);

        return distance[0] < circleOptionsOfStop.getRadius();
    }

    private String getUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;


        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;


        return url;
    }

    // Fetches data from url passed
    private class FetchUrl extends AsyncTask<PathWrapper, Void, PathWrapper> {

        @Override
        protected PathWrapper doInBackground(PathWrapper... pathWrapperSettings) {

            // For storing data from web service
            String data = "";

            try {
                // Fetching the data from web service
                data = downloadUrl(pathWrapperSettings[0].url);
                Log.d("Background Task data", data.toString());

                pathWrapperSettings[0].url = data;
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }

            Log.i("found path url", data);

            return pathWrapperSettings[0];
        }

        @Override
        protected void onPostExecute(PathWrapper result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);

        }
    }

    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();
            Log.d("downloadUrl", data.toString());
            br.close();

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    private class ParserTask extends AsyncTask<PathWrapper, Integer, PathWrapper> {

        // Parsing the data in non-ui thread
        @Override
        protected PathWrapper doInBackground(PathWrapper... pathWrapperSettings) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(pathWrapperSettings[0].url);
                Log.d("ParserTask",pathWrapperSettings[0].url.toString());
                DataParser parser = new DataParser();
                Log.d("ParserTask", parser.toString());

                // Starts parsing data
                routes = parser.parse(jObject);
                Log.d("ParserTask","Executing routes");
                Log.d("ParserTask",routes.toString());

                pathWrapperSettings[0].routes = routes;

            } catch (Exception e) {
                Log.d("ParserTask",e.toString());
                e.printStackTrace();
            }
            return pathWrapperSettings[0];
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(PathWrapper result) {
            ArrayList<LatLng> points;
            PolylineOptions lineOptions = null;

            // Traversing through all the routes
            for (int i = 0; i < result.routes.size(); i++) {
                points = new ArrayList<>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.routes.get(i);

                // Fetching all the points in i-th route
                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(result.lineWidth);
                lineOptions.color(result.pathColor);

                Log.d("onPostExecute","onPostExecute lineoptions decoded");

            }

            // Drawing polyline in the Google Map for the i-th route
            if(lineOptions != null) {
                mMap.addPolyline(lineOptions);
            }
            else {
                Log.d("onPostExecute","without Polylines drawn");
            }
        }
    }
}
