package hellow.mobapde.com.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

import hellow.mobapde.com.helloworld.Adapters.RelatedAdventureAdapter;
import hellow.mobapde.com.helloworld.Beans.Adventure;
import hellow.mobapde.com.helloworld.Beans.Stop;
import hellow.mobapde.com.helloworld.Firebase.FirebaseHelper;

public class ViewRelAdvPopActivity extends AppCompatActivity {

    private static String TAG = "ViewRelAdvPopActivity";

    public static final String PENDING_ADVENTURE_KEY = "pending";

    private String stopKey;

    RecyclerView rvRelAdvList;
    RelatedAdventureAdapter relatedAdventureAdapter;
    ArrayList<Adventure> adventureList;

    Button btnRelAdvGoing;
    Button btnRelAdvCancel;

    FirebaseHelper firebaseHelper;

    private boolean isAdventureSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rel_adv);

        firebaseHelper = new FirebaseHelper();

        stopKey = getIntent().getStringExtra(MapsActivity.SELECT_STOP_KEY);

        initRelAdvDummyData();

        createContentView();
        initListeners();

    }

    public void createContentView(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8), (int)(height*0.8));

        btnRelAdvCancel = (Button) findViewById(R.id.btn_rel_adv_cancel);

        btnRelAdvGoing = (Button) findViewById(R.id.btn_rel_adv_going);

        rvRelAdvList = (RecyclerView) findViewById(R.id.rv_rel_adv_list);
        rvRelAdvList.setLayoutManager(new LinearLayoutManager(
                getBaseContext(),
                LinearLayoutManager.VERTICAL,
                false
        ));
        initRelAdvList();



    }

    public void initListeners(){
        btnRelAdvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnRelAdvGoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultingIntent = new Intent();
                resultingIntent.putExtra("selected", true);


                if ( relatedAdventureAdapter.getSelectedIndex() > -1 ) {
                    resultingIntent.putExtra(PENDING_ADVENTURE_KEY,
                            adventureList.get(relatedAdventureAdapter.getSelectedIndex()).getKey());
                }

                setResult(Activity.RESULT_OK, resultingIntent);
                finish();
            }
        });
    }

    public void initRelAdvDummyData() {
        adventureList = new ArrayList<>();
        /*adventureList.add(new Adventure("South Adventure",
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
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));*/

        DatabaseReference adventureReference = firebaseHelper.getAdventureReference();

        adventureReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Adventure> adventures = new ArrayList<Adventure>();

                for (DataSnapshot adventure : dataSnapshot.getChildren()) {
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
                        if (keys[j].equals(stopKey)) {
                            adventureList.add(adventures.get(i));
                            relatedAdventureAdapter.notifyItemInserted(adventureList.size());
                            j = currentAdventure.getNumberOfStops();
                        }
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void initRelAdvList(){
        relatedAdventureAdapter = new RelatedAdventureAdapter(adventureList);
        rvRelAdvList.setAdapter(relatedAdventureAdapter);
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
