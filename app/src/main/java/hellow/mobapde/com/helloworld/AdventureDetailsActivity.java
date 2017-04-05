package hellow.mobapde.com.helloworld;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileInputStream;

public class AdventureDetailsActivity extends AppCompatActivity {
    Button btnGo;
    Button btnCancel;
    TextView tvAdventureViewedTitle;
    TextView tvAdventureViewedDetails;
    //ImageView ivAdventureViewedPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure_details);

        createMapFragment();


    }

    private void createMapFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.map_container, new MapDetailsFragment());
        fragmentTransaction.commit();
    }


}
