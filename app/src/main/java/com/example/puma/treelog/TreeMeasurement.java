package com.example.puma.treelog;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.puma.treelog.models.TreeData;
import com.example.puma.treelog.models.TreeSession;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TreeMeasurement extends AppCompatActivity {
    private EditText editTreeType; //edit for tree type (family), ListView will be called here
    private EditText editPlanted;
    private EditText editSpecies;
    private EditText editTreeDiametr;
    private EditText editTreeSize;
    private TreeData treeData;
    private DatePickerDialog dpDialog;
    private SimpleDateFormat dateFormat;
    private Button btn_next; //button to move to the next page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_measurement);
        TreeData treeData = TreeSession.getInstance().getTreeData();
        //treeData = (TreeData) getIntent().getSerializableExtra(EXTRA_TREE_DATA);
        editSpecies = (EditText)findViewById(R.id.edit_species);
        editTreeDiametr = (EditText)findViewById(R.id.edit_diametr);
        editTreeSize = (EditText)findViewById(R.id.edit_size);
        //List of Types
        editTreeType = (EditText) findViewById(R.id.edit_treetype);
        editTreeType.setInputType(InputType.TYPE_NULL);
        editTreeType.setOnClickListener(new TreeTypeLstr());

        /*Calendar for planted date*/
        Calendar newCalendar = Calendar.getInstance(); //defining Calendar object to connect it with DOB editText
        dpDialog = new DatePickerDialog(TreeMeasurement.this, new MyOnDateSetLstr(), newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        dateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.US);//defining date format data
        editPlanted = (EditText) findViewById(R.id.edit_planted);//defining editText by id to work with DOB data
        editPlanted.setInputType(InputType.TYPE_NULL);
        editPlanted.setOnClickListener(new PlantedLstr());
        btn_next = (Button)findViewById(R.id.btn_next_meas);
        btn_next.setOnClickListener(new MoveNextLstr());
        if (treeData != null) {
            editSpecies.setText(treeData.getSpecies());
            editTreeDiametr.setText(treeData.getTreeDiametr());
            editTreeSize.setText(treeData.getTreeSize());
            editPlanted.setText(treeData.getDatePlanted());
            editTreeType.setText(treeData.getTreeType());
        }
    }

    class TreeTypeLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            TreeData treeData = TreeSession.getInstance().getTreeData();
            treeData.setSpecies(editSpecies.getText().toString());
            treeData.setDatePlanted(editPlanted.getText().toString());
            treeData.setTreeDiametr(editTreeDiametr.getText().toString());
            treeData.setTreeSize(editTreeSize.getText().toString());
            Intent exIntent = new Intent(TreeMeasurement.this, TreeTypeList.class);
            startActivity(exIntent);
        }
    }

    class PlantedLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.edit_planted) {
                dpDialog.show();
            }
        }
    }

    //Method onDataSet sets up the picked Date to Planted Date
    class MyOnDateSetLstr implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Calendar newDate = Calendar.getInstance();
            newDate.set(year, monthOfYear, dayOfMonth);
            editPlanted.setText(dateFormat.format(newDate.getTime()));
        }

    }

    class MoveNextLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_next_meas) {
//TODO: validate all fields and show alerts
                TreeData treeData = TreeSession.getInstance().getTreeData();
                treeData.setSpecies(editSpecies.getText().toString());
                treeData.setTreeType(editTreeType.getText().toString());
                treeData.setDatePlanted(editPlanted.getText().toString());
                treeData.setTreeDiametr(editTreeDiametr.getText().toString());
                treeData.setTreeSize(editTreeSize.getText().toString());
                Intent intent = new Intent(TreeMeasurement.this, TreePit.class);
                startActivity(intent);
            }
        }
    }
}
