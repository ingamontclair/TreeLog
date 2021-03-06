package com.montclair.puma.treelog.utils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.montclair.puma.treelog.LoadingScreenActivity;
import com.montclair.puma.treelog.LocateNewTree;
import com.montclair.puma.treelog.Login;
import com.montclair.puma.treelog.R;
import com.montclair.puma.treelog.TakeTreePic;
import com.montclair.puma.treelog.TreeList;
import com.montclair.puma.treelog.TreeListFiltered;
import com.montclair.puma.treelog.WelcomeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.montclair.puma.treelog.models.TreeData;
import com.montclair.puma.treelog.models.TreeSession;

/**
 * Created by elionlimanaj on 4/26/17.
 */

public class BaseActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        Intent intent;
        switch (item.getItemId()) {
            case R.id.menu_welcome:
                //start activity welcome screen
                intent = new Intent(this, WelcomeActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_new_tree:
                //start activity to make a new tree
                TreeSession.getInstance().setTreeData(new TreeData()); //clear session
                intent = new Intent(this, LocateNewTree.class);
                startActivity(intent);
                //Toast.makeText(WelcomeActivity.this, "New tree clicked", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_list_tree:
                //start list tree activity in the fenced area
                intent = new Intent(this, TreeList.class);
                startActivity(intent);
                //Toast.makeText(WelcomeActivity.this, "Clicked List os trees", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_list_tree_filtered:
                //start list tree activity in the fenced area
                intent = new Intent(this, TreeListFiltered.class);
                startActivity(intent);
                //Toast.makeText(WelcomeActivity.this, "Clicked List os trees", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_make_photo:
                //load camera for making picture (quick access for making photos)
                intent = new Intent(this, TakeTreePic.class);
                startActivity(intent);
                //Toast.makeText(WelcomeActivity.this, "Clicked Camera", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_map:
                startActivity(new Intent(this, LoadingScreenActivity.class));
                return true;
            case R.id.menu_logout:
                FirebaseAuth.getInstance().signOut();
                finish();
                intent = new Intent(this, Login.class);
                startActivity(intent);
                return true;
            case R.id.menu_exit:
                moveTaskToBack(true);
                //android.os.Process.killProcess(android.os.Process.myPid());
                //System.exit(1);
                FirebaseAuth.getInstance().signOut();
                finish();
                System.exit(1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Shows a toast with the given text.
     */
    protected void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
