package com.montclair.puma.treelog;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.montclair.puma.treelog.models.TreeData;
import com.montclair.puma.treelog.models.TreeSession;
import com.montclair.puma.treelog.utils.Constants;
import com.montclair.puma.treelog.utils.CustomizedListAdapter;
import com.montclair.puma.treelog.utils.LocationActivity;
import com.montclair.puma.treelog.utils.TreeDistanceComparator;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TreeListFiltered extends LocationActivity {
    DatabaseReference mDatabase;
    CustomizedListAdapter adapter;
    ArrayList<TreeData> treeList = new ArrayList<>();

    @BindView(R.id.lv_tree_list_filter)
    ListView treeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_list_filtered);
        ButterKnife.bind(this);
        treeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TreeSession.getInstance().setTreeData(treeList.get(position));
                Intent intent = new Intent(TreeListFiltered.this, TreeHistory.class);
                startActivity(intent);
            }
        });
        mDatabase = FirebaseDatabase.getInstance().getReference(Constants.FIRBASE_TREE_DATA);
    }

    public void load10(View view) {
        load(3);
    }

    public void load30(View view) {
        load(9);
    }

    public void load100(View view) {
        load(30);
    }

    public void load50000(View view) {
        load(15240);
    }

    public void load(final int distanceInMeters) {
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                treeList = new ArrayList<>();
                getCurrentLocation();
                adapter = new CustomizedListAdapter(TreeListFiltered.this, treeList);
                treeListView.setAdapter(adapter);

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    TreeData treeItem = child.getValue(TreeData.class);

                    if (mLastLocation != null) {
                        Location treeLocation = new Location("");
                        treeLocation.setLatitude(Float.parseFloat(treeItem.getLatitude()));
                        treeLocation.setLongitude(Float.parseFloat(treeItem.getLongitude()));

                        if (treeLocation.distanceTo(mLastLocation) < distanceInMeters) {
                            treeItem.setTreeId(child.getKey());
                            treeList.add(treeItem);
                        }
                    }
                }
                Collections.sort(treeList, new TreeDistanceComparator(mLastLocation));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
}