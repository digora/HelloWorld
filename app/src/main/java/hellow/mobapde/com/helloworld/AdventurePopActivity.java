package hellow.mobapde.com.helloworld;

import android.graphics.BitmapFactory;
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

import org.w3c.dom.Text;

import java.util.ArrayList;

import hellow.mobapde.com.helloworld.Adapters.AdventureAdapter;
import hellow.mobapde.com.helloworld.Beans.Adventure;

public class AdventurePopActivity extends AppCompatActivity {

    private static final String TAG = "AdventurePopActivity";

    RecyclerView rvAdventures;
    ArrayList<Adventure> adventureList;
    AdventureAdapter adventureAdapter;
    TextView tvAdvPopBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure_pop);

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

    public void initAdventuresList(){

        Log.d(TAG, "Assigning adapter to adventure list");

        adventureAdapter = new AdventureAdapter(adventureList);
        rvAdventures.setAdapter(adventureAdapter);

    }
}
