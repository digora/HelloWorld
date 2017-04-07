package hellow.mobapde.com.helloworld;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.io.FileInputStream;
import java.util.Arrays;

import hellow.mobapde.com.helloworld.Beans.Adventure;
import hellow.mobapde.com.helloworld.Beans.Stop;
import hellow.mobapde.com.helloworld.Firebase.FirebaseHelper;
import hellow.mobapde.com.helloworld.Settings.CircleSettings;
import hellow.mobapde.com.helloworld.Settings.MarkerSettings;
import hellow.mobapde.com.helloworld.Wrapper.StopWrapper;
import hellow.mobapde.com.helloworld.Wrapper.StopWrapperList;

public class AdventureDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {
    Button btnGo;
    Button btnCancel;
    TextView tvAdventureViewedTitle;
    TextView tvAdventureViewedDetails;
    //ImageView ivAdventureViewedPicture;

    GoogleMap mMap;

    StopWrapperList stopWrappers;

    String selectedAdventureKey;

    FirebaseHelper firebaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure_details);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_detail);
        supportMapFragment.getMapAsync(this);

        stopWrappers = new StopWrapperList();

        firebaseHelper = new FirebaseHelper();

        selectedAdventureKey = getIntent().getStringExtra(AdventureActivity.ADVENTURE_KEY);

        createContentView();
    }


    private void createContentView(){

        Intent collectIntent = getIntent();

        btnGo = (Button) findViewById(R.id.btn_go);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                intent.putExtra(AdventureActivity.ADVENTURE_KEY, selectedAdventureKey);

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                String userKey =  sharedPreferences.getString(NoNameActivity.USER_KEY, "null");

                firebaseHelper.updateProfilesCurrAdventure(userKey, selectedAdventureKey);

                startActivity(intent);
            }
        });

        btnCancel = (Button) findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvAdventureViewedTitle = (TextView) findViewById(R.id.tv_adventure_viewed_title);
        //tvAdventureViewedTitle.setText(collectIntent.getStringExtra("aName"));

        tvAdventureViewedDetails = (TextView) findViewById(R.id.tv_adventure_viewed_details);
        //tvAdventureViewedDetails.setText(collectIntent.getStringExtra("aDetails"));

        /*ivAdventureViewedPicture = (ImageView) findViewById(R.id.iv_adventure_viewed_picture);
        ivAdventureViewedPicture.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.app_icon));*/

        DatabaseReference databaseReference = firebaseHelper.getAdventureReference(selectedAdventureKey);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Adventure adventure = dataSnapshot.getValue(Adventure.class);

                tvAdventureViewedTitle.setText(adventure.getName());
                tvAdventureViewedDetails.setText(adventure.getDetails());

                addAdventureToMap(adventure, mMap);
                viewAllMarkersInMap(stopWrappers, mMap);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.getUiSettings().setAllGesturesEnabled(false);

    }

    private void viewAllMarkersInMap(StopWrapperList stopWrappers, GoogleMap map) {

        Marker[] markers = new Marker[stopWrappers.size()];

        for (int i = 0; i < stopWrappers.size(); i++) {
            markers[i] = stopWrappers.get(i).getMarker();
        }

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Marker marker : markers) {
            builder.include(marker.getPosition());
        }

        LatLngBounds bounds = builder.build();

        int padding = 400; // offset from edges of the map in pixels

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding);

        map.moveCamera(cu);
    }

    private void addAdventureToMap(Adventure adventure, GoogleMap map) {

        initMarkersAndCircles(adventure);

        Object[] objects = adventure.getStops().keySet().toArray();

        String[] keys = Arrays.copyOf(objects, objects.length, String[].class);

        for (int i = 0; i < adventure.getNumberOfStops(); i++) {
            stopWrappers.add(addStopToMap(adventure.getStop(keys[i]), map));
        }
    }

    private void initMarkersAndCircles (Adventure adventure) {
        Object[] objects = adventure.getStops().keySet().toArray();

        String[] keys = Arrays.copyOf(objects, objects.length, String[].class);

        for (int i = 0; i < adventure.getNumberOfStops(); i++) {

            Stop stop = adventure.getStop(keys[i]);

            stop.setMarkerOptions(new MarkerOptions()
                    .position(stop.getLatLng())
                    .title(stop.getDescription())
                    .icon(MarkerSettings.getActivePin()));

            stop.setCircleOptions(new CircleOptions()
                    .center(stop.getLatLng())
                    .fillColor(CircleSettings.getFillColor())
                    .strokeColor(CircleSettings.getStrokeColor())
                    .radius(CircleSettings.getRadius()));
        }
    }

    private StopWrapper addStopToMap(Stop stop, GoogleMap map) {

        for (int i = 0; i < stopWrappers.size(); i++) { // to prevent multiple

            Stop currentStop = stopWrappers.get(i).getStop();

            if ( (currentStop.getLatLng().latitude == stop.getLatLng().latitude) &&
                    (currentStop.getLatLng().longitude == stop.getLatLng().longitude))
                return null;
        }

        Marker marker = map.addMarker(stop.getMarkerOptions());
        Circle circle = map.addCircle(stop.getCircleOptions());

        return new StopWrapper(stop, marker, circle);
    }
}
