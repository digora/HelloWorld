package hellow.mobapde.com.helloworld.StampChecker;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import hellow.mobapde.com.helloworld.Beans.Profile;
import hellow.mobapde.com.helloworld.Beans.Stamp;
import hellow.mobapde.com.helloworld.Firebase.FirebaseHelper;

/**
 * Created by patricktobias on 07/04/2017.
 */

public class StampManager {

    public final static String FIRST_STAMP_KEY = "-Kh9pzwAD9fdPq5KiqIH";
    public final static String FIRST_ADVENTURE_KEY = "-Kh9rCkP_ag_71RQX8zm";

    FirebaseHelper firebaseHelper;

    public StampManager () {
        firebaseHelper = new FirebaseHelper();
    }

    public void checkEligibleForFirstStop (final String profileKey) {
        DatabaseReference profileReference = firebaseHelper.getProfileReference(profileKey);

        profileReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Profile profile = dataSnapshot.getValue(Profile.class);

                String stampKey;

                if (profile.getStamps() != null)
                    stampKey = profile.getStamps().get(FIRST_STAMP_KEY);
                else
                    stampKey = null;

                if (stampKey == null) {
                    if (profile.getVisitedStops().size() == 1) {
                        firebaseHelper.updateUserStamps(profileKey, FIRST_STAMP_KEY);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void checkEligibleForFirstAdventure (final String profileKey) {
        DatabaseReference profileReference = firebaseHelper.getProfileReference(profileKey);

        profileReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Profile profile = dataSnapshot.getValue(Profile.class);

                String stampKey;

                if (profile.getStamps() != null)
                    stampKey = profile.getStamps().get(FIRST_ADVENTURE_KEY);
                else
                    stampKey = null;

                if (stampKey == null) {
                    if (profile.getAdventureLog().size() == 1) {
                        firebaseHelper.updateUserStamps(profileKey, FIRST_ADVENTURE_KEY);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
