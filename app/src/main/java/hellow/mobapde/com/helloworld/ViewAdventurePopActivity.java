package hellow.mobapde.com.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class ViewAdventurePopActivity extends AppCompatActivity {

    TextView tvViewAdventurePopTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_adventure_pop);

        createContentView();
    }

    public void createContentView(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.7), (int)(height*0.3));

        tvViewAdventurePopTitle = (TextView) findViewById(R.id.tv_view_adventure_pop_title);
        tvViewAdventurePopTitle.setText(getIntent().getStringExtra("aName"));


    }
}
