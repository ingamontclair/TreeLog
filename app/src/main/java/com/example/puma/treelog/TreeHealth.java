package com.example.puma.treelog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TreeHealth extends AppCompatActivity {
    private EditText editBiotic;
    private EditText editABiotic;
    private Button btnNextPicture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_health);
        editBiotic = (EditText)findViewById(R.id.editBiotic);
        editABiotic = (EditText)findViewById(R.id.editAbiotic);
        editBiotic.setInputType(InputType.TYPE_NULL);
        editABiotic.setInputType(InputType.TYPE_NULL);
        editBiotic.setOnClickListener(new BioticTypeLstr());
        editABiotic.setOnClickListener(new ABioticTypeLstr());
        btnNextPicture = (Button) findViewById(R.id.btn_health_next);
        btnNextPicture.setOnClickListener(new TreePictureLstr());
        Intent setHealthTree = getIntent();
        Bundle bundle = setHealthTree.getExtras();
        if (bundle != null) {
            String tmpBiotic = (String) bundle.get("bioticType");
            String tmpABiotic = (String) bundle.get("abioticType");
            editBiotic.setText(tmpBiotic);
            editABiotic.setText(tmpABiotic);
        }
    }

    class BioticTypeLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            //TODO: use serializable to save data from TreeHealthActivity for the next page, besides Biotic damage
            Intent exIntent = new Intent(TreeHealth.this, BioticTypeList.class);
            startActivity(exIntent);
        }
    }
    class ABioticTypeLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            //TODO: use serializable to save data from TreeHealthActivity for the next page, besides ABiotic damage
            Intent exIntent = new Intent(TreeHealth.this, AbioticTypeList.class);
            startActivity(exIntent);
        }
    }

    class TreePictureLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            //TODO: use serializable to save data from TreeHealthActivity for the next page
            Intent exIntent = new Intent(TreeHealth.this, TreePicture.class);
            startActivity(exIntent);
        }
    }
}
