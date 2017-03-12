package hellow.mobapde.com.helloworld;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import hellow.mobapde.com.helloworld.Adapters.CustomSwipeAdapter;

public class AdventureActivity extends AppCompatActivity {

    ViewPager vpFeaturedList;
    CustomSwipeAdapter customSwipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure);

        vpFeaturedList = (ViewPager) findViewById(R.id.vp_featured_list);
        customSwipeAdapter = new CustomSwipeAdapter(this);
        vpFeaturedList.setAdapter(customSwipeAdapter);
    }
}
