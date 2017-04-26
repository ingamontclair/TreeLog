package com.example.puma.treelog;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import com.example.puma.treelog.utils.BaseActivity;

public class LoadingScreenActivity extends BaseActivity {
    private final int DELAY = 3000;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        Intent intent = getIntent();
        final String address = intent.getStringExtra("address");
        final String lat = intent.getStringExtra("latitude");
        final String lon = intent.getStringExtra("longitude");

        progressBar = (ProgressBar) findViewById(R.id.spinner);
        progressBar.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent mainIntent = new Intent(LoadingScreenActivity.this, MapsActivity.class);
                mainIntent.putExtra("address", address);
                mainIntent.putExtra("latitude", lat);
                mainIntent.putExtra("longitude", lon);
                LoadingScreenActivity.this.startActivity(mainIntent);
                LoadingScreenActivity.this.finish();
            }
        }, DELAY);
    }
}
