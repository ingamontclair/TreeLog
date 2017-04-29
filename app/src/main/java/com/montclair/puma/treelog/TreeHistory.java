package com.montclair.puma.treelog;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.montclair.puma.treelog.models.TreeData;
import com.montclair.puma.treelog.models.TreeImageData;
import com.montclair.puma.treelog.models.TreeSession;
import com.montclair.puma.treelog.utils.BaseActivity;
import com.montclair.puma.treelog.utils.Constants;
import com.montclair.puma.treelog.utils.FireBase;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

public class TreeHistory extends BaseActivity {
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
    //private Button btnPics;
    private Button btnAddEvent;
    private Button btnShowHistory;
    private Button btnShowPics;
    private Button btnMaps;
    private Button btnDeleteTree;
    private ImageView img_main_pic;

    public final static int PICK_PHOTO_CODE = 1046;
    Button buttonLoadImage;
    ArrayList mArrayUri, mBitmapsSelected;
    private TreeData treeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_history);
        treeData = TreeSession.getInstance().getTreeData();
        tv_species = (TextView) findViewById(R.id.tv_tree_species);
        tv_tree_street_address = (TextView) findViewById(R.id.tv_tree_address);
        tv_tree_name = (TextView) findViewById(R.id.tv_tree_name);
        tv_date_planted = (TextView) findViewById(R.id.tv_date_planted);
        tv_property_type = (TextView) findViewById(R.id.tv_property_type);
        tv_date_created = (TextView) findViewById(R.id.tv_date_created);
        tv_vol_creator = (TextView) findViewById(R.id.tv_vol_creator_name);
//        tv_date_modified = (TextView)findViewById(R.id.tv_date_modyfied);
//        tv_vol_modified = (TextView)findViewById(R.id.tv_modified_volunteer);
        tv_tree_type = (TextView) findViewById(R.id.tv_tree_type);
        tv_tree_diametr = (TextView) findViewById(R.id.tv_tree_diametr);
        tv_tree_size = (TextView) findViewById(R.id.tv_tree_size);
        tv_remove = (TextView) findViewById(R.id.tv_tree_remove);
        tv_biotic = (TextView) findViewById(R.id.tv_biotic_damage);
        tv_a_biotic = (TextView) findViewById(R.id.tv_a_biotic_damage);
        tv_hazard = (TextView) findViewById(R.id.tv_tree_hazard);
        tv_tree_pit_comments = (TextView) findViewById(R.id.tv_tree_pit);
        img_main_pic = (ImageView) findViewById(R.id.iv_main_picture);

        btnShowPics=(Button)findViewById(R.id.btn_pics);
        btnShowPics.setOnClickListener(new showTreePics());

        btnEdit = (Button) findViewById(R.id.btn_Edit_tree);

        btnEdit.setOnClickListener(new EditTreeLstr());

        btnMaps = (Button) findViewById(R.id.btn_map);
        btnMaps.setOnClickListener(new MapBtnLstr());

        //btnPics = (Button) findViewById(R.id.btn_pics);
        //btnPics.setOnClickListener(new ImagesListLstr());
        btnAddEvent = (Button) findViewById(R.id.btn_add_event);
        btnAddEvent.setOnClickListener(new AddTreeEventLstr());
        btnShowHistory = (Button) findViewById(R.id.btn_HistoryData);
        btnShowHistory.setOnClickListener(new ShowHistoryLstr());
        //btnAddPics = (Button) findViewById(R.id.btn_add_pics);
        //btnAddPics.setOnClickListener(new AddPicsLstr());

        btnDeleteTree = (Button) findViewById(R.id.btn_delete_tree);
        btnDeleteTree.setOnClickListener(new DeleteTreeLstr());


        if (treeData != null) {
            tv_species.setText(treeData.getSpecies());
            tv_tree_street_address.setText(treeData.getStreetAddress());
            tv_tree_name.setText(treeData.getTreeName());
            tv_date_planted.setText(treeData.getDatePlanted());
            tv_property_type.setText(treeData.getPropertyType());
            tv_date_created.setText(treeData.getDateCreated());

            // Log.d("Tree Photo URL ",treeData.getPhotoMainURL());
            if (treeData.getPhotoMainURL() != null) {
                Picasso.with(img_main_pic.getContext())
                        .load(treeData.getPhotoMainURL())
                        .resize(150, 150)
                        .centerCrop()
                        .into(img_main_pic);
            }//tv_vol_creator.setText(treeData.);
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
            Toast.makeText(getApplicationContext(), "Tree ID: " + treeData.getTreeId(), Toast.LENGTH_SHORT).show();
        }
    }

    class EditTreeLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_Edit_tree) {
                //TreeData treeData = TreeSession.getInstance().getTreeData();
                Intent intent = new Intent(TreeHistory.this, EditTree.class);
                startActivity(intent);
            }

        }
    }

    class ImagesListLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_pics) {
                Intent intent = new Intent(TreeHistory.this, TreeImagesList.class);
                startActivity(intent);
            }
        }
    }

/*    class AddPicsLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_add_pics) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image*//*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PHOTO_CODE);
            }
        }
    }*/

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


            Log.d("Images count", "" + mArrayUri.size() + "Bit map Images :" + mBitmapsSelected.size());

        }

        //TreeImageData treeImageData=TreeSession.getInstance().getTreeImageData();

        TreeSession treeSession = TreeSession.getInstance();
        TreeImageData treeImageData = new TreeImageData();

        treeImageData.setTreeImages(mBitmapsSelected);
        treeImageData.setTreeImmageUri(mArrayUri);

        treeSession.setTreeImageData(treeImageData);
        Intent intent = new Intent(TreeHistory.this, AddPics.class);
        //intent.putStringArrayListExtra("URIList",mArrayUri);
        //intent.putStringArrayListExtra("selectedImages",mBitmapsSelected);
        startActivity(intent);
    }

    class MapBtnLstr implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btn_map) {
                startActivity(new Intent(TreeHistory.this, LoadingScreenActivity.class)
                        .putExtra("address", tv_tree_street_address.getText().toString())
                        .putExtra("longitude", treeData.getLongitude())
                        .putExtra("latitude", treeData.getLatitude()));

            }
        }
    }


    class showTreePics implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(TreeHistory.this,ShowTreeImages.class);
            startActivity(intent);
        }
    }
    class DeleteTreeLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_delete_tree) {
                CustomDialogClass cdd = new CustomDialogClass(TreeHistory.this);
                cdd.show();
            }
        }
    }

    public class CustomDialogClass extends Dialog implements
            android.view.View.OnClickListener {

        public Activity currentActivity;
        public Dialog d;
        public Button confirm, back;

        public CustomDialogClass(Activity a) {
            super(a);
            // Auto-generated constructor stub
            this.currentActivity = a;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.delete_confirm);
            confirm = (Button) findViewById(R.id.btn_deleteYes);
            back = (Button) findViewById(R.id.btn_deleteNo);
            confirm.setOnClickListener(this);
            back.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()) {
                case R.id.btn_deleteNo:
                    dismiss();
                    break;

                case R.id.btn_deleteYes:
                    TreeData treeData = TreeSession.getInstance().getTreeData();
                    DatabaseReference myref = FireBase.getInstance().getFireBaseReference(Constants.FIRBASE_TREE_DATA);
                    DatabaseReference treeRef = myref.child(treeData.getTreeId());
                    treeRef.removeValue();
                    Toast.makeText(TreeHistory.this, treeData.getTreeName() + " was removed", Toast.LENGTH_SHORT).show();
                    Intent listIntent = new Intent(TreeHistory.this, TreeList.class);
                    startActivity(listIntent);
                default:
                    break;
            }
            dismiss();
        }
    }

    class AddTreeEventLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_add_event) {
                Intent intent = new Intent(TreeHistory.this, AddEvent.class);
                startActivity(intent);
            }
        }
    }

    class ShowHistoryLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_HistoryData){
                Intent intent = new Intent (TreeHistory.this, ShowHistory.class);
                startActivity(intent);
            }
        }
    }
}
