package com.montclair.puma.treelog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.montclair.puma.treelog.models.TreeImages;
import com.montclair.puma.treelog.utils.CustomizedImagesListAdapter;

import java.util.ArrayList;

public class TreeImagesList extends AppCompatActivity {
    ListView treeImagesList;
    CustomizedImagesListAdapter adapter;
    ArrayList<TreeImages> treeImages = new ArrayList<TreeImages>(){
        {
            add(new TreeImages("pit image"));
            add(new TreeImages("trunk image"));
            add(new TreeImages("blooming tree image"));
            add(new TreeImages("green tree image"));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_images_list);
        treeImagesList = (ListView)findViewById(R.id.lv_tree_images);
        adapter = new CustomizedImagesListAdapter(this, treeImages);
        treeImagesList.setAdapter(adapter);
        treeImagesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(TreeImagesList.this, "List item clicked " + position, Toast.LENGTH_SHORT).show();
                //TreeSession.getInstance().setTreeData(treeImages.get(position));
                Intent intent = new Intent(TreeImagesList.this,BigPicture.class);
                startActivity(intent);
            }
        });
    }
}
