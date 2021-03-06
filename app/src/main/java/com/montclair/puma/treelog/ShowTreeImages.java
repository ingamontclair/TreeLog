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
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.montclair.puma.treelog.models.TreeData;
import com.montclair.puma.treelog.models.TreeImages;
import com.montclair.puma.treelog.models.TreeSession;
import com.montclair.puma.treelog.utils.BaseActivity;
import com.montclair.puma.treelog.utils.Constants;
import com.montclair.puma.treelog.utils.CustomizedListAdapter;
import com.montclair.puma.treelog.utils.CustomizedNewPicsAdapter;
import com.montclair.puma.treelog.utils.CustomizedTreeImagesListAdapter;
import com.montclair.puma.treelog.utils.FireBase;

import java.util.ArrayList;
import java.util.List;

public class ShowTreeImages extends BaseActivity {

    private String treeId;
    ListView treeListView;
    CustomizedTreeImagesListAdapter adapter;
    ChildEventListener mChildEventListener;
    DatabaseReference myRef;
    ArrayList<TreeImages> treeImageList = new ArrayList<TreeImages>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tree_images);

        treeListView=(ListView)findViewById(R.id.list_tree__extra_images);
        treeId= TreeSession.getInstance().getTreeData().getTreeId();
        //Log.d("Tree id :",treeId);

        adapter = new CustomizedTreeImagesListAdapter(this,R.layout.show_pic_row,treeImageList);
        treeListView.setAdapter(adapter);

        myRef = FireBase.getInstance().getFireBaseReference(Constants.FIRBASE_TREE_IMAGES);


        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                TreeImages treeItem = dataSnapshot.getValue(TreeImages.class);
                treeItem.setTreeImageID(dataSnapshot.getKey());
                //Log.d("Tree Id in ",""+treeItem.getTreeID());
                if(treeId.equals(treeItem.getTreeID())){
                    adapter.add(treeItem);
                   // Log.d("Tree Photo URI",treeItem.getTreeImageURL());
                   // Log.d("Tree added","success");
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
