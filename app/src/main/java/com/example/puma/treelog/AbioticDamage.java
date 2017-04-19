package com.example.puma.treelog;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.puma.treelog.models.TreeData;
import com.example.puma.treelog.models.TreeSession;

public class AbioticDamage extends AppCompatActivity {
    private ListView lv_abiotic;
    private String[] abioticTypes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abiotic_damage);
        abioticTypes=getResources().getStringArray(R.array.abiotic_source);
        lv_abiotic=(ListView)findViewById(R.id.list_abiotic);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,abioticTypes);
        lv_abiotic.setAdapter(adapter);
        lv_abiotic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pstn, long l) {
                view.setBackgroundColor(Color.LTGRAY);
                Intent exIntent=new Intent(AbioticDamage.this, TreeCondition.class);
                TreeData treeData = TreeSession.getInstance().getTreeData();
                treeData.setA_bioticDamage(abioticTypes[pstn]);
                startActivity(exIntent);
            }
        });

    }
}
