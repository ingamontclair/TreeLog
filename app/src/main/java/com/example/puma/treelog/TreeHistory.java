package com.example.puma.treelog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.puma.treelog.models.TreeData;
import com.example.puma.treelog.models.TreeSession;

public class TreeHistory extends AppCompatActivity {
    private TextView tv_species;
    private TextView tv_tree_street_address;
    private TextView tv_tree_name;
    private TextView tv_date_planted;
    private TextView tv_property_type;
    private TextView tv_date_created;
    private TextView tv_date_modified;
    private TextView tv_vol_creator;
    private TextView tv_vol_modified;
    private TextView tv_tree_type;
    private TextView tv_tree_diametr;
    private TextView tv_tree_size;
    private TextView tv_remove;
    private TextView tv_biotic;
    private TextView tv_a_biotic;
    private TextView tv_hazard;
    private TextView tv_tree_pit_comments;
    private Button btnEdit;
    private Button btnPics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_history);
        TreeData treeData = TreeSession.getInstance().getTreeData();
        tv_species = (TextView)findViewById(R.id.tv_tree_species);
        tv_tree_street_address = (TextView)findViewById(R.id.tv_tree_address);
        tv_tree_name = (TextView)findViewById(R.id.tv_tree_name);
        tv_date_planted = (TextView)findViewById(R.id.tv_date_planted);
        tv_property_type = (TextView)findViewById(R.id.tv_property_type);
        tv_date_created = (TextView)findViewById(R.id.tv_date_created);
        tv_vol_creator = (TextView)findViewById(R.id.tv_vol_creator_name);
        tv_date_modified = (TextView)findViewById(R.id.tv_date_modyfied);
        tv_vol_modified = (TextView)findViewById(R.id.tv_modified_volunteer);
        tv_tree_type = (TextView)findViewById(R.id.tv_tree_type);
        tv_tree_diametr = (TextView)findViewById(R.id.tv_tree_diametr);
        tv_tree_size = (TextView)findViewById(R.id.tv_tree_size);
        tv_remove = (TextView)findViewById(R.id.tv_tree_remove);
        tv_biotic = (TextView)findViewById(R.id.tv_biotic_damage);
        tv_a_biotic = (TextView)findViewById(R.id.tv_a_biotic_damage);
        tv_hazard = (TextView)findViewById(R.id.tv_tree_hazard);
        tv_tree_pit_comments = (TextView)findViewById(R.id.tv_tree_pit);
        btnEdit = (Button) findViewById(R.id.btn_Edit_tree);
        btnEdit.setOnClickListener(new EditTreeLstr());

        btnPics = (Button)findViewById(R.id.btn_pics);
        btnPics.setOnClickListener(new ImagesListLstr());

        if (treeData != null) {
            tv_species.setText(treeData.getSpecies());
            tv_tree_street_address.setText(treeData.getStreetAddress());
            tv_tree_name.setText(treeData.getTreeName());
            tv_date_planted.setText(treeData.getDatePlanted());
            tv_property_type.setText(treeData.getPropertyType());
            tv_date_created.setText(treeData.getDateCreated());
            //tv_vol_creator.setText(treeData.);
            //tv_date_modified.setText(treeData.getD);
            //tv_vol_modified = (TextView)findViewById(R.id.tv_modified_volunteer);
            tv_tree_type.setText(treeData.getTreeType());
            tv_tree_diametr.setText(treeData.getTreeDiametr());
            tv_tree_size.setText(treeData.getTreeSize());
            tv_remove.setText(treeData.getRemove());
            tv_biotic.setText(treeData.getBioticDamage());
            tv_a_biotic.setText(treeData.getA_bioticDamage());
            tv_hazard.setText(treeData.getTreeHazard());
            tv_tree_pit_comments.setText(treeData.getTreePitComments());
        }
    }

    class EditTreeLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_Edit_tree){
                TreeData treeData = TreeSession.getInstance().getTreeData();
                Intent intent = new Intent (TreeHistory.this, LocateNewTree.class);
                startActivity(intent);
            }

        }
    }

    class ImagesListLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_pics){
                Intent intent = new Intent(TreeHistory.this, TreeImagesList.class);
                startActivity(intent);
            }
        }
    }
}
