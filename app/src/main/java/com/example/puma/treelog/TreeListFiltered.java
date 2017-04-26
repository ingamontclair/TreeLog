package com.example.puma.treelog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.puma.treelog.models.TreeData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TreeListFiltered extends AppCompatActivity {
    private DatabaseReference mDatabase;

    @BindView(R.id.lv_tree_list_filter)
    ListView treeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_list_filtered);

        ButterKnife.bind(this);

        mDatabase = FirebaseDatabase.getInstance().getReference("Tree");
        final ArrayList<String> treeList = new ArrayList<>();

        mDatabase.orderByValue().limitToFirst(10).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    TreeData treeItem = child.getValue(TreeData.class);
                    treeItem.setTreeId(child.getKey());
//                    treeList.add(treeItem);
                    treeList.add(++i + ". " + treeItem.getTreeName() + ":  " + treeItem.getStreetAddress());
                }

                //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(Leaderboard.this, android.R.layout.simple_list_item_1, players);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(TreeListFiltered.this, android.R.layout.simple_list_item_1, treeList);
                treeListView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
}
