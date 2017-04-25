package com.example.puma.treelog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.puma.treelog.models.TreeData;
import com.example.puma.treelog.models.TreeHistoryData;
import com.example.puma.treelog.models.TreeSession;
import com.example.puma.treelog.utils.Constants;
import com.example.puma.treelog.utils.FireBase;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;

public class AddEvent extends AppCompatActivity {
    private HashMap<String,String> hazard = new HashMap<>();
    private TextView tvTreeName;
    private  TreeData treeData;
    private Button btnAddPics;
    private Button btnCommitEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        treeData = TreeSession.getInstance().getTreeData();
        tvTreeName = (TextView)findViewById(R.id.tv_event_treeName);
        tvTreeName.setText(treeData.getTreeName());

        btnAddPics = (Button)findViewById(R.id.btn_add_pics);
        btnCommitEvent = (Button)findViewById(R.id.btn_event_commit);
        btnCommitEvent.setOnClickListener(new CommitEventLstr());

    }

    public void onHazardClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        TreeData treeData = TreeSession.getInstance().getTreeData();
        switch(view.getId()) {
            case R.id.chb_balance:
                if (checked) {
                    hazard.put("Dangerous balance (more than 10 degree)","yes");
                }
                else {
                    hazard.remove("Dangerous balance (more than 10 degree)");
                }
                break;
            case R.id.chb_brokenBranches:
                if (checked) {
                    hazard.put("Broken or forked branches","yes");
                }
                else {
                    hazard.remove("Broken or forked branches");
                }
                break;
            case R.id.chb_decay:
                if (checked) {
                    hazard.put("Signs of Decay","yes");
                }
                else {
                    hazard.remove("Signs of Decay");
                }
                break;
            case R.id.chb_forkedTrunks:
                if (checked) {
                    hazard.put("Forked Trunks","yes");
                }
                else {
                    hazard.remove("Forked Trunks");
                }
                break;
            case R.id.chb_remove:
                if (checked) {
                    treeData.setRemove("Yes");
                }
                else {
                    treeData.setRemove("No");
                }
                break;
        }
    }

    class CommitEventLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_event_commit){
                TreeData treeData = TreeSession.getInstance().getTreeData();
                String treeID = treeData.getTreeId();
                String userID = "";
                String entryDate = "";
                String treeTrunkPicURL = "";
                String treeHistoryDiametr = "";
                String treeHistorySize = "";
                String treeHistoryBiotic= "";
                String treeHistory_a_Biotic= "";
                String treeHistoryPitURL = "";
                String treeHistoryPitComments = "";
                //get hazard from checked types
                String tmpHazard="";
                for (String s:hazard.keySet()) {
                    tmpHazard += s;
                    tmpHazard += ", ";
                }

                if (tmpHazard.length() > 0 ){
                    tmpHazard = tmpHazard.replaceFirst(", $","");
                }
                TreeHistoryData treeHistoryData =
                        new TreeHistoryData(treeID,null,null,null,null,null,null,null,null,null,tmpHazard);
                DatabaseReference myref= FireBase.getInstance().getFireBaseReference(Constants.FIRBASE_TREE_HISTORY_DATA);
                myref.push().setValue(treeHistoryData);
            }

        }
    }
}
