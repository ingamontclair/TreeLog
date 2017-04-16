package com.example.puma.treelog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {
    private Button btnLocateME; //button for fencing?
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        btnLocateME = (Button)findViewById(R.id.btn_locateMe);
        btnLocateME.setOnClickListener(new LocateMELstr());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        // Handle item selection
        Intent intent;
        switch (item.getItemId()) {
            case R.id.menu_new_tree:
                //start activity to make a new tree
                intent = new Intent(WelcomeActivity.this, LocateNewTree.class);
                startActivity(intent);
                Toast.makeText(WelcomeActivity.this, "New tree clicked", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_list_tree:
                //TODO: start form with filter for listing trees
                Toast.makeText(WelcomeActivity.this, "Clicked List os trees", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_make_photo:
                //TODO: load camera for making picture (quick access for making photos)
                intent=new Intent(WelcomeActivity.this,TakeTreePic.class);
                startActivity(intent);
                Toast.makeText(WelcomeActivity.this, "Clicked Camera", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_map:
                intent = new Intent(WelcomeActivity.this, MapsActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_exit:
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    class LocateMELstr implements View.OnClickListener {
        //TODO: make a circle or fence which depends on a user location before starting all new/edit/delete etc functions
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_locateMe){
                //TODO: place fencing code here
                //TODO: place map with user location and icons of the trees around (without filters)
            }

        }
    }
}
