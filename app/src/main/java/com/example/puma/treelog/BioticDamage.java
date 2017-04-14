package com.example.puma.treelog;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BioticDamage extends AppCompatActivity {
    private ListView lv_biotic;
    private String[] bioticTypes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biotic_damage);
        bioticTypes=getResources().getStringArray(R.array.biotic_source);
        lv_biotic=(ListView)findViewById(R.id.list_biotic);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,bioticTypes);
        lv_biotic.setAdapter(adapter);
        lv_biotic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pstn, long l) {
                view.setBackgroundColor(Color.LTGRAY);
                Intent exIntent=new Intent(BioticDamage.this, TreeCondition.class);
                TreeData treeData = TreeSession.getInstance().getTreeData();
                treeData.setBioticDamage(bioticTypes[pstn]);
                startActivity(exIntent);
            }
        });
    }
}
