package hellow.mobapde.com.helloworld.Firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by patricktobias on 12/03/2017.
 */

public class FirebaseHelper {

    private DatabaseReference databaseReference;

    public FirebaseHelper () {

        databaseReference = FirebaseDatabase.getInstance().getReference();

    }

}
