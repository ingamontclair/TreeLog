package com.example.puma.treelog;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.puma.treelog.models.*;
import com.example.puma.treelog.utils.Constants;
import com.example.puma.treelog.utils.FireBase;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class WelcomeActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
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
        btnLocateME = (Button)findViewById(R.id.btn_locateMe);
        btnLocateME.setOnClickListener(new LocateMELstr());

        myRef= FireBase.getInstance().getFireBaseReference(Constants.FIRBASE_TREE_DATA);
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
        if (user != null){
            ((TextView)findViewById(R.id.wc_msg)).setText("Welcome "+user.getUserName());
        }
        buildGoogleApiClient();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        // Handle item selection
        Intent intent;
        switch (item.getItemId()) {
            case R.id.menu_new_tree:
                //start activity to make a new tree
                intent = new Intent(WelcomeActivity.this, LocateNewTree.class);
                startActivity(intent);
                //Toast.makeText(WelcomeActivity.this, "New tree clicked", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_list_tree:
                //start list tree activity in the fenced area
                intent = new Intent (WelcomeActivity.this, TreeList.class);
                startActivity(intent);
                //Toast.makeText(WelcomeActivity.this, "Clicked List os trees", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_make_photo:
                //load camera for making picture (quick access for making photos)
                intent=new Intent(WelcomeActivity.this,TakeTreePic.class);
                startActivity(intent);
                //Toast.makeText(WelcomeActivity.this, "Clicked Camera", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_map:
                startActivity(new Intent(WelcomeActivity.this, LoadingScreenActivity.class));
                return true;
            case R.id.menu_logout:
                FirebaseAuth.getInstance().signOut();
                finish();
                intent = new Intent(WelcomeActivity.this, Login.class);
                startActivity(intent);
                return true;
            case R.id.menu_exit:
                moveTaskToBack(true);
                //android.os.Process.killProcess(android.os.Process.myPid());
                //System.exit(1);
                FirebaseAuth.getInstance().signOut();
                finish();
                System.exit(1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
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
                    if (ActivityCompat.checkSelfPermission(WelcomeActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                        if (mLastLocation != null) {
                            double lat = mLastLocation.getLatitude();
                            double lon = mLastLocation.getLongitude();
                            startActivity(new Intent(WelcomeActivity.this, LoadingScreenActivity.class)
                                    .putExtra("latitude", String.valueOf(lat)).putExtra("longitude",  String.valueOf(lon)));
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
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
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
