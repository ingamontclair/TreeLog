package com.example.puma.treelog;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.puma.treelog.models.TreeData;
import com.example.puma.treelog.models.TreeImageData;
import com.example.puma.treelog.models.TreeSession;

import java.io.IOException;
import java.util.ArrayList;

public class TreeHistory extends AppCompatActivity {
    private TextView tv_species;
    private TextView tv_tree_street_address;
    private TextView tv_tree_name;
    private TextView tv_date_planted;
    private TextView tv_property_type;
    private TextView tv_date_created;
    private TextView tv_date_modified;
    private TextView tv_vol_creator;
    private TextView tv_vol_modified;
    private TextView tv_tree_type;
    private TextView tv_tree_diametr;
    private TextView tv_tree_size;
    private TextView tv_remove;
    private TextView tv_biotic;
    private TextView tv_a_biotic;
    private TextView tv_hazard;
    private TextView tv_tree_pit_comments;
    private Button btnEdit;
    private Button btnPics;
    private Button btnAddPics;
    private Button btnMaps;

    public final static int PICK_PHOTO_CODE = 1046;
    Button buttonLoadImage;
    ArrayList mArrayUri, mBitmapsSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_history);
        TreeData treeData = TreeSession.getInstance().getTreeData();
        tv_species = (TextView)findViewById(R.id.tv_tree_species);
        tv_tree_street_address = (TextView)findViewById(R.id.tv_tree_address);
        tv_tree_name = (TextView)findViewById(R.id.tv_tree_name);
        tv_date_planted = (TextView)findViewById(R.id.tv_date_planted);
        tv_property_type = (TextView)findViewById(R.id.tv_property_type);
        tv_date_created = (TextView)findViewById(R.id.tv_date_created);
        tv_vol_creator = (TextView)findViewById(R.id.tv_vol_creator_name);
//        tv_date_modified = (TextView)findViewById(R.id.tv_date_modyfied);
//        tv_vol_modified = (TextView)findViewById(R.id.tv_modified_volunteer);
        tv_tree_type = (TextView)findViewById(R.id.tv_tree_type);
        tv_tree_diametr = (TextView)findViewById(R.id.tv_tree_diametr);
        tv_tree_size = (TextView)findViewById(R.id.tv_tree_size);
        tv_remove = (TextView)findViewById(R.id.tv_tree_remove);
        tv_biotic = (TextView)findViewById(R.id.tv_biotic_damage);
        tv_a_biotic = (TextView)findViewById(R.id.tv_a_biotic_damage);
        tv_hazard = (TextView)findViewById(R.id.tv_tree_hazard);
        tv_tree_pit_comments = (TextView)findViewById(R.id.tv_tree_pit);
        btnEdit = (Button) findViewById(R.id.btn_Edit_tree);
        btnEdit.setOnClickListener(new EditTreeLstr());

        btnMaps = (Button) findViewById(R.id.btn_map);
        btnMaps.setOnClickListener(new MapBtnLstr());

        btnPics = (Button)findViewById(R.id.btn_pics);
        btnPics.setOnClickListener(new ImagesListLstr());

        btnAddPics= (Button)findViewById(R.id.btn_add_pics);
        btnAddPics.setOnClickListener(new AddPicsLstr());

        if (treeData != null) {
            tv_species.setText(treeData.getSpecies());
            tv_tree_street_address.setText(treeData.getStreetAddress());
            tv_tree_name.setText(treeData.getTreeName());
            tv_date_planted.setText(treeData.getDatePlanted());
            tv_property_type.setText(treeData.getPropertyType());
            tv_date_created.setText(treeData.getDateCreated());
            //tv_vol_creator.setText(treeData.);
            //tv_date_modified.setText(treeData.getD);
            //tv_vol_modified = (TextView)findViewById(R.id.tv_modified_volunteer);
            tv_tree_type.setText(treeData.getTreeType());
            tv_tree_diametr.setText(treeData.getTreeDiametr());
            tv_tree_size.setText(treeData.getTreeSize());
            tv_remove.setText(treeData.getRemove());
            tv_biotic.setText(treeData.getBioticDamage());
            tv_a_biotic.setText(treeData.getA_bioticDamage());
            tv_hazard.setText(treeData.getTreeHazard());
            tv_tree_pit_comments.setText(treeData.getTreePitComments());
        }
    }

    class EditTreeLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_Edit_tree){
                TreeData treeData = TreeSession.getInstance().getTreeData();
                Intent intent = new Intent (TreeHistory.this, LocateNewTree.class);
                startActivity(intent);
            }

        }
    }

    class ImagesListLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_pics){
                Intent intent = new Intent(TreeHistory.this, TreeImagesList.class);
                startActivity(intent);
            }
        }
    }

    class AddPicsLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_add_pics){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PHOTO_CODE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        if (data.getClipData() != null) {
            ClipData mClipData = data.getClipData();
            mArrayUri = new ArrayList<Uri>();
            mBitmapsSelected = new ArrayList<Bitmap>();
            for (int i = 0; i < mClipData.getItemCount(); i++) {
                ClipData.Item item = mClipData.getItemAt(i);
                Uri uri = item.getUri();
                mArrayUri.add(uri);


                // !! You may need to resize the image if it's too large
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mBitmapsSelected.add(bitmap);
            }



            Log.d("Images count",""+mArrayUri.size()+"Bit map Images :"+mBitmapsSelected.size());

        }

        //TreeImageData treeImageData=TreeSession.getInstance().getTreeImageData();

        TreeSession treeSession=TreeSession.getInstance();
        TreeImageData treeImageData=new TreeImageData();

        treeImageData.setTreeImages(mBitmapsSelected);
        treeImageData.setTreeImmageUri(mArrayUri);

        treeSession.setTreeImageData(treeImageData);
        Intent intent=new Intent(TreeHistory.this,AddPics.class);
        //intent.putStringArrayListExtra("URIList",mArrayUri);
        //intent.putStringArrayListExtra("selectedImages",mBitmapsSelected);
        startActivity(intent);
    }

    class MapBtnLstr implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btn_map) {
                startActivity(new Intent(TreeHistory.this, LoadingScreenActivity.class)
                        .putExtra("address", tv_tree_street_address.getText().toString()));
            }
        }
    }
}
