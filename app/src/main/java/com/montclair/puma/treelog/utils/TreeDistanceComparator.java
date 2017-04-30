package com.montclair.puma.treelog.utils;

import android.location.Location;

import com.montclair.puma.treelog.models.TreeData;

import java.util.Comparator;

/**
 * Created by elionlimanaj on 4/30/17.
 */

public class TreeDistanceComparator implements Comparator<TreeData> {
    private Location currentLocation;

    public TreeDistanceComparator(Location location) {
        currentLocation = location;
    }

    @Override
    public int compare(TreeData o1, TreeData o2) {
        Location l1 = new Location("");
        l1.setLatitude(Float.parseFloat(o1.getLatitude()));
        l1.setLongitude(Float.parseFloat(o1.getLongitude()));
        Location l2 = new Location("");
        l2.setLatitude(Float.parseFloat(o2.getLatitude()));
        l2.setLongitude(Float.parseFloat(o2.getLongitude()));
        return Float.compare(l1.distanceTo(currentLocation), l2.distanceTo(currentLocation));
    }
}