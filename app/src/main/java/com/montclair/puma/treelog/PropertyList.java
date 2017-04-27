package com.montclair.puma.treelog;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.montclair.puma.treelog.models.TreeData;
import com.montclair.puma.treelog.models.TreeSession;

public class PropertyList extends AppCompatActivity {
    private ListView listView;
    private String[] propertyTypes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_list);
        Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();
        propertyTypes=getResources().getStringArray(R.array.property_type);
        listView=(ListView)findViewById(R.id.list_property);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,propertyTypes);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pstn, long l) {
                TreeData treeData = TreeSession.getInstance().getTreeData();
                view.setBackgroundColor(Color.LTGRAY);
                Class tmpClass = (Class) bundle.get("className"); //getting classname to call activity to return to
                Intent exIntent=new Intent(PropertyList.this, tmpClass);
                treeData.setPropertyType(propertyTypes[pstn]);
                startActivity(exIntent);
            }
        });
    }
}
