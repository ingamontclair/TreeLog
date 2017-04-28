package com.montclair.puma.treelog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.montclair.puma.treelog.models.TreeData;
import com.montclair.puma.treelog.models.TreeHistoryData;
import com.montclair.puma.treelog.models.TreeSession;
import com.montclair.puma.treelog.utils.ChildEventListenerAdapter;
import com.montclair.puma.treelog.utils.Constants;

import java.util.ArrayList;
import java.util.List;


public class ShowHistory extends AppCompatActivity {
    private DatabaseReference refTree;
    private TreeData treeData;
    private Button btnQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_history);
        treeData = TreeSession.getInstance().getTreeData();
        Toast.makeText(this, "treeID= " + treeData.getTreeId(), Toast.LENGTH_SHORT).show();
        refTree = FirebaseDatabase.getInstance().getReference(Constants.FIRBASE_TREE_DATA);
        btnQ = (Button) findViewById(R.id.btn_q);
        btnQ.setOnClickListener(new MyL());
    }


    class MyL implements View.OnClickListener {
        @Override
        public void onClick(View v) {
/*    ref.orderByChild("height").equalTo(25).on("child_added", function(snapshot) {
        console.log(snapshot.key);
    }*/
            final List<TreeHistoryData> historyList = new ArrayList<>();
            DatabaseReference refHistory = FirebaseDatabase.getInstance().getReference(Constants.FIRBASE_TREE_HISTORY_DATA);

            //"treeID" -> "-KiNsBnsBNr_2TOV0AwJ"
            refHistory.orderByChild("treeID").equalTo(treeData.getTreeId()).addChildEventListener(
                    new ChildEventListenerAdapter() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            historyList.add(dataSnapshot.getValue(TreeHistoryData.class));
                           // System.out.println();
                        }
                    }
            );

            //System.out.println("refHistory = " + historyList);
        }

    }

    ;
}
