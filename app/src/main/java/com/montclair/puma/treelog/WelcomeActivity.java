package com.montclair.puma.treelog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.montclair.puma.treelog.models.User;
import com.montclair.puma.treelog.utils.Constants;
import com.montclair.puma.treelog.utils.FireBase;
import com.montclair.puma.treelog.utils.LocationActivity;

public class WelcomeActivity extends LocationActivity
        implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    ChildEventListener mChildEventListener;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Button btnLocateME = (Button) findViewById(R.id.btn_locateMe);
        btnLocateME.setOnClickListener(new LocateMELstr());

        myRef = FireBase.getInstance().getFireBaseReference(Constants.FIRBASE_TREE_DATA);
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        myRef.addChildEventListener(mChildEventListener);

        User user = com.montclair.puma.treelog.models.TreeSession.getInstance().getUser();
        if (user != null) {
            ((TextView) findViewById(R.id.wc_msg)).setText("Welcome " + user.getUserName());
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    class LocateMELstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_locateMe) {
                getCurrentLocation();
                if (mLastLocation != null) {
                    startActivity(new Intent(WelcomeActivity.this, LoadingScreenActivity.class)
                            .putExtra("latitude", String.valueOf(mLastLocation.getLatitude()))
                            .putExtra("longitude", String.valueOf(mLastLocation.getLongitude())));
                }
            }
        }
    }
}