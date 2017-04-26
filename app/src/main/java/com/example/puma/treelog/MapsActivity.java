package com.example.puma.treelog;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.example.puma.treelog.models.TreeData;
import com.example.puma.treelog.utils.BaseActivity;
import com.example.puma.treelog.utils.Constants;
import com.example.puma.treelog.utils.FireBase;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends BaseActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<TreeData> locations;

    double lat;
    double lon;
    String treeAddress;

    ChildEventListener mChildEventListener;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Intent intent = getIntent();
        lat =intent.getStringExtra("latitude")!=null?Double.valueOf(intent.getStringExtra("latitude")):0;
        lon = intent.getStringExtra("longitude")!=null?Double.valueOf(intent.getStringExtra("longitude")):0;
        treeAddress = intent.getStringExtra("address")!=null ?intent.getStringExtra("address").replace("\n"," "):null;

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Geocoder geocoder = new Geocoder(this);

        // If the user chooses the map from Tree History
        if (treeAddress!=null){
            try {
                    LatLng vita = new LatLng(lat, lon);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(vita,17));
                    mMap.addMarker(new MarkerOptions().position(vita).title(treeAddress));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // If the user chooses 'Locate Me'
        else if (lat != 0 && lon != 0) {
            LatLng myLocale = new LatLng(lat, lon);
            mMap.addMarker(new MarkerOptions().position(myLocale).title("You are here"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocale,17));
        }
        // If the user chooses 'Map'
        else {
            // Sets up Camden focus
            try {
                List<Address> camdenCoord = geocoder.getFromLocationName("Camden, NJ", 1);
                if (camdenCoord != null && camdenCoord.size() > 0) {
                    double camLat = camdenCoord.get(0).getLatitude();
                    double camLon = camdenCoord.get(0).getLongitude();
                    LatLng camCord = new LatLng(camLat, camLon);
                    mMap.addMarker(new MarkerOptions().position(camCord).title("Camden, NJ"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(camCord,13));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // add markers for database items
        myRef= FireBase.getInstance().getFireBaseReference(Constants.FIRBASE_TREE_DATA);
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                TreeData treeItem = dataSnapshot.getValue(TreeData.class);

                if (treeItem!=null&& treeItem.getLatitude()!=null && treeItem.getLongitude()!=null && !treeItem.getLatitude().equals("") && !treeItem.getLongitude().equals("")){
                    double addLat = Double.valueOf(treeItem.getLatitude());
                    double addLon = Double.valueOf(treeItem.getLongitude());
                    LatLng addCord = new LatLng(addLat, addLon);
                    mMap.addMarker(new MarkerOptions().position(addCord).title(treeItem.getStreetAddress()));
                }
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
    }
}