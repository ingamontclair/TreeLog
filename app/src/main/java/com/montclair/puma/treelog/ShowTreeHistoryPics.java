package com.montclair.puma.treelog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.montclair.puma.treelog.models.TreeHistoryData;
import com.montclair.puma.treelog.models.TreeImages;
import com.montclair.puma.treelog.models.TreeSession;
import com.montclair.puma.treelog.utils.Constants;
import com.montclair.puma.treelog.utils.CustomizedTreeHistoryListAdapter;
import com.montclair.puma.treelog.utils.CustomizedTreeImagesListAdapter;
import com.montclair.puma.treelog.utils.FireBase;

import java.util.ArrayList;

public class ShowTreeHistoryPics extends AppCompatActivity {
private TreeHistoryData treeHistoryData;
    private String treeHistoryId;
    ListView treeListView;
    ChildEventListener mChildEventListener;
    DatabaseReference myRef;
    ArrayList<TreeImages> treeImageList = new ArrayList<TreeImages>();

    CustomizedTreeImagesListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tree_history_pics);

        treeListView=(ListView)findViewById(R.id.list_tree__history_images);
        treeHistoryData = TreeSession.getInstance().getTreeHistoryData(); //treeHistoryData with all fields ready to use
        //Toast below shows treHistoryID for selecting images related only to this history record

        treeHistoryId=treeHistoryData.getTreeHistoryID();

        adapter = new CustomizedTreeImagesListAdapter(this,R.layout.show_pic_row,treeImageList);
        treeListView.setAdapter(adapter);

        myRef = FireBase.getInstance().getFireBaseReference(Constants.FIRBASE_TREE_IMAGES);

       // Toast.makeText(this, treeHistoryData.getTreeHistoryID(), Toast.LENGTH_SHORT).show();
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                TreeImages treeItem = dataSnapshot.getValue(TreeImages.class);
                if(treeHistoryId.equals(treeItem.getTreeHistoryID())){
                    adapter.add(treeItem);
                    // Log.d("Tree Photo URI",treeItem.getTreeImageURL());
                     Log.d("Tree added","success");
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
        });

    }
}
