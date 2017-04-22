package com.example.puma.treelog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.puma.treelog.models.TreeData;
import com.example.puma.treelog.models.TreeSession;
import com.example.puma.treelog.utils.Constants;
import com.example.puma.treelog.utils.CustomizedListAdapter;
import com.example.puma.treelog.utils.FireBase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class TreeList extends AppCompatActivity {
    ListView treeListView;
    CustomizedListAdapter adapter;
    ChildEventListener mChildEventListener;
    DatabaseReference myRef;
    ArrayList<TreeData> treeList = new ArrayList<TreeData>(){
        {
            add(new TreeData("1600 Pennsylvania Avenue","private","Evergreen","Pine123"));
            add(new TreeData("Edgewater rd","city","Fruit tree","Apple567"));
            add(new TreeData("7119 Shore rd. Brooklyn","private","Deciduous Tree","Willow789"));
            add(new TreeData("19 Highland Ave, Montville, NJ 07045","city","Fruit tree","Peach123"));
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_list);
        treeListView = (ListView) findViewById(R.id.lv_tree_list);
        adapter = new CustomizedListAdapter(this, treeList);
        treeListView.setAdapter(adapter);
        treeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Toast.makeText(TreeList.this, "List item clicked " + position, Toast.LENGTH_SHORT).show();
                TreeSession.getInstance().setTreeData(treeList.get(position));
                Intent intent = new Intent(TreeList.this, TreeHistory.class);
                startActivity(intent);
            }
        });
        myRef= FireBase.getInstance().getFireBaseReference(Constants.FIRBASE_TREE_DATA);
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                TreeData treeItem = dataSnapshot.getValue(TreeData.class);
                treeList.add(treeItem);
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