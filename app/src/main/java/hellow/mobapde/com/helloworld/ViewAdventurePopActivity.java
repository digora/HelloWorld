package hellow.mobapde.com.helloworld;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;

import hellow.mobapde.com.helloworld.Beans.Adventure;

public class ViewAdventurePopActivity extends AppCompatActivity {

    TextView tvViewAdventurePopTitle;
    ImageView ivAdventureDifficulty;

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
        ivAdventureDifficulty = (ImageView) findViewById(R.id.iv_view_adventure_pop_image);

        tvViewAdventurePopTitle.setText(getIntent().getStringExtra("aName"));
        ivAdventureDifficulty.setImageBitmap(initBitmapDifficulty(getIntent().getStringExtra("aDifficulty")));
    }

    public Bitmap initBitmapDifficulty(String difficulty) {
        switch (difficulty) {
            case Adventure.CASUAL_DIFFICULTY :
                return BitmapFactory.decodeResource(getResources(), R.drawable.icon_casual);
            case Adventure.INTERMEDIATE_DIFFICULTY :
                return BitmapFactory.decodeResource(getResources(), R.drawable.icon_intermediate);
            case Adventure.ADVENTUROUS_DIFFICULTY :
                return BitmapFactory.decodeResource(getResources(), R.drawable.icon_adventurous);
            default:
                return null;
        }
    }
}
