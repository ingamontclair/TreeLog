package com.montclair.puma.treelog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.montclair.puma.treelog.models.TreeData;
import com.montclair.puma.treelog.models.TreeHistoryData;
import com.montclair.puma.treelog.models.TreeSession;
import com.montclair.puma.treelog.models.User;
import com.montclair.puma.treelog.utils.Constants;
import com.montclair.puma.treelog.utils.FireBase;
import com.google.firebase.database.DatabaseReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class AddEvent extends AppCompatActivity {
    private HashMap<String, String> hazard = new HashMap<>();
    private TextView tvTreeName;
    private TreeData treeData;
    private Button btnAddPics;
    private Button btnCommitEvent;
    private EditText editEventDiametr;
    private EditText editEventSize;
    private EditText editEventBiotic;
    private EditText editEventAbiotic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        treeData = TreeSession.getInstance().getTreeData();
        tvTreeName = (TextView) findViewById(R.id.tv_event_treeName);
        tvTreeName.setText(treeData.getTreeName());

        btnAddPics = (Button) findViewById(R.id.btn_add_pics);
        btnCommitEvent = (Button) findViewById(R.id.btn_event_commit);
        btnCommitEvent.setOnClickListener(new CommitEventLstr());

        editEventDiametr = (EditText) findViewById(R.id.edit_event_diametr);
        editEventSize = (EditText) findViewById(R.id.edit_event_size);

        editEventBiotic = (EditText) findViewById(R.id.edit_event_biotic);
        editEventAbiotic = (EditText) findViewById(R.id.edit_event_a_biotic);

        editEventBiotic.setInputType(InputType.TYPE_NULL);
        editEventAbiotic.setInputType(InputType.TYPE_NULL);

        editEventBiotic.setOnClickListener(new BioticTypeLstr());
        editEventAbiotic.setOnClickListener(new ABioticTypeLstr());

       // TreeData treeData = TreeSession.getInstance().getTreeData();

        TreeHistoryData treeHistoryData = TreeSession.getInstance().getTreeHistoryData();
        if (treeHistoryData == null){
            treeHistoryData = new TreeHistoryData();
            TreeSession.getInstance().setTreeHistoryData(treeHistoryData);
        }
        if (treeHistoryData != null) {
            editEventDiametr.setText(treeHistoryData.getTreeHistoryDiametr());
            editEventSize.setText(treeHistoryData.getTreeHistorySize());
            editEventBiotic.setText(treeHistoryData.getTreeHistoryBiotic());
            editEventAbiotic.setText(treeHistoryData.getTreeHistory_a_Biotic());
        }

    }

    public void onHazardClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        TreeData treeData = TreeSession.getInstance().getTreeData();
        switch (view.getId()) {
            case R.id.chb_balance:
                if (checked) {
                    hazard.put("Dangerous balance (more than 10 degree)", "yes");
                } else {
                    hazard.remove("Dangerous balance (more than 10 degree)");
                }
                break;
            case R.id.chb_brokenBranches:
                if (checked) {
                    hazard.put("Broken or forked branches", "yes");
                } else {
                    hazard.remove("Broken or forked branches");
                }
                break;
            case R.id.chb_decay:
                if (checked) {
                    hazard.put("Signs of Decay", "yes");
                } else {
                    hazard.remove("Signs of Decay");
                }
                break;
            case R.id.chb_forkedTrunks:
                if (checked) {
                    hazard.put("Forked Trunks", "yes");
                } else {
                    hazard.remove("Forked Trunks");
                }
                break;
            case R.id.chb_remove:
                if (checked) {
                    treeData.setRemove("Yes");
                } else {
                    treeData.setRemove("No");
                }
                break;
        }
    }

    class BioticTypeLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            TreeData treeData = TreeSession.getInstance().getTreeData();
            TreeHistoryData treeHistoryData = TreeSession.getInstance().getTreeHistoryData();
            treeHistoryData.setTreeID(treeData.getTreeId());
            treeHistoryData.setTreeHistoryDiametr(editEventDiametr.getText().toString());
            treeHistoryData.setTreeHistorySize(editEventSize.getText().toString());
            treeHistoryData.setTreeHistoryBiotic(editEventBiotic.getText().toString());
            treeHistoryData.setTreeHistory_a_Biotic(editEventAbiotic.getText().toString());
            Intent exIntent = new Intent(AddEvent.this, BioticDamage.class);
            exIntent.putExtra("className", AddEvent.class);
            startActivity(exIntent);
        }
    }
    class ABioticTypeLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            TreeData treeData = TreeSession.getInstance().getTreeData();
            TreeHistoryData treeHistoryData = TreeSession.getInstance().getTreeHistoryData();
            treeHistoryData.setTreeID(treeData.getTreeId());
            treeHistoryData.setTreeHistoryDiametr(editEventDiametr.getText().toString());
            treeHistoryData.setTreeHistorySize(editEventSize.getText().toString());
            treeHistoryData.setTreeHistoryBiotic(editEventBiotic.getText().toString());
            treeHistoryData.setTreeHistory_a_Biotic(editEventAbiotic.getText().toString());
            Intent exIntent = new Intent(AddEvent.this, AbioticDamage.class);
            exIntent.putExtra("className", AddEvent.class);
            startActivity(exIntent);

        }
    }

    class CommitEventLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_event_commit) {
                TreeData treeData = TreeSession.getInstance().getTreeData();
                //TreeHistoryData treeHistoryData = new TreeHistoryData();
                TreeHistoryData treeHistoryData = TreeSession.getInstance().getTreeHistoryData();
                User user = TreeSession.getInstance().getUser();
                treeHistoryData.setTreeID(treeData.getTreeId());
                treeHistoryData.setUserID(user.getUserID());
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
                String dateModyfied = df.format(c.getTime());
                treeHistoryData.setEntryDate(dateModyfied);
                treeHistoryData.setTreeTrunkPicURL("");
                treeHistoryData.setTreeHistoryDiametr(editEventDiametr.getText().toString());
                treeHistoryData.setTreeHistoryDiametr(editEventSize.getText().toString());
                treeHistoryData.setTreeHistoryBiotic(editEventBiotic.getText().toString());
                treeHistoryData.setTreeHistory_a_Biotic(editEventAbiotic.getText().toString());
                treeHistoryData.setTreeHistoryPitURL("");
                treeHistoryData.setTreeHistoryPitComments("");
                //get hazard from checked types
                String tmpHazard = "";
                for (String s : hazard.keySet()) {
                    tmpHazard += s;
                    tmpHazard += ", ";
                }

                if (tmpHazard.length() > 0) {
                    tmpHazard = tmpHazard.replaceFirst(", $", "");
                }
                treeHistoryData.setTreeHistoryHazard(tmpHazard);

                DatabaseReference myref = FireBase.getInstance().getFireBaseReference(Constants.FIRBASE_TREE_HISTORY_DATA);
                myref.push().setValue(treeHistoryData);
                Toast.makeText(AddEvent.this, "New record in history was added", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddEvent.this, TreeHistory.class);
                startActivity(intent);
            }

        }
    }
}
