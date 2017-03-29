package hellow.mobapde.com.helloworld;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.util.ArrayList;

import hellow.mobapde.com.helloworld.Adapters.AdventureAdapter;
import hellow.mobapde.com.helloworld.Adapters.StampAdapter;
import hellow.mobapde.com.helloworld.Beans.Adventure;
import hellow.mobapde.com.helloworld.Beans.Profile;
import hellow.mobapde.com.helloworld.Beans.Stamp;

public class ProfileActivity extends AppCompatActivity {

    Profile your_profile;
    private static final String TAG = "ProfileActivity";

    FloatingActionButton fBtnVaStamps;
    FloatingActionButton fBtnVaAdventures;

    Button btnProfileBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //setTitle("Profile");

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        createContentView();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("/profiles/1/name");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("Fyrebass", dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Log.i("Reference", databaseReference.toString());

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

        btnProfileBack = (Button) findViewById(R.id.btn_profile_back);

        fBtnVaStamps = (FloatingActionButton) findViewById(R.id.fbtn_va_stamps);
        fBtnVaAdventures = (FloatingActionButton) findViewById(R.id.fbtn_va_adventures);

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
