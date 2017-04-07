package hellow.mobapde.com.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ViewStampPopActivity extends AppCompatActivity {

    TextView tvStampTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stamp_pop);

        createContentView();
    }

    public void createContentView(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.7), (int)(height*0.3));

        tvStampTitle = (TextView) findViewById(R.id.tv_view_stamp_pop_title);
        tvStampTitle.setText(getIntent().getStringExtra("sName"));
    }
}
