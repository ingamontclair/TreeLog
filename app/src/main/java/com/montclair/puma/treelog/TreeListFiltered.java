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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TreeListFiltered extends BaseActivity {
    private DatabaseReference mDatabase;
    CustomizedListAdapter adapter;
    ArrayList<TreeData> treeList = new ArrayList<>();

    @BindView(R.id.lv_tree_list_filter)
    ListView treeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_list_filtered);
        ButterKnife.bind(this);

        mDatabase = FirebaseDatabase.getInstance().getReference(Constants.FIRBASE_TREE_DATA);

        adapter = new CustomizedListAdapter(this, treeList);
        treeListView.setAdapter(adapter);
        treeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TreeSession.getInstance().setTreeData(treeList.get(position));
                Intent intent = new Intent(TreeListFiltered.this, TreeHistory.class);
                startActivity(intent);
            }
        });

        mDatabase.orderByValue().limitToFirst(10).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    TreeData treeItem = child.getValue(TreeData.class);
                    treeItem.setTreeId(child.getKey());
                    treeList.add(treeItem);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
}
