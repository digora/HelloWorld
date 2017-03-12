package hellow.mobapde.com.helloworld;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hellow.mobapde.com.helloworld.Adapters.AdventureAdapter;
import hellow.mobapde.com.helloworld.Adapters.StampAdapter;
import hellow.mobapde.com.helloworld.Beans.Adventure;
import hellow.mobapde.com.helloworld.Beans.Profile;
import hellow.mobapde.com.helloworld.Beans.Stamp;

public class ProfileActivity extends AppCompatActivity {

    Profile your_profile;
    private static final String TAG = "ProfileActivity";
    RecyclerView rvStamps;
    RecyclerView rvAdventures;
    ArrayList<Stamp> stampList;
    ArrayList<Adventure> adventureList;

    TextView tvStampsSeeAll;
    TextView tvAdventuresSeeAll;
    AdventureAdapter adventureAdapter;
    StampAdapter stampAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        createContentView();

    }

    private void createContentView(){

        Log.d(TAG, "Creating content view");

        tvStampsSeeAll = (TextView) findViewById(R.id.tv_stamps_see_all);
        tvAdventuresSeeAll = (TextView) findViewById(R.id.tv_adventures_see_all);

        initListeners();

        initDummyAdventureData();
        initDummyStampData();

        Log.d(TAG, "Adventure List instantiated with size: " + adventureList.size());
        Log.d(TAG, "Stamp List instantiated with size: " + stampList.size());

        rvStamps = (RecyclerView) findViewById(R.id.rv_stamps);
        rvStamps.setLayoutManager(new LinearLayoutManager(
                getBaseContext(),
                LinearLayoutManager.HORIZONTAL,
                false
        ));
        initStampList();


        rvAdventures = (RecyclerView) findViewById(R.id.rv_adventures);
        rvAdventures.setLayoutManager(new LinearLayoutManager(
                getBaseContext(),
                LinearLayoutManager.HORIZONTAL,
                false
        ));
        initAdventuresList();
    }

    public void initListeners(){
        tvAdventuresSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getBaseContext(), "Adventure See all clicked", Toast.LENGTH_LONG).show();
            }
        });

        tvStampsSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getBaseContext(), "Stamps See all clicked", Toast.LENGTH_LONG).show();
            }
        });
    }

    /* DELETE Temporary Data */
    public void initDummyStampData(){

        stampList = new ArrayList<>();
        stampList.add(new Stamp("Casual Adventurer",
                "Completed a casual adventure.",
                "01/01/17",
                BitmapFactory.decodeResource(getResources(), R.drawable.red_stamp)));
        stampList.add(new Stamp("Hardcore Adventurer",
                "Completed a hardcore adventure.",
                "01/01/17",
                BitmapFactory.decodeResource(getResources(), R.drawable.red_stamp)));
        stampList.add(new Stamp("Quick Adventurer",
                "Completed a casual adventure in less than 1 hour.",
                "01/01/17",
                BitmapFactory.decodeResource(getResources(), R.drawable.red_stamp)));
        stampList.add(new Stamp("Quick Adventurer",
                "Completed a casual adventure in less than 1 hour.",
                "01/01/17",
                BitmapFactory.decodeResource(getResources(), R.drawable.red_stamp)));
        stampList.add(new Stamp("Quick Adventurer",
                "Completed a casual adventure in less than 1 hour.",
                "01/01/17",
                BitmapFactory.decodeResource(getResources(), R.drawable.red_stamp)));
        stampList.add(new Stamp("Quick Adventurer",
                "Completed a casual adventure in less than 1 hour.",
                "01/01/17",
                BitmapFactory.decodeResource(getResources(), R.drawable.red_stamp)));
        stampList.add(new Stamp("Quick Adventurer",
                "Completed a casual adventure in less than 1 hour.",
                "01/01/17",
                BitmapFactory.decodeResource(getResources(), R.drawable.red_stamp)));


    }

    public void initDummyAdventureData(){
        adventureList = new ArrayList<>();
        adventureList.add(new Adventure("South Adventure",
                "Venture the south.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        adventureList.add(new Adventure("East Adventure",
                "Venture the east.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        adventureList.add(new Adventure("North Adventure",
                "Venture the north.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        adventureList.add(new Adventure("North Adventure",
                "Venture the north.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        adventureList.add(new Adventure("North Adventure",
                "Venture the north.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        adventureList.add(new Adventure("North Adventure",
                "Venture the north.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));

    }

    public void initStampList(){

        Log.d(TAG, "Assigning adapter to stamp list");
        stampAdapter = new StampAdapter(stampList);
        rvStamps.setAdapter(stampAdapter);

    }

    public void initAdventuresList(){

        Log.d(TAG, "Assigning adapter to adventure list");

        adventureAdapter = new AdventureAdapter(adventureList);
        rvAdventures.setAdapter(adventureAdapter);

    }
}
