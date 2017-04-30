package com.montclair.puma.treelog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.montclair.puma.treelog.models.TreeHistoryData;
import com.montclair.puma.treelog.models.TreeSession;

public class ShowTreeHistoryPics extends AppCompatActivity {
private TreeHistoryData treeHistoryData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tree_history_pics);
        treeHistoryData = TreeSession.getInstance().getTreeHistoryData(); //treeHistoryData with all fields ready to use
        //Toast below shows treHistoryID for selecting images related only to this history record
        Toast.makeText(this, treeHistoryData.getTreeHistoryID(), Toast.LENGTH_SHORT).show();
    }
}
