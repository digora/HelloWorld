package hellow.mobapde.com.helloworld;

import android.content.Intent;
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

import java.util.ArrayList;

import hellow.mobapde.com.helloworld.Adapters.StampAdapter;
import hellow.mobapde.com.helloworld.Beans.Stamp;

public class StampPopActivity extends AppCompatActivity {

    private static final String TAG = "StampPopActivity";

    RecyclerView rvStamps;
    ArrayList<Stamp> stampList;
    StampAdapter stampAdapter;
    TextView tvStampPopBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp_pop);

        createContentView();

    }

    public void createContentView(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8), (int)(height*0.8));

        initDummyStampData();

        Log.d(TAG, "Stamp List instantiated with size: " + stampList.size());

        rvStamps = (RecyclerView) findViewById(R.id.rv_stamps);
        rvStamps.setLayoutManager(new GridLayoutManager(
                getBaseContext(),
                4
                ));
        initStampList();

        tvStampPopBack = (TextView) findViewById(R.id.tv_stamp_pop_back);
        tvStampPopBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

    public void initStampList(){

        Log.d(TAG, "Assigning adapter to stamp list");
        stampAdapter = new StampAdapter(stampList);
        stampAdapter.setOnStampClickListener(new StampAdapter.OnStampClickListener() {
            @Override
            public void onStampClick(View view, Stamp s) {
                Intent viewStampDetailsIntent = new Intent(getBaseContext(), ViewStampPopActivity.class);
                viewStampDetailsIntent.putExtra("sName", s.getName());


                startActivity(viewStampDetailsIntent);
            }
        });
        rvStamps.setAdapter(stampAdapter);

    }


}
