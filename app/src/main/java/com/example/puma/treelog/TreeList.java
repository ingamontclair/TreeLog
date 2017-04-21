package com.example.puma.treelog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.puma.treelog.models.TreeData;
import com.example.puma.treelog.models.TreeSession;
import com.example.puma.treelog.utils.CustomizedListAdapter;

import java.util.ArrayList;

public class TreeList extends AppCompatActivity {
    ListView treeListView;
    CustomizedListAdapter adapter;
    ArrayList<TreeData> treeList = new ArrayList<TreeData>(){
        {
            add(new TreeData("1600 Pennsylvania Avenue","private","Evergreen","Pine123"));
            add(new TreeData("Edgewater rd","city","Fruit tree","Apple567"));
            add(new TreeData("7119 Shore rd. Brooklyn","private","Deciduous Tree","Willow789"));
            add(new TreeData("388 Greenwich st. New York","city","Fruit tree","Peach123"));
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_list);
        treeListView = (ListView)findViewById(R.id.lv_tree_list);
        adapter=new CustomizedListAdapter(this, treeList);
        treeListView.setAdapter(adapter);
        treeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Toast.makeText(TreeList.this, "List item clicked " + position, Toast.LENGTH_SHORT).show();
                TreeSession.getInstance().setTreeData(treeList.get(position));
                Intent intent = new Intent(TreeList.this,TreeHistory.class);
                startActivity(intent);
            }
        });
    }
}