package hellow.mobapde.com.helloworld;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

import hellow.mobapde.com.helloworld.Adapters.AdventureAdapter;
import hellow.mobapde.com.helloworld.Adapters.StampAdapter;
import hellow.mobapde.com.helloworld.Beans.Adventure;
import hellow.mobapde.com.helloworld.Beans.Profile;
import hellow.mobapde.com.helloworld.Beans.Stamp;
import hellow.mobapde.com.helloworld.Firebase.FirebaseHelper;

public class ProfileActivity extends AppCompatActivity {

    Profile your_profile;
    private static final String TAG = "ProfileActivity";

    FloatingActionButton fBtnVaStamps;
    FloatingActionButton fBtnVaAdventures;

    TextView tvAdventurerName;
    TextView tvNumAdvCompleted;
    TextView tvNumStamps;

    Button btnProfileBack;

    FirebaseHelper firebaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //setTitle("Profile");

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseHelper = new FirebaseHelper();

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

        Log.d(TAG, "Creating content view");

        tvAdventurerName = (TextView) findViewById(R.id.tv_adventurer_name);
        tvNumAdvCompleted = (TextView) findViewById(R.id.tv_number_adv_completed);
        tvNumStamps = (TextView) findViewById(R.id.tv_number_stamps);

        btnProfileBack = (Button) findViewById(R.id.btn_profile_back);

        fBtnVaStamps = (FloatingActionButton) findViewById(R.id.fbtn_va_stamps);
        fBtnVaAdventures = (FloatingActionButton) findViewById(R.id.fbtn_va_adventures);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String userKey =  sharedPreferences.getString(NoNameActivity.USER_KEY, "null");
        String username =  sharedPreferences.getString(NoNameActivity.USERNAME, "null");

        tvAdventurerName.setText(username);

        Log.i("current key", userKey);

        DatabaseReference profileReference = firebaseHelper.getProfileReference(userKey);
        profileReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Profile retrievedProfile = dataSnapshot.getValue(Profile.class);

                tvAdventurerName.setText(retrievedProfile.getName());

                if (retrievedProfile.getAdventureLog() == null)
                    tvNumAdvCompleted.setText("0");
                else {
                    tvNumAdvCompleted.setText(retrievedProfile.getAdventureLog().size() + "");
                }

                if (retrievedProfile.getStamps() == null)
                    tvNumStamps.setText("0");
                else {
                    tvNumStamps.setText(retrievedProfile.getStamps().size() + "");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //tvAdventurerName.setText(sharedPreferences.getString(NoNameActivity.USERNAME, "JuanDefault"));

        initListeners();

    }

    public void initListeners(){
        fBtnVaStamps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), StampPopActivity.class));
            }
        });

        fBtnVaAdventures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), AdventurePopActivity.class));
            }
        });

        btnProfileBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }






}
