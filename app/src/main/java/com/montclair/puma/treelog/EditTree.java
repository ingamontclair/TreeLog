package com.montclair.puma.treelog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.montclair.puma.treelog.models.TreeData;
import com.montclair.puma.treelog.models.TreeSession;
import com.montclair.puma.treelog.utils.Constants;
import com.montclair.puma.treelog.utils.FireBase;
import com.google.firebase.database.DatabaseReference;

//Class to save essential tree data like species, location coordinates etc
public class EditTree extends AppCompatActivity {
    private EditText updateTreeName;
    private EditText updateTreeStreetAddress;
    private EditText updateTreeLatitude;
    private EditText updateTreeLongitude;
    private EditText updateTreeSpecies;
    private EditText updateTreeType;
    private EditText updateTreeProperty;
    private Button btnUpdateTreeData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tree);
        updateTreeName = (EditText)findViewById(R.id.update_tree_name);
        updateTreeStreetAddress = (EditText)findViewById(R.id.update_tree_street);
        updateTreeLatitude = (EditText)findViewById(R.id.update_latitude);
        updateTreeLongitude = (EditText)findViewById(R.id.update_longitude);
        updateTreeSpecies = (EditText)findViewById(R.id.update_species);
        updateTreeType = (EditText)findViewById(R.id.update_tree_type);
        updateTreeType.setInputType(InputType.TYPE_NULL);
        updateTreeType.setOnClickListener(new TreeTypeLstr());
        updateTreeProperty = (EditText)findViewById(R.id.update_property_type);
        updateTreeProperty.setInputType(InputType.TYPE_NULL);
        updateTreeProperty.setOnClickListener(new PropertyTypeLstr());
        btnUpdateTreeData = (Button) findViewById(R.id.btn_updateTree);
        btnUpdateTreeData.setOnClickListener(new UpdateTreeDataLstr());

        TreeData treeData = TreeSession.getInstance().getTreeData();
        if (treeData != null) {
            if (treeData.getTreeName() == null) {
                String treeNameGen = treeData.getSpecies() + " " + treeData.getStreetAddress();
                treeData.setTreeName(treeNameGen);
            }
            updateTreeName.setText(treeData.getTreeName());
            updateTreeStreetAddress.setText(treeData.getStreetAddress());
            updateTreeLatitude.setText(treeData.getLatitude());
            updateTreeLongitude.setText(treeData.getLongitude());
            updateTreeSpecies.setText(treeData.getSpecies());
            updateTreeType.setText(treeData.getTreeType());
            updateTreeProperty.setText(treeData.getPropertyType());
        }

    }

    class PropertyTypeLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            //use bean to save data from Location Activity for the next page, besides Property Type list
            TreeData treeData = TreeSession.getInstance().getTreeData();
            treeData.setTreeName(updateTreeName.getText().toString());
            treeData.setStreetAddress(updateTreeStreetAddress.getText().toString());
            treeData.setLatitude(updateTreeLatitude.getText().toString());
            treeData.setLongitude(updateTreeLongitude.getText().toString());
            treeData.setSpecies(updateTreeSpecies.getText().toString());
            treeData.setTreeType(updateTreeType.getText().toString());
            treeData.setPropertyType(updateTreeProperty.getText().toString());
            Intent intent = new Intent(EditTree.this, PropertyList.class);
            intent.putExtra("className", EditTree.class);
            startActivity(intent);
        }
    }

    class TreeTypeLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            TreeData treeData = TreeSession.getInstance().getTreeData();
            treeData.setTreeName(updateTreeName.getText().toString());
            treeData.setStreetAddress(updateTreeStreetAddress.getText().toString());
            treeData.setLatitude(updateTreeLatitude.getText().toString());
            treeData.setLongitude(updateTreeLongitude.getText().toString());
            treeData.setSpecies(updateTreeSpecies.getText().toString());
            treeData.setTreeType(updateTreeType.getText().toString());
            treeData.setPropertyType(updateTreeProperty.getText().toString());
            Intent exIntent = new Intent(EditTree.this, TreeTypeList.class);
            exIntent.putExtra("className", EditTree.class);
            startActivity(exIntent);
        }
    }

    class UpdateTreeDataLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_updateTree) {
                TreeData treeData = TreeSession.getInstance().getTreeData();
                treeData.setTreeName(updateTreeName.getText().toString());
                treeData.setStreetAddress(updateTreeStreetAddress.getText().toString());
                treeData.setLatitude(updateTreeLatitude.getText().toString());
                treeData.setLongitude(updateTreeLongitude.getText().toString());
                treeData.setSpecies(updateTreeSpecies.getText().toString());
                treeData.setTreeType(updateTreeType.getText().toString());
                treeData.setPropertyType(updateTreeProperty.getText().toString());
                DatabaseReference myref= FireBase.getInstance().getFireBaseReference(Constants.FIRBASE_TREE_DATA);
                DatabaseReference treeRef = myref.child(treeData.getTreeId());
                treeRef.setValue(treeData);
                Intent intent = new Intent(EditTree.this, TreeHistory.class);
                startActivity(intent);
            }

        }
    }
}
