package hellow.mobapde.com.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import hellow.mobapde.com.helloworld.Adapters.RelatedAdventureAdapter;
import hellow.mobapde.com.helloworld.Beans.Adventure;

public class ViewRelAdvPopActivity extends AppCompatActivity {

    private static String TAG = "ViewRelAdvPopActivity";


    RecyclerView rvRelAdvList;
    RelatedAdventureAdapter relatedAdventureAdapter;
    ArrayList<Adventure> adventureList;

    Button btnRelAdvGoing;
    Button btnRelAdvCancel;

    private boolean isAdventureSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rel_adv);

        initRelAdvDummyData();

        createContentView();
        initListeners();


    }

    public void createContentView(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8), (int)(height*0.8));

        btnRelAdvCancel = (Button) findViewById(R.id.btn_rel_adv_cancel);

        btnRelAdvGoing = (Button) findViewById(R.id.btn_rel_adv_going);

        rvRelAdvList = (RecyclerView) findViewById(R.id.rv_rel_adv_list);
        rvRelAdvList.setLayoutManager(new LinearLayoutManager(
                getBaseContext(),
                LinearLayoutManager.VERTICAL,
                false
        ));
        initRelAdvList();



    }

    public void initListeners(){
        btnRelAdvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnRelAdvGoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultingIntent = new Intent();
                resultingIntent.putExtra("selected", true);
                setResult(Activity.RESULT_OK, resultingIntent);
                finish();
            }
        });
    }

    public void initRelAdvDummyData(){
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

    public void initRelAdvList(){
        relatedAdventureAdapter = new RelatedAdventureAdapter(adventureList);
        rvRelAdvList.setAdapter(relatedAdventureAdapter);
    }
}
