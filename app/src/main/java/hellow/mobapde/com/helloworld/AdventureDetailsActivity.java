package hellow.mobapde.com.helloworld;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileInputStream;

public class AdventureDetailsActivity extends AppCompatActivity implements OnMapReadyCallback{
    Button btnGo;
    Button btnCancel;
    TextView tvAdventureViewedTitle;
    TextView tvAdventureViewedDetails;
    //ImageView ivAdventureViewedPicture;

    GoogleMap mMap;

    String selectedAdventureKey;

    FirebaseHelper firebaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure_details);

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
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /*Bitmap bmp = null;
        String filename = collectIntent.getStringExtra("aPicture");
        try{
            FileInputStream fis = openFileInput(filename);
            bmp = BitmapFactory.decodeStream(fis);
            fis.close();

            ivAdventureViewedPicture.setImageBitmap(bmp);
        }catch(Exception e){
            e.printStackTrace();
        }*/

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
