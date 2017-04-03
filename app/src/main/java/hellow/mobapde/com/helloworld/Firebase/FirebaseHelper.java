package hellow.mobapde.com.helloworld.Firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import hellow.mobapde.com.helloworld.Beans.Adventure;
import hellow.mobapde.com.helloworld.Beans.Profile;
import hellow.mobapde.com.helloworld.Beans.Stop;

/**
 * Created by patricktobias on 12/03/2017.
 */

public class FirebaseHelper {

    private DatabaseReference firebaseReference = FirebaseDatabase.getInstance().getReference();
    private Map<String,Stop> stops = new HashMap<>();
    private ArrayList<Adventure> adventures = new ArrayList<>();
    public static final String STOP_PATH = "stops";
    public static final String AD_PATH = "adventures";
    public static final String PRO_PATH = "profiles";
    public static final String NONE = "0";
    public static final String CUR_AD_KEY = "currAdKey";
    public static final String AD_LOG_KEY = "adventureLog";

    public FirebaseHelper () {

    }

    public DatabaseReference getFirebaseReference() {
        return firebaseReference;
    }

    public void setFirebaseReference(DatabaseReference firebaseReference) {
        this.firebaseReference = firebaseReference;
    }

    public ArrayList<Adventure> getAdventures() {
        return adventures;
    }

    public DatabaseReference getStopReference() {
        return firebaseReference.child(STOP_PATH);
    }


    public DatabaseReference getAdventureReference() {
        return firebaseReference.child(AD_PATH);
    }

    public DatabaseReference getAdventureReference (String key) {
        return firebaseReference.child(AD_PATH).child(key);
    }

    public Map<String, Stop> getStops() {
        return stops;
    }

    public void setStops(Map<String, Stop> stops) {
        this.stops = stops;
    }

    public void setAdventures(ArrayList<Adventure> adventures) {
        this.adventures = adventures;
    }

    public void createAdventure(Adventure a){
        DatabaseReference adReference = firebaseReference.child(AD_PATH);
        String key = adReference.push().getKey();
        a.setKey(key);
        adReference.child(key).setValue(a);
    }
    public void updateProfilesAdventure(final String profileKey){
        firebaseReference.child(PRO_PATH).child(profileKey).child(CUR_AD_KEY).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String curAdventure = dataSnapshot.getValue().toString();

                firebaseReference.child(PRO_PATH).child(profileKey).child(AD_LOG_KEY).child(curAdventure).setValue(true);

                firebaseReference.child(PRO_PATH).child(profileKey).child(CUR_AD_KEY).setValue(null);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void updateProfilesCurrAdventure(final String profileKey, String adventureKey){
        firebaseReference.child(PRO_PATH).child(profileKey).child(CUR_AD_KEY).setValue(adventureKey);

    }
    public void createStop(Stop s){
        DatabaseReference stopReference = firebaseReference.child(STOP_PATH);
        String key = stopReference.push().getKey();
        s.setKey(key);
        stopReference.child(key).setValue(s);
    }
    public void createProfile(Profile p){
        DatabaseReference profileReference = firebaseReference.child(PRO_PATH);
        String key = profileReference.push().getKey();
        p.setKey(key);
        profileReference.child(key).setValue(p);
    }
}
