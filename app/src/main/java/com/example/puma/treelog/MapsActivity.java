package com.example.puma.treelog;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Populate list with examples
        locations.add("Camden, NJ");
        locations.add("The Victor's Pub, NJ");
        locations.add("Norma's Grocery Camden,NJ");
        locations.add("Antioch Baptist Church, Camden, NJ");
        locations.add("Cooper Plaza Commons, Camden, NJ");
        locations.add("704 Chelton Ave, NJ");
        locations.add("1573 S 8th St, NJ");

        // Sets up Camden focus
        Geocoder geocoder = new Geocoder(this);
        try {
            List<Address> nameCord = geocoder.getFromLocationName(locations.get(0),1);
            if (nameCord != null && nameCord.size() > 0) {
                double lat = nameCord.get(0).getLatitude();
                double lng = nameCord.get(0).getLongitude();
                LatLng vita = new LatLng(lat, lng);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(vita, 13));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Populates the map with markers
        int ct;
        for (ct = 0; ct < locations.size(); ct++) {
            try {
                List<Address> nameCord = geocoder.getFromLocationName(locations.get(ct),1);
                if (nameCord != null && nameCord.size() > 0) {
                    double lat = nameCord.get(0).getLatitude();
                    double lng = nameCord.get(0).getLongitude();
                    LatLng vita = new LatLng(lat, lng);
                    mMap.addMarker(new MarkerOptions().position(vita).title(locations.get(ct)));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
