package com.example.puma.treelog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TreePit extends AppCompatActivity {
    private Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_pit);

        btnNext = (Button)findViewById(R.id.btn_pit_next);
        btnNext.setOnClickListener(new HealthNextLstr());
    }

    class HealthNextLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_pit_next) {
//TODO: validate all fields and show alerts
//TODO: save to serializable and move to the next page
                Intent intent = new Intent(TreePit.this, TreeHealth.class);
                startActivity(intent);
            }
        }
    }
}
