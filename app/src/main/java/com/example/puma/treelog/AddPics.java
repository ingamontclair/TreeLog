package com.example.puma.treelog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.puma.treelog.models.TreeImages;
import com.example.puma.treelog.utils.CustomizedImagesListAdapter;
import com.example.puma.treelog.utils.CustomizedNewPicsAdapter;

import java.util.ArrayList;

public class AddPics extends AppCompatActivity {
    ListView treeNewImagesList;
    CustomizedNewPicsAdapter adapter;
    ArrayList<TreeImages> treeImages = new ArrayList<TreeImages>(){
        {
            add(new TreeImages("pit image"));
            add(new TreeImages("trunk image"));
            add(new TreeImages("blooming tree image"));
            add(new TreeImages("green tree image"));
            add(new TreeImages("pit image"));
            add(new TreeImages("trunk image"));
            add(new TreeImages("blooming tree image"));
            add(new TreeImages("green tree image"));
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pics);
        treeNewImagesList = (ListView)findViewById(R.id.list_new_pics);
        adapter = new CustomizedNewPicsAdapter(this, treeImages);
        treeNewImagesList.setAdapter(adapter);
/*        treeNewImagesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(AddPics.this, "Image item clicked " + position, Toast.LENGTH_SHORT).show();
                //TreeSession.getInstance().setTreeData(treeImages.get(position));
                //Intent intent = new Intent(TreeImagesList.this,BigPicture.class);
                //startActivity(intent);
            }
        });*/
    }

}