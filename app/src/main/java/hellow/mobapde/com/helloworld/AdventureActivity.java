package hellow.mobapde.com.helloworld;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import hellow.mobapde.com.helloworld.Adapters.NearbyAdventureAdapter;
import hellow.mobapde.com.helloworld.Adapters.CustomSwipeAdapter;
import hellow.mobapde.com.helloworld.Adapters.LatestAdventureAdapter;
import hellow.mobapde.com.helloworld.Adapters.TopAdventureAdapter;
import hellow.mobapde.com.helloworld.Beans.Adventure;
import hellow.mobapde.com.helloworld.Firebase.FirebaseHelper;

public class AdventureActivity extends AppCompatActivity {

    public static final String ADVENTURE_KEY = "ad_key";

    /*Testes*/
    //ViewPager vpFeaturedList;
    //CustomSwipeAdapter customSwipeAdapter;
    RecyclerView rvLatestList;
    RecyclerView rvTopList;
    RecyclerView rvClosestList;

    ArrayList<Adventure> latestAdventureList;
    ArrayList<Adventure> topAdventureList;
    ArrayList<Adventure> closestAdventureList;

    NearbyAdventureAdapter closestAdventureAdapter;
    LatestAdventureAdapter latestAdventureAdapter;
    TopAdventureAdapter topAdventureAdapter;

    LinearLayout llCatalogContainer;

    FirebaseHelper firebaseHelper;
    Location currentLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure);
        setTitle("Adventure Catalog");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseHelper = new FirebaseHelper();

        currentLocation = getIntent().getParcelableExtra(MapsActivity.LOCATION_KEY);

        createContentView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void createContentView(){
        /*vpFeaturedList = (ViewPager) findViewById(R.id.vp_featured_list);
        customSwipeAdapter = new CustomSwipeAdapter(this);
        vpFeaturedList.setAdapter(customSwipeAdapter);*/

        initDummyLatestList();
        initDummyTopList();
        initDummyClosestList();

        rvLatestList = (RecyclerView) findViewById(R.id.rv_latest_list);
        rvLatestList.setLayoutManager(new LinearLayoutManager(
                getBaseContext(),
                LinearLayoutManager.HORIZONTAL,
                false
        ));


        rvTopList = (RecyclerView) findViewById(R.id.rv_top_list);
        rvTopList.setLayoutManager(new LinearLayoutManager(
                getBaseContext(),
                LinearLayoutManager.HORIZONTAL,
                false
        ));


        rvClosestList = (RecyclerView) findViewById(R.id.rv_closest_list);
        rvClosestList.setLayoutManager(new LinearLayoutManager(
                getBaseContext(),
                LinearLayoutManager.HORIZONTAL,
                false
        ));
        initClosestList();

        llCatalogContainer = (LinearLayout) findViewById(R.id.ll_catalog_container);

    }

    public void initTopList(){


    }

    public void initLatestList(){

    }

    public void initClosestList(){

        closestAdventureAdapter = new NearbyAdventureAdapter(closestAdventureList);
        closestAdventureAdapter.setOnAdventureClickListener(new NearbyAdventureAdapter.OnAdventureClickListener() {
            @Override
            public void onAdventureClick(View view, Adventure a) {
                Intent adventurePageIntent = new Intent(getBaseContext(), AdventureDetailsActivity.class);
                adventurePageIntent.putExtra(ADVENTURE_KEY, a.getKey());

                startActivity(adventurePageIntent);
            }
        });

        rvClosestList.setAdapter(closestAdventureAdapter);

        DatabaseReference adventureReference = firebaseHelper.getAdventureReference();

        adventureReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Adventure> adventures = new ArrayList<Adventure>();

                for (DataSnapshot adventure: dataSnapshot.getChildren()) {
                    Adventure retrievedAdventure = adventure.getValue(Adventure.class);
                    Log.i("retrieved adventure", retrievedAdventure.getKey());

                    initBitmapDifficulty(retrievedAdventure);

                    adventures.add(retrievedAdventure);
                }

                for (int i = 0; i < adventures.size(); i++) {

                    Adventure currentAdventure = adventures.get(i);

                    Object[] objects = currentAdventure.getStops().keySet().toArray();

                    String[] keys = Arrays.copyOf(objects, objects.length, String[].class);

                    for (int j = 0; j < currentAdventure.getNumberOfStops(); j++) {
                        Location locationOfStop = new Location("null");
                        locationOfStop.setLatitude(currentAdventure.getStop(keys[j]).getLatitude());
                        locationOfStop.setLongitude(currentAdventure.getStop(keys[j]).getLongitude());

                        if (currentLocation.distanceTo(locationOfStop) <= MapsActivity.NEARBY_METERS) {
                            closestAdventureList.add(currentAdventure);
                            closestAdventureAdapter.notifyItemInserted(closestAdventureList.size());
                            break;
                        }
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void initDummyLatestList(){
        latestAdventureList = new ArrayList<>();
        latestAdventureList.add(new Adventure("South Adventure",
                "Venture the south.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        latestAdventureList.add(new Adventure("East Adventure",
                "Venture the east.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        latestAdventureList.add(new Adventure("North Adventure",
                "Venture the north.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        latestAdventureList.add(new Adventure("North Adventure",
                "Venture the north.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        latestAdventureList.add(new Adventure("North Adventure",
                "Venture the north.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        latestAdventureList.add(new Adventure("North Adventure",
                "Venture the north.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
    }

    public void initDummyClosestList(){
        closestAdventureList = new ArrayList<>();
        /*closestAdventureList.add(new Adventure("South Adventure",
                "Venture the south.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        closestAdventureList.add(new Adventure("East Adventure",
                "Venture the east.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        closestAdventureList.add(new Adventure("North Adventure",
                "Venture the north.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        closestAdventureList.add(new Adventure("North Adventure",
                "Venture the north.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        closestAdventureList.add(new Adventure("North Adventure",
                "Venture the north.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        closestAdventureList.add(new Adventure("North Adventure",
                "Venture the north.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));*/


    }

    public void initDummyTopList(){
        topAdventureList = new ArrayList<>();
        topAdventureList.add(new Adventure("South Adventure",
                "Venture the south.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        topAdventureList.add(new Adventure("East Adventure",
                "Venture the east.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        topAdventureList.add(new Adventure("North Adventure",
                "Venture the north.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        topAdventureList.add(new Adventure("North Adventure",
                "Venture the north.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        topAdventureList.add(new Adventure("North Adventure",
                "Venture the north.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        topAdventureList.add(new Adventure("North Adventure",
                "Venture the north.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));


    }

    public void initBitmapDifficulty (Adventure adventure) {
        switch (adventure.getDifficulty()) {
            case Adventure.CASUAL_DIFFICULTY :
                adventure.setPicture(BitmapFactory.decodeResource(getResources(), R.drawable.icon_casual));
                break;
            case Adventure.INTERMEDIATE_DIFFICULTY :
                adventure.setPicture(BitmapFactory.decodeResource(getResources(), R.drawable.icon_intermediate));
                break;
            case Adventure.ADVENTUROUS_DIFFICULTY :
                adventure.setPicture(BitmapFactory.decodeResource(getResources(), R.drawable.icon_adventurous));
                break;
            default:

        }
    }
}
