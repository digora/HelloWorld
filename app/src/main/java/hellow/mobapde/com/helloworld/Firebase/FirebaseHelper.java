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
import hellow.mobapde.com.helloworld.Beans.Stop;

/**
 * Created by patricktobias on 12/03/2017.
 */

public class FirebaseHelper {

    private DatabaseReference stopReference;
    private DatabaseReference adReference;
    private Map<String,Stop> stops = new HashMap<>();
    private ArrayList<Adventure> adventures = new ArrayList<>();

    public FirebaseHelper () {
        stopReference = FirebaseDatabase.getInstance().getReference("/stops");
        adReference = FirebaseDatabase.getInstance().getReference("/adventures");

        //On Instantiation of helper add all adventures to array list
        adReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for(DataSnapshot child:children){
                    Adventure value = child.getValue(Adventure.class);
                    adventures.add(value);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //On Instantiation of helper add all adventures to hash map
        stopReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child:children) {
                    Stop value = child.getValue(Stop.class);
                    String key = value.getKey();
                    stops.put(key,value);
                }
                System.out.println("Total number of stops: "+ stops.size());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public DatabaseReference getStopReference() {
        return stopReference;
    }

    public void setStopReference(DatabaseReference stopReference) {
        this.stopReference = stopReference;
    }

    public DatabaseReference getAdReference() {
        return adReference;
    }

    public void setAdReference(DatabaseReference adReference) {
        this.adReference = adReference;
    }

    public Map<String, Stop> getStops() {
        return stops;
    }

    public void setStops(Map<String, Stop> stops) {
        this.stops = stops;
    }

    public ArrayList<Adventure> getAdventures() {
        return adventures;
    }

    public void setAdventures(ArrayList<Adventure> adventures) {
        this.adventures = adventures;
    }

    public void createAdventure(Adventure a){
        String key = adReference.push().getKey();
        a.setKey(key);
        adReference.child(key).setValue(a);
    }
    public void createStop(Stop s){
        String key = stopReference.push().getKey();
        s.setKey(key);
        stopReference.child(key).setValue(s);
    }
}
