package com.montclair.puma.treelog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.montclair.puma.treelog.models.TreeData;
import com.montclair.puma.treelog.models.TreeSession;
import com.montclair.puma.treelog.utils.BaseActivity;
import com.montclair.puma.treelog.utils.Constants;
import com.montclair.puma.treelog.utils.CustomizedListAdapter;
import com.montclair.puma.treelog.utils.FireBase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class TreeList extends BaseActivity {
    ListView treeListView;
    CustomizedListAdapter adapter;
    ChildEventListener mChildEventListener;
    DatabaseReference myRef;
    ArrayList<TreeData> treeList = new ArrayList<TreeData>();

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
        myRef = FireBase.getInstance().getFireBaseReference(Constants.FIRBASE_TREE_DATA);
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                TreeData treeItem = dataSnapshot.getValue(TreeData.class);
                treeItem.setTreeId(dataSnapshot.getKey());
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