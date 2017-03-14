package hellow.mobapde.com.helloworld;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    ImageView ivAdventureViewedPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure_details);


        createContentView();
    }

    private void createContentView(){

        Intent collectIntent = getIntent();

        btnGo = (Button) findViewById(R.id.btn_go);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapIntent = new Intent(getBaseContext(), MapsActivity.class);


                startActivity(mapIntent);
            }
        });

        btnCancel = (Button) findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvAdventureViewedTitle = (TextView) findViewById(R.id.tv_adventure_viewed_title);
        tvAdventureViewedTitle.setText(collectIntent.getStringExtra("aTitle"));

        tvAdventureViewedDetails = (TextView) findViewById(R.id.tv_adventure_viewed_details);
        tvAdventureViewedDetails.setText(collectIntent.getStringExtra("aDetails"));

        ivAdventureViewedPicture = (ImageView) findViewById(R.id.iv_adventure_viewed_picture);

        Bitmap bmp = null;
        String filename = collectIntent.getStringExtra("aPicture");
        try{
            FileInputStream fis = openFileInput(filename);
            bmp = BitmapFactory.decodeStream(fis);
            fis.close();

            ivAdventureViewedPicture.setImageBitmap(bmp);
        }catch(Exception e){
            e.printStackTrace();
        }








    }
}
