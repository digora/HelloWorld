package hellow.mobapde.com.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import hellow.mobapde.com.helloworld.Beans.Profile;

public class ProfileActivity extends AppCompatActivity {

    Profile your_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }
}
