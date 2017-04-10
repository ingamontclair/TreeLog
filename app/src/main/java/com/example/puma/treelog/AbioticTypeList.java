package com.example.puma.treelog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AbioticTypeList extends AppCompatActivity {
    private ListView lv_abiotic;
    private String[] abioticTypes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abiotic_type_list);
        abioticTypes=getResources().getStringArray(R.array.abiotic_source);
        lv_abiotic=(ListView)findViewById(R.id.list_abiotic);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,abioticTypes);
        lv_abiotic.setAdapter(adapter);
        lv_abiotic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pstn, long l) {
                //view.setBackgroundColor(Color.LTGRAY);
                Intent exIntent=new Intent(AbioticTypeList.this, TreeHealth.class);
                //RegistrationData registrationData = (RegistrationData) getIntent().getSerializableExtra(EXTRA_REG_DATA);
                //registrationData.setMajor(myItems[pstn]);
                exIntent.putExtra("abioticType",abioticTypes[pstn]);
                startActivity(exIntent);
            }
        });
    }
}
