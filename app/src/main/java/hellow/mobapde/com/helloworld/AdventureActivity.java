package hellow.mobapde.com.helloworld;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import hellow.mobapde.com.helloworld.Adapters.ClosestAdventureAdapter;
import hellow.mobapde.com.helloworld.Adapters.CustomSwipeAdapter;
import hellow.mobapde.com.helloworld.Adapters.LatestAdventureAdapter;
import hellow.mobapde.com.helloworld.Adapters.TopAdventureAdapter;
import hellow.mobapde.com.helloworld.Beans.Adventure;

public class AdventureActivity extends AppCompatActivity {

    ViewPager vpFeaturedList;
    CustomSwipeAdapter customSwipeAdapter;
    RecyclerView rvLatestList;
    RecyclerView rvTopList;
    RecyclerView rvClosestList;

    ArrayList<Adventure> latestAdventureList;
    ArrayList<Adventure> topAdventureList;
    ArrayList<Adventure> closestAdventureList;

    ClosestAdventureAdapter closestAdventureAdapter;
    LatestAdventureAdapter latestAdventureAdapter;
    TopAdventureAdapter topAdventureAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure);

        createContentView();
    }

    private void createContentView(){
        vpFeaturedList = (ViewPager) findViewById(R.id.vp_featured_list);
        customSwipeAdapter = new CustomSwipeAdapter(this);
        vpFeaturedList.setAdapter(customSwipeAdapter);

        initDummyLatestList();
        initDummyTopList();
        initDummyClosestList();

        rvLatestList = (RecyclerView) findViewById(R.id.rv_latest_list);
        rvLatestList.setLayoutManager(new LinearLayoutManager(
                getBaseContext(),
                LinearLayoutManager.HORIZONTAL,
                false
        ));


        rvTopList = (RecyclerView) findViewById(R.id.rv_top_list);
        rvTopList.setLayoutManager(new LinearLayoutManager(
                getBaseContext(),
                LinearLayoutManager.HORIZONTAL,
                false
        ));


        rvClosestList = (RecyclerView) findViewById(R.id.rv_closest_list);
        rvClosestList.setLayoutManager(new LinearLayoutManager(
                getBaseContext(),
                LinearLayoutManager.HORIZONTAL,
                false
        ));
        initClosestList();


    }

    public void initTopList(){


    }

    public void initLatestList(){

    }

    public void initClosestList(){
        closestAdventureAdapter = new ClosestAdventureAdapter(closestAdventureList);
        closestAdventureAdapter.setOnAdventureClickListener(new ClosestAdventureAdapter.OnAdventureClickListener() {
            @Override
            public void onAdventureClick(View view, Adventure a) {
                Intent adventurePageIntent = new Intent(getBaseContext(), AdventureDetailsActivity.class);
                adventurePageIntent.putExtra("aName", a.getName());
                adventurePageIntent.putExtra("aDetails", a.getDetails());

                startActivity(adventurePageIntent);

                /*try{
                    String filename = "adventureViewedTempPic.png";
                    FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
                    a.getPicture().compress(Bitmap.CompressFormat.PNG, 100, fos);

                    fos.close();
                    a.getPicture().recycle();

                    adventurePageIntent.putExtra("aPicture", filename);

                    startActivity(adventurePageIntent);


                } catch (Exception e){
                    e.printStackTrace();
                }*/





            }
        });

        rvClosestList.setAdapter(closestAdventureAdapter);



    }


    public void initDummyLatestList(){
        latestAdventureList = new ArrayList<>();
        latestAdventureList.add(new Adventure("South Adventure",
                "Venture the south.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        latestAdventureList.add(new Adventure("East Adventure",
                "Venture the east.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        latestAdventureList.add(new Adventure("North Adventure",
                "Venture the north.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        latestAdventureList.add(new Adventure("North Adventure",
                "Venture the north.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        latestAdventureList.add(new Adventure("North Adventure",
                "Venture the north.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        latestAdventureList.add(new Adventure("North Adventure",
                "Venture the north.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
    }

    public void initDummyClosestList(){
        closestAdventureList = new ArrayList<>();
        closestAdventureList.add(new Adventure("South Adventure",
                "Venture the south.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        closestAdventureList.add(new Adventure("East Adventure",
                "Venture the east.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        closestAdventureList.add(new Adventure("North Adventure",
                "Venture the north.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        closestAdventureList.add(new Adventure("North Adventure",
                "Venture the north.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        closestAdventureList.add(new Adventure("North Adventure",
                "Venture the north.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        closestAdventureList.add(new Adventure("North Adventure",
                "Venture the north.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));


    }

    public void initDummyTopList(){
        topAdventureList = new ArrayList<>();
        topAdventureList.add(new Adventure("South Adventure",
                "Venture the south.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        topAdventureList.add(new Adventure("East Adventure",
                "Venture the east.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        topAdventureList.add(new Adventure("North Adventure",
                "Venture the north.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        topAdventureList.add(new Adventure("North Adventure",
                "Venture the north.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        topAdventureList.add(new Adventure("North Adventure",
                "Venture the north.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        topAdventureList.add(new Adventure("North Adventure",
                "Venture the north.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));


    }
}
