package com.montclair.puma.treelog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.montclair.puma.treelog.models.TreeData;
import com.montclair.puma.treelog.models.TreeHistoryData;
import com.montclair.puma.treelog.models.TreeImages;
import com.montclair.puma.treelog.models.TreeSession;
import com.montclair.puma.treelog.utils.BaseActivity;
import com.montclair.puma.treelog.utils.ChildEventListenerAdapter;
import com.montclair.puma.treelog.utils.Constants;
import com.montclair.puma.treelog.utils.CustomizedTreeHistoryListAdapter;

import java.util.ArrayList;
import java.util.List;


public class ShowHistory extends BaseActivity {
    private TreeData treeData;
    ArrayList<TreeHistoryData> treeHistoryDataList = new ArrayList<TreeHistoryData>();
    CustomizedTreeHistoryListAdapter adapter;
    private ListView treeHistoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_history);
        treeHistoryList = (ListView)findViewById(R.id.lv_history_list);
        treeData = TreeSession.getInstance().getTreeData();
        adapter = new CustomizedTreeHistoryListAdapter(this,R.layout.history_row,treeHistoryDataList);
        treeHistoryList.setAdapter(adapter);
        DatabaseReference refHistory = FirebaseDatabase.getInstance().getReference(Constants.FIRBASE_TREE_HISTORY_DATA);
        //Query search all history records for chosen Tree
        refHistory.orderByChild("treeID").equalTo(treeData.getTreeId()).addChildEventListener(
                new ChildEventListenerAdapter() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        //we  process the result on this firebase callback
                        TreeHistoryData value = dataSnapshot.getValue(TreeHistoryData.class);
                        //copy ID to treeHistoryID field (not updating table)
                        value.setTreeHistoryID(dataSnapshot.getKey());
                        adapter.add(value);

                    }
                }
        );

        treeHistoryList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //obtaining clicked position
                TreeSession.getInstance().setTreeHistoryData(treeHistoryDataList.get(position));
                //start new intent to show pics only related to this one history record
                Intent intent = new Intent(ShowHistory.this, ShowTreeHistoryPics.class);
                startActivity(intent);
            }
        });

    }

}
