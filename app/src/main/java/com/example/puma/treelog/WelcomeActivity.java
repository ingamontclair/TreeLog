package com.example.puma.treelog;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.puma.treelog.models.TreeData;
import com.example.puma.treelog.models.User;
import com.example.puma.treelog.utils.BaseActivity;
import com.example.puma.treelog.utils.Constants;
import com.example.puma.treelog.utils.FireBase;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

public class WelcomeActivity extends BaseActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private Button btnLocateME; //button for fencing?

    /**
     * Provides the entry point to Google Play services.
     */
    protected GoogleApiClient mGoogleApiClient;

    /**
     * Represents a geographical location.
     */
    protected Location mLastLocation;

    ChildEventListener mChildEventListener;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        btnLocateME = (Button) findViewById(R.id.btn_locateMe);
        btnLocateME.setOnClickListener(new LocateMELstr());

        myRef = FireBase.getInstance().getFireBaseReference(Constants.FIRBASE_TREE_DATA);
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                TreeData treeItem = dataSnapshot.getValue(TreeData.class);
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

        User user = com.example.puma.treelog.models.TreeSession.getInstance().getUser();
        if (user != null) {
            ((TextView) findViewById(R.id.wc_msg)).setText("Welcome " + user.getUserName());
        }
        buildGoogleApiClient();
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
                if (ActivityCompat.checkSelfPermission(WelcomeActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                    if (mLastLocation != null) {
                        double lat = mLastLocation.getLatitude();
                        double lon = mLastLocation.getLongitude();
                        startActivity(new Intent(WelcomeActivity.this, LoadingScreenActivity.class)
                                .putExtra("latitude", String.valueOf(lat)).putExtra("longitude", String.valueOf(lon)));
                    } else {
                        Toast.makeText(WelcomeActivity.this, "No connection", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    ActivityCompat.requestPermissions(WelcomeActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                }
            }
        }
    }

    // Checks for user response to permission request
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(WelcomeActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                        if (mLastLocation != null) {
                            double lat = mLastLocation.getLatitude();
                            double lon = mLastLocation.getLongitude();
                            startActivity(new Intent(WelcomeActivity.this, LoadingScreenActivity.class)
                                    .putExtra("latitude", String.valueOf(lat)).putExtra("longitude", String.valueOf(lon)));
                        } else {
                            Toast.makeText(WelcomeActivity.this, "No connection", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }
    }

    /**
     * Builds a GoogleApiClient. Uses the addApi() method to request the LocationServices API.
     */
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }
}