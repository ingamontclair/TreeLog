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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TreeMeasurement extends AppCompatActivity {
    private EditText editTreeType; //edit for tree type (family), ListView will be called here
    private EditText editPlanted;
    private DatePickerDialog dpDialog;
    private SimpleDateFormat dateFormat;
    private Button btn_next; //button to move to the next page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_measurement);
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
        btn_next = (Button)findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new MoveNextLsatr());
        Intent treeMeasurment = getIntent();
        Bundle bundle = treeMeasurment.getExtras();
        if (bundle != null) {
            String tmpType = (String) bundle.get("treeType");
            editTreeType.setText(tmpType);
        }
    }

    class TreeTypeLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            //TODO: use serializable to save data from TreeMeas Activity for the next page etc
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

    class MoveNextLsatr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_next) {
//TODO: validate all fields and show alerts
//TODO: save to serializable and move to the next page
            }
        }
    }
}
