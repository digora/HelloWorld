package hellow.mobapde.com.helloworld;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

import hellow.mobapde.com.helloworld.Adapters.AdventureAdapter;
import hellow.mobapde.com.helloworld.Beans.Adventure;
import hellow.mobapde.com.helloworld.Beans.Profile;
import hellow.mobapde.com.helloworld.Firebase.FirebaseHelper;

public class AdventurePopActivity extends AppCompatActivity {

    private static final String TAG = "AdventurePopActivity";

    RecyclerView rvAdventures;
    ArrayList<Adventure> adventureList;
    AdventureAdapter adventureAdapter;
    TextView tvAdvPopBack;

    String userKey;

    FirebaseHelper firebaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure_pop);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        userKey =  sharedPreferences.getString(NoNameActivity.USER_KEY, "null");

        firebaseHelper = new FirebaseHelper();

        createContentView();
    }

    public void createContentView(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8), (int)(height*0.8));

        initDummyAdventureData();
        Log.d(TAG, "Adventure List instantiated with size: " + adventureList.size());


        rvAdventures = (RecyclerView) findViewById(R.id.rv_adventures);
        rvAdventures.setLayoutManager(new GridLayoutManager(
                getBaseContext(),
                4
                ));
        initAdventuresList();

        tvAdvPopBack = (TextView) findViewById(R.id.tv_adv_pop_back);
        tvAdvPopBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    /* DELETE Temporary Data */
    public void initDummyAdventureData(){
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

        DatabaseReference profileReference = firebaseHelper.getProfileReference(userKey);

        profileReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Profile profile = dataSnapshot.getValue(Profile.class);

                Object[] objects = profile.getAdventureLog().keySet().toArray();

                String[] keys = Arrays.copyOf(objects, objects.length, String[].class);

                for (String key : keys) {
                    DatabaseReference adventureReference = firebaseHelper.getAdventureReference(key);

                    adventureReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Adventure adventure = dataSnapshot.getValue(Adventure.class);

                            initBitmapDifficulty(adventure);

                            adventureList.add(adventure);
                            adventureAdapter.notifyItemInserted(adventureList.size());
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void initAdventuresList(){

        Log.d(TAG, "Assigning adapter to adventure list");

        adventureAdapter = new AdventureAdapter(adventureList);
        adventureAdapter.setOnAdventureClickListener(new AdventureAdapter.OnAdventureClickListener() {
            @Override
            public void onAdventureClick(View view, Adventure a) {
                Intent viewAdventureDetailIntent = new Intent(getBaseContext(), ViewAdventurePopActivity.class);
                viewAdventureDetailIntent.putExtra("aName", a.getName());
                viewAdventureDetailIntent.putExtra("aDifficulty", a.getDifficulty());

                startActivity(viewAdventureDetailIntent);

            }
        });
        rvAdventures.setAdapter(adventureAdapter);

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
