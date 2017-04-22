package com.example.puma.treelog;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<String> locations = new ArrayList<>();

    double lat;
    double lon;
    String treeAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Intent intent = getIntent();
        lat =Double.valueOf(intent.getStringExtra("latitude"));
        lon = Double.valueOf(intent.getStringExtra("longitude"));
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

        // Populate list with examples
       /* locations.add("Clifton, NJ");
        locations.add("186 Ackerman Avenue, Clifton, NJ");
        locations.add("76 Center Street, Clifton, NJ");
        locations.add("27 Cutler Street, Clifton, NJ");
        locations.add("Cooper Plaza Commons, Camden, NJ");
        locations.add("704 Chelton Ave, NJ");
        locations.add("1573 S 8th St, NJ");*/

        // If the user chooses the map from Tree History
        if (treeAddress!=null){
            locations.add(treeAddress);
            try {
                //List<Address> nameCord = geocoder.getFromLocationName(locations.get(locations.size()-1),1);
                //if (nameCord != null && nameCord.size() > 0) {
                    //double lat = nameCord.get(0).getLatitude();
                   // double lng = nameCord.get(0).getLongitude();
                    LatLng vita = new LatLng(lat, lon);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(vita,17));
                    mMap.addMarker(new MarkerOptions().position(vita).title(locations.get(locations.size()-1)));
                //}
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
        // Populates the map with markers
        int ct;
        for (ct = 0; ct < locations.size(); ct++) {
            try {
                //List<Address> nameCord = geocoder.getFromLocationName(locations.get(ct),1);
                //if (nameCord != null && nameCord.size() > 0) {
                   // double lat = nameCord.get(0).getLatitude();
                    //double lng = nameCord.get(0).getLongitude();
                    LatLng vita = new LatLng(lat, lon);
                    mMap.addMarker(new MarkerOptions().position(vita).title(locations.get(ct)));
               // }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}