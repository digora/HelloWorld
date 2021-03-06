package hellow.mobapde.com.helloworld;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

import hellow.mobapde.com.helloworld.Adapters.NearbyAdventureAdapter;
import hellow.mobapde.com.helloworld.Beans.Adventure;
import hellow.mobapde.com.helloworld.Firebase.FirebaseHelper;

public class AdventureActivity extends AppCompatActivity {

    public static final String ADVENTURE_KEY = "ad_key";

    /*Testes*/
    //ViewPager vpFeaturedList;
    //CustomSwipeAdapter customSwipeAdapter;
    RecyclerView rvCasualList;
    RecyclerView rvTopList;
    RecyclerView rvClosestList;

    ArrayList<Adventure> latestAdventureList;
    ArrayList<Adventure> casualAdventureList;
    ArrayList<Adventure> closestAdventureList;

    NearbyAdventureAdapter closestAdventureAdapter;
    NearbyAdventureAdapter latestAdventureAdapter;
    NearbyAdventureAdapter casualAdventureAdapter;

    LinearLayout llCatalogContainer;

    FirebaseHelper firebaseHelper;
    Location currentLocation;

    TextView tvMoreClosest;
    TextView tvMoreLatest;
    TextView tvMoreSimple;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure);
        setTitle("Adventure Catalog");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseHelper = new FirebaseHelper();

        currentLocation = getIntent().getParcelableExtra(MapsActivity.LOCATION_KEY);

        createContentView();

        initListeners();
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

        rvCasualList = (RecyclerView) findViewById(R.id.rv_latest_list);
        rvCasualList.setLayoutManager(new LinearLayoutManager(
                getBaseContext(),
                LinearLayoutManager.HORIZONTAL,
                false
        ));


        /*rvTopList = (RecyclerView) findViewById(R.id.rv_top_list);
        rvTopList.setLayoutManager(new LinearLayoutManager(
                getBaseContext(),
                LinearLayoutManager.HORIZONTAL,
                false
        ));*/


        rvClosestList = (RecyclerView) findViewById(R.id.rv_closest_list);
        rvClosestList.setLayoutManager(new LinearLayoutManager(
                getBaseContext(),
                LinearLayoutManager.HORIZONTAL,
                false
        ));
        initCasualList();
        initClosestList();

        llCatalogContainer = (LinearLayout) findViewById(R.id.ll_catalog_container);

        tvMoreClosest = (TextView) findViewById(R.id.tv_more_closest);

        //tvMoreLatest = (TextView) findViewById(R.id.tv_more_latest);

        tvMoreSimple = (TextView) findViewById(R.id.tv_more_simple);


    }

    private void initListeners(){
        tvMoreClosest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), MoreActivity.class);

                i.putExtra("type", "Nearby Adventures");

                startActivity(i);
            }
        });

        /*tvMoreLatest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), MoreActivity.class);

                i.putExtra("type", "Latest Adventures");

                startActivity(i);
            }
        });*/

        tvMoreSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), MoreActivity.class);

                i.putExtra("type", "Simple Adventures");

                startActivity(i);
            }
        });
    }

    public void initCasualList(){
        casualAdventureAdapter = new NearbyAdventureAdapter(casualAdventureList);
        casualAdventureAdapter.setOnAdventureClickListener(new NearbyAdventureAdapter.OnAdventureClickListener() {
            @Override
            public void onAdventureClick(View view, Adventure a) {
                Intent adventurePageIntent = new Intent(getBaseContext(), AdventureDetailsActivity.class);
                adventurePageIntent.putExtra(ADVENTURE_KEY, a.getKey());

                startActivity(adventurePageIntent);
            }
        });

        rvCasualList.setAdapter(casualAdventureAdapter);

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

                    if (currentAdventure.getDifficulty().equals(Adventure.CASUAL_DIFFICULTY)) {
                        casualAdventureList.add(currentAdventure);
                        casualAdventureAdapter.notifyItemInserted(casualAdventureList.size());
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
        casualAdventureList = new ArrayList<>();
        /*casualAdventureList.add(new Adventure("South Adventure",
                "Venture the south.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        casualAdventureList.add(new Adventure("East Adventure",
                "Venture the east.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        casualAdventureList.add(new Adventure("North Adventure",
                "Venture the north.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        casualAdventureList.add(new Adventure("North Adventure",
                "Venture the north.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        casualAdventureList.add(new Adventure("North Adventure",
                "Venture the north.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        casualAdventureList.add(new Adventure("North Adventure",
                "Venture the north.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));*/


    }

    public void initBitmapDifficulty(Adventure adventure) {
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
