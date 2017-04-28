package com.montclair.puma.treelog.utils;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

/**
 * Created by Agni on 4/27/2017.
 */

public class ChildEventListenerAdapter implements ChildEventListener {
    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        System.out.println("dataSnapshot = " + dataSnapshot.getValue());

    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        System.out.println("dataSnapshot = " + dataSnapshot.getValue());
    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {
        System.out.println("dataSnapshot = " + dataSnapshot.getValue());
    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
        System.out.println("dataSnapshot = " + dataSnapshot.getValue());
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
