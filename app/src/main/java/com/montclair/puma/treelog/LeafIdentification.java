package com.montclair.puma.treelog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LeafIdentification extends AppCompatActivity {
    private Button btnUploadLeaf; //button to call leaf recognition module
    private Button btnProceed; //button to proceed with obtained data
    private Button btnManual; //button sor simple manual tree info entering
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaf_identification);

        btnUploadLeaf = (Button)findViewById(R.id.btn_upload_leaf);
        btnUploadLeaf.setOnClickListener(new LeafRecognitionLstr());

        btnProceed = (Button)findViewById(R.id.btn_proceed);
        btnManual = (Button)findViewById(R.id.btn_manual);
        btnProceed.setOnClickListener(new InfoEnterLsrt());
        btnManual.setOnClickListener(new InfoEnterLsrt());
    }

    class LeafRecognitionLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_upload_leaf) {
                //TODO: call photo upload funtion
                //TODO: call LeafSnap integrated module or Google API or whaterever works
            }

        }
    }

    class InfoEnterLsrt implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_proceed) {
                //TODO: put obtained info about recognition into serializable
                //TODO: transfer data to the next forms with uploaded photo
                Intent  intent = new Intent(LeafIdentification.this, TreeMeasurement.class);
                startActivity(intent);
            } else if (view.getId() == R.id.btn_manual) {
                //TODO: start next activity and save uploaded leaf photo
                Intent  intent = new Intent(LeafIdentification.this, TreeMeasurement.class);
                startActivity(intent);
            }
        }
    }
}