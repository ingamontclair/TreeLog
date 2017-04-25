package com.example.puma.treelog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.puma.treelog.models.TreeData;
import com.example.puma.treelog.models.TreeSession;

import java.util.HashMap;

public class TreeCondition extends AppCompatActivity {
    private Button btnNextPicture;
    private EditText editBiotic;
    private EditText editABiotic;
    private HashMap<String,String> hazard = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_condition);
        btnNextPicture = (Button) findViewById(R.id.btn_health_next);
        btnNextPicture.setOnClickListener(new TreePictureLstr());
        editBiotic = (EditText) findViewById(R.id.editBiotic);
        editABiotic = (EditText) findViewById(R.id.editAbiotic);
        editBiotic.setInputType(InputType.TYPE_NULL);
        editABiotic.setInputType(InputType.TYPE_NULL);

        editBiotic.setOnClickListener(new BioticTypeLstr());
        editABiotic.setOnClickListener(new ABioticTypeLstr());

        btnNextPicture = (Button) findViewById(R.id.btn_health_next);
        btnNextPicture.setOnClickListener(new TreePictureLstr());
        TreeData treeData = TreeSession.getInstance().getTreeData();
        if (treeData != null) {

            editBiotic.setText(treeData.getBioticDamage());
            editABiotic.setText(treeData.getA_bioticDamage());
        }
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

    class BioticTypeLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            TreeData treeData = TreeSession.getInstance().getTreeData();
            treeData.setBioticDamage(editBiotic.getText().toString());
                Intent exIntent = new Intent(TreeCondition.this, BioticDamage.class);
                exIntent.putExtra("className", TreeCondition.class);
                startActivity(exIntent);
        }
    }
    class ABioticTypeLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            TreeData treeData = TreeSession.getInstance().getTreeData();
            treeData.setA_bioticDamage(editABiotic.getText().toString());
                Intent exIntent = new Intent(TreeCondition.this, AbioticDamage.class);
            exIntent.putExtra("className", TreeCondition.class);
                startActivity(exIntent);

        }
    }

    class TreePictureLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_health_next) {
                TreeData treeData = TreeSession.getInstance().getTreeData();
                treeData.setA_bioticDamage(editABiotic.getText().toString());
                treeData.setBioticDamage(editBiotic.getText().toString());
                String tmpHazard="";
                for (String s:hazard.keySet()) {
                    tmpHazard += s;
                    tmpHazard += ", ";
                }

                if (tmpHazard.length() > 0 ){
                    tmpHazard = tmpHazard.replaceFirst(", $","");
                }
                treeData.setTreeHazard(tmpHazard);
                Intent intent = new Intent(TreeCondition.this, TreeFinalPic.class);
                startActivity(intent);
            }
        }
    }
}
