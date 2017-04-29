package com.montclair.puma.treelog.utils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Zee on 4/19/2017.
 */

public class FireBase {
    private static FirebaseDatabase fb = FirebaseDatabase.getInstance();

    private static FireBase ourInstance = new FireBase();

    public static FireBase getInstance() {
        return ourInstance;
    }

    public DatabaseReference getFireBaseReference(String graph) {
        return fb.getReference(graph);
    }
}
