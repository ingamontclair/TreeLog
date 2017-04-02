package com.example.puma.treelog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LocateNewTree extends AppCompatActivity {
    private Button btnLocate; //button to call GPS snapping
    private Button btnNextLocate; //button to go to the next page after Locating New Tree
    private EditText editPropertyType; //edit for property type, ListView will be called here
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate_new_tree);
        btnLocate = (Button)findViewById(R.id.btn_locate);
        btnNextLocate = (Button)findViewById(R.id.btn_next_locate);
        btnLocate.setOnClickListener(new LocateLstr());
        btnNextLocate.setOnClickListener(new LocationDataLstr());
        editPropertyType = (EditText)findViewById(R.id.edit_property);
        editPropertyType.setInputType(InputType.TYPE_NULL);
        editPropertyType.setOnClickListener(new PropertyTypeLstr());
        Intent locateNewTree = getIntent();
        Bundle bundle = locateNewTree.getExtras();
        if (bundle != null) {
            String tmpProperty = (String) bundle.get("propertyType");
            editPropertyType.setText(tmpProperty);
        }
    }

    class LocateLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            if (view.getId() == R.id.btn_locate) {
                Toast.makeText(LocateNewTree.this, "Locate clicked", Toast.LENGTH_LONG).show();
//TODO: call main method for obtaining coordinates and all validations, show alerts if necessarily
            }
        }
    }

    class LocationDataLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_next_locate) {
                Toast.makeText(LocateNewTree.this, "Next page clicked", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LocateNewTree.this, LeafIdentification.class);
                startActivity(intent);
//TODO: validate all fields for not being empty
//TODO: putexptra or serializable to save the obtained data for the next page
            }
        }
    }

    class PropertyTypeLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            //TODO: use serializable to save data from Location Activity for the next page, besides Property Type list
            Intent exIntent = new Intent(LocateNewTree.this, PropertyList.class);
            startActivity(exIntent);
        }
    }
}
