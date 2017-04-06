package hellow.mobapde.com.helloworld;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import hellow.mobapde.com.helloworld.Adapters.MoreAdventureAdapter;
import hellow.mobapde.com.helloworld.Adapters.NearbyAdventureAdapter;
import hellow.mobapde.com.helloworld.Beans.Adventure;
import hellow.mobapde.com.helloworld.R;

public class MoreActivity extends AppCompatActivity {

    ArrayList<Adventure> moreAdventuresList;

    RecyclerView rvMoreAdventuresList;

    MoreAdventureAdapter moreAdventureAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        Intent recIntent = getIntent();
        String activityName = recIntent.getStringExtra("type");

        setTitle(activityName);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        initDummyMoreList();

        rvMoreAdventuresList = (RecyclerView) findViewById(R.id.rv_more_adventures_list);
        rvMoreAdventuresList.setLayoutManager(new LinearLayoutManager(
                getBaseContext(),
                LinearLayoutManager.VERTICAL,
                false
        ));
        initMoreAdventuresList();


    }

    public void initMoreAdventuresList(){
        moreAdventureAdapter = new MoreAdventureAdapter(moreAdventuresList);
        moreAdventureAdapter.setOnAdventureClickListener(new MoreAdventureAdapter.OnAdventureClickListener() {
            @Override
            public void onAdventureClick(View view, Adventure a) {
                Intent adventurePageIntent = new Intent(getBaseContext(), AdventureDetailsActivity.class);
                adventurePageIntent.putExtra("aName", a.getName());
                adventurePageIntent.putExtra("aDetails", a.getDetails());

                startActivity(adventurePageIntent);


            }
        });

        rvMoreAdventuresList.setAdapter(moreAdventureAdapter);
    }

    public void initDummyMoreList(){
        moreAdventuresList = new ArrayList<>();
        moreAdventuresList.add(new Adventure("South Adventure",
              "Venture the south.",
              "Incomplete",
              BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        moreAdventuresList.add(new Adventure("East Adventure",
              "Venture the east.",
              "Incomplete",
              BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        moreAdventuresList.add(new Adventure("North Adventure",
              "Venture the north.",
              "Incomplete",
              BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        moreAdventuresList.add(new Adventure("North Adventure",
              "Venture the north.",
              "Incomplete",
              BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        moreAdventuresList.add(new Adventure("North Adventure",
              "Venture the north.",
              "Incomplete",
              BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
        moreAdventuresList.add(new Adventure("North Adventure",
                "Venture the north.",
                "Incomplete",
                BitmapFactory.decodeResource(getResources(), R.drawable.app_icon)));
    }
}
