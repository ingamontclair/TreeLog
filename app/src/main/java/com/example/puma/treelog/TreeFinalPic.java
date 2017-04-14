package com.example.puma.treelog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TreeFinalPic extends AppCompatActivity {
    private Button btnCommit;
    private TextView tvDateCreated;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_final_pic);
        TreeData treeData = TreeSession.getInstance().getTreeData();
        btnCommit = (Button)findViewById(R.id.btn_commit);
        btnCommit.setOnClickListener(new CommitLstr());
        tvDateCreated = (TextView)findViewById(R.id.tv_dateCreated);

        if (treeData != null) {

            tvDateCreated.setText(treeData.getDateCreated());

        }

    }

    class CommitLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_commit){
                TreeData treeData = TreeSession.getInstance().getTreeData();
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
                String dateCreated = df.format(c.getTime());
                treeData.setDateCreated(dateCreated);
//TODO: all TreeData is ready for commit - place for commit !!!

            }
        }
    }
}
