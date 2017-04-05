package hellow.mobapde.com.helloworld;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.LinkedHashMap;
import java.util.Map;

import hellow.mobapde.com.helloworld.Beans.Profile;
import hellow.mobapde.com.helloworld.Firebase.FirebaseHelper;

public class NoNameActivity extends AppCompatActivity {

    Button btnFinish;
    EditText etAdvName;

    public static final String USERNAME = "username";
    public static final String USER_KEY = "userkey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_name);

        createContentView();
        initListeners();
    }

    public void createContentView(){
        btnFinish = (Button) findViewById(R.id.btn_finish);
        etAdvName = (EditText) findViewById(R.id.et_adv_name);
    }

    public void initListeners(){
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = "default";
                if(etAdvName.getText() != null){
                    name = etAdvName.getText().toString();
                }

                Map<String, String> adventureLog = new LinkedHashMap<String, String>();

                Profile p = new Profile(name, "Male", adventureLog);
                FirebaseHelper firebaseHelper = new FirebaseHelper();

                String newUserKey = firebaseHelper.createProfile(p);

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(USERNAME, name);
                editor.putString(USER_KEY, newUserKey);
                editor.commit();

                Intent nameCreatedIntent = new Intent(getBaseContext(), MapsActivity.class);

                finish();

                startActivity(nameCreatedIntent);

            }
        });
    }
}
