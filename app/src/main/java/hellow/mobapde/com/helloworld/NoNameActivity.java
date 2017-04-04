package hellow.mobapde.com.helloworld;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NoNameActivity extends AppCompatActivity {

    Button btnFinish;
    EditText etAdvName;

    public static final String USERNAME = "username";

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

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(USERNAME, name);
                editor.commit();

                Intent nameCreatedIntent = new Intent(getBaseContext(), MapsActivity.class);

                finish();

                startActivity(nameCreatedIntent);

            }
        });
    }
}
