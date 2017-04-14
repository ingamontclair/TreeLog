package com.example.puma.treelog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TreePit extends AppCompatActivity {
    private Button btnNext;
    private EditText editComments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_pit);
        TreeData treeData = TreeSession.getInstance().getTreeData();
        btnNext = (Button)findViewById(R.id.btn_pit_next);
        btnNext.setOnClickListener(new HealthNextLstr());
        editComments = (EditText)findViewById(R.id.edit_comments);
        if (treeData != null) {
            editComments.setText(treeData.getTreePitComments());

        }
    }

    class HealthNextLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_pit_next) {
                TreeData treeData = TreeSession.getInstance().getTreeData();
                treeData.setTreePitComments(editComments.getText().toString());
//TODO: validate all fields and show alerts
                Intent intent = new Intent(TreePit.this, TreeCondition.class);
                startActivity(intent);
            }
        }
    }
}
