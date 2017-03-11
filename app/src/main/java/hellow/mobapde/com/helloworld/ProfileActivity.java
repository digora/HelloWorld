package hellow.mobapde.com.helloworld;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import hellow.mobapde.com.helloworld.Beans.Adventure;
import hellow.mobapde.com.helloworld.Beans.Profile;
import hellow.mobapde.com.helloworld.Beans.Stamp;

public class ProfileActivity extends AppCompatActivity {

    Profile your_profile;
    RecyclerView rvStamps;
    RecyclerView rvAdventures;
    ArrayList<Stamp> stampList;
    ArrayList<Adventure> adventureList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        createContentView();

        /* DELETE Temporary Data */
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

    }

    private void createContentView(){

        rvStamps = (RecyclerView) findViewById(R.id.rv_stamps);
        rvStamps.setLayoutManager(new LinearLayoutManager(
                getBaseContext(),
                LinearLayoutManager.HORIZONTAL,
                false
        ));

        rvAdventures = (RecyclerView) findViewById(R.id.rv_adventures);
        rvAdventures.setLayoutManager(new LinearLayoutManager(
                getBaseContext(),
                LinearLayoutManager.HORIZONTAL,
                false
        ));
    }
}
