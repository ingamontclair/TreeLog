package com.example.puma.treelog;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import static com.example.puma.treelog.LocateNewTree.EXTRA_TREE_DATA;

public class TreeTypeList extends AppCompatActivity {
    private ListView listView;
    private String[] treeTypes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_type_list);
        treeTypes=getResources().getStringArray(R.array.tree_type);
        listView=(ListView)findViewById(R.id.list_treetype);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,treeTypes);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pstn, long l) {
                view.setBackgroundColor(Color.LTGRAY);
                Intent exIntent=new Intent(TreeTypeList.this, TreeMeasurement.class);
                TreeData treeData = TreeSession.getInstance().getTreeData();
                treeData.setTreeType(treeTypes[pstn]);
                startActivity(exIntent);
            }
        });
    }
}
