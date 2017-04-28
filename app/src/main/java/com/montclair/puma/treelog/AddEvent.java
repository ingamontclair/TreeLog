package com.montclair.puma.treelog;

import android.content.ClipData;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.montclair.puma.treelog.models.TreeData;
import com.montclair.puma.treelog.models.TreeHistoryData;
import com.montclair.puma.treelog.models.TreeImageData;
import com.montclair.puma.treelog.models.TreeSession;
import com.montclair.puma.treelog.models.User;
import com.montclair.puma.treelog.utils.ChildEventListenerAdapter;
import com.montclair.puma.treelog.utils.Constants;
import com.montclair.puma.treelog.utils.FireBase;
import com.google.firebase.database.DatabaseReference;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class AddEvent extends AppCompatActivity {
    private HashMap<String, String> hazard = new HashMap<>();
    private TextView tvTreeName;
    private TreeData treeData;
    private Button btnAddPics;
    private Button btnCommitEvent;
    private EditText editEventDiametr;
    private EditText editEventSize;
    private EditText editEventBiotic;
    private EditText editEventAbiotic;
    private static int PICK_PHOTO_CODE = 1004;
    ArrayList<Bitmap> mBitmapsSelected;
    ArrayList<Uri> mArrayUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        treeData = TreeSession.getInstance().getTreeData();
        tvTreeName = (TextView) findViewById(R.id.tv_event_treeName);
        tvTreeName.setText(treeData.getTreeName());

        //btnAddPics = (Button) findViewById(R.id.btn_add_pics);
        //btnAddPics.setOnClickListener(new AddPicLstr());

        btnCommitEvent = (Button) findViewById(R.id.btn_event_commit);
        btnCommitEvent.setOnClickListener(new CommitEventLstr());

        editEventDiametr = (EditText) findViewById(R.id.edit_event_diametr);
        editEventSize = (EditText) findViewById(R.id.edit_event_size);

        editEventBiotic = (EditText) findViewById(R.id.edit_event_biotic);
        editEventAbiotic = (EditText) findViewById(R.id.edit_event_a_biotic);

        editEventBiotic.setInputType(InputType.TYPE_NULL);
        editEventAbiotic.setInputType(InputType.TYPE_NULL);

        editEventBiotic.setOnClickListener(new BioticTypeLstr());
        editEventAbiotic.setOnClickListener(new ABioticTypeLstr());

        // TreeData treeData = TreeSession.getInstance().getTreeData();

        TreeHistoryData treeHistoryData = TreeSession.getInstance().getTreeHistoryData();
        if (treeHistoryData == null) {
            treeHistoryData = new TreeHistoryData();
            TreeSession.getInstance().setTreeHistoryData(treeHistoryData);
        }
        if (treeHistoryData != null) {
            editEventDiametr.setText(treeHistoryData.getTreeHistoryDiametr());
            editEventSize.setText(treeHistoryData.getTreeHistorySize());
            editEventBiotic.setText(treeHistoryData.getTreeHistoryBiotic());
            editEventAbiotic.setText(treeHistoryData.getTreeHistory_a_Biotic());
        }

    }

    public void onHazardClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        TreeData treeData = TreeSession.getInstance().getTreeData();
        switch (view.getId()) {
            case R.id.chb_balance:
                if (checked) {
                    hazard.put("Dangerous balance (more than 10 degree)", "yes");
                } else {
                    hazard.remove("Dangerous balance (more than 10 degree)");
                }
                break;
            case R.id.chb_brokenBranches:
                if (checked) {
                    hazard.put("Broken or forked branches", "yes");
                } else {
                    hazard.remove("Broken or forked branches");
                }
                break;
            case R.id.chb_decay:
                if (checked) {
                    hazard.put("Signs of Decay", "yes");
                } else {
                    hazard.remove("Signs of Decay");
                }
                break;
            case R.id.chb_forkedTrunks:
                if (checked) {
                    hazard.put("Forked Trunks", "yes");
                } else {
                    hazard.remove("Forked Trunks");
                }
                break;
            case R.id.chb_remove:
                if (checked) {
                    treeData.setRemove("Yes");
                } else {
                    treeData.setRemove("No");
                }
                break;
        }
    }

    class BioticTypeLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            TreeData treeData = TreeSession.getInstance().getTreeData();
            TreeHistoryData treeHistoryData = TreeSession.getInstance().getTreeHistoryData();
            treeHistoryData.setTreeID(treeData.getTreeId());
            treeHistoryData.setTreeHistoryDiametr(editEventDiametr.getText().toString());
            treeHistoryData.setTreeHistorySize(editEventSize.getText().toString());
            treeHistoryData.setTreeHistoryBiotic(editEventBiotic.getText().toString());
            treeHistoryData.setTreeHistory_a_Biotic(editEventAbiotic.getText().toString());
            Intent exIntent = new Intent(AddEvent.this, BioticDamage.class);
            exIntent.putExtra("className", AddEvent.class);
            startActivity(exIntent);
        }
    }

    class ABioticTypeLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            TreeData treeData = TreeSession.getInstance().getTreeData();
            TreeHistoryData treeHistoryData = TreeSession.getInstance().getTreeHistoryData();
            treeHistoryData.setTreeID(treeData.getTreeId());
            treeHistoryData.setTreeHistoryDiametr(editEventDiametr.getText().toString());
            treeHistoryData.setTreeHistorySize(editEventSize.getText().toString());
            treeHistoryData.setTreeHistoryBiotic(editEventBiotic.getText().toString());
            treeHistoryData.setTreeHistory_a_Biotic(editEventAbiotic.getText().toString());
            Intent exIntent = new Intent(AddEvent.this, AbioticDamage.class);
            exIntent.putExtra("className", AddEvent.class);
            startActivity(exIntent);

        }
    }

    class AddPicLstr implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //if (v.getId() == R.id.btn_add_pics) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PHOTO_CODE);
           // }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_PHOTO_CODE) {

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
            }

        }

        TreeSession treeSession = TreeSession.getInstance();
        TreeImageData treeImageData = new TreeImageData();

        //We have stored multiple selected images from gallery to List and corrosponding local url  of image
        treeImageData.setTreeImages(mBitmapsSelected);
        treeImageData.setTreeImmageUri(mArrayUri);

        treeSession.setTreeImageData(treeImageData);
        Intent intent = new Intent(AddEvent.this, AddPics.class);

        startActivity(intent);

    }

    class CommitEventLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_event_commit) {
                TreeData treeData = TreeSession.getInstance().getTreeData();
                //TreeHistoryData treeHistoryData = new TreeHistoryData();
                TreeHistoryData treeHistoryData = TreeSession.getInstance().getTreeHistoryData();
                User user = TreeSession.getInstance().getUser();
                TreeImageData treeimageData = TreeSession.getInstance().getTreeImageData();


                treeHistoryData.setTreeID(treeData.getTreeId());
                treeHistoryData.setUserID(user.getUserID());
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
                String dateModyfied = df.format(c.getTime());
                treeHistoryData.setEntryDate(dateModyfied);
                treeHistoryData.setTreeTrunkPicURL("");
                treeHistoryData.setTreeHistoryDiametr(editEventDiametr.getText().toString());
                treeHistoryData.setTreeHistoryDiametr(editEventSize.getText().toString());
                treeHistoryData.setTreeHistoryBiotic(editEventBiotic.getText().toString());
                treeHistoryData.setTreeHistory_a_Biotic(editEventAbiotic.getText().toString());
                treeHistoryData.setTreeHistoryPitURL("");
                treeHistoryData.setTreeHistoryPitComments("");
                //treeHistoryData.setTreeImageUri(TreeSession.getInstance().getTreeHistoryData().getTreeImageUri());

                //Log.d("Tree History Uri",""+treeHistoryData.getTreeImageUri().toString());
                //get hazard from checked types
                String tmpHazard = "";
                for (String s : hazard.keySet()) {
                    tmpHazard += s;
                    tmpHazard += ", ";
                }

                if (tmpHazard.length() > 0) {
                    tmpHazard = tmpHazard.replaceFirst(", $", "");
                }
                treeHistoryData.setTreeHistoryHazard(tmpHazard);
                DatabaseReference myref = FireBase.getInstance().getFireBaseReference(Constants.FIRBASE_TREE_HISTORY_DATA);
                //insert new record of thee History Update
                DatabaseReference thdRef = myref.push(); //generate a unique key for a new history record
                treeHistoryData.setTreeHistoryID(thdRef.getKey());
                thdRef.setValue(treeHistoryData); //set value



/*                myref.push().setValue(treeHistoryData).addOnSuccessListener(
                        myref.addChildEventListener(                    new ChildEventListenerAdapter() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                //on event happend we set a new treeHistoryData session and save key to the field treeHistoryID
                                TreeHistoryData treeHistoryData = dataSnapshot.getValue(TreeHistoryData.class);
                                treeHistoryData.setTreeHistoryID(dataSnapshot.getKey());
                                TreeSession.getInstance().setTreeHistoryData(treeHistoryData);

                            }
                        } )
                );*/

                Toast.makeText(AddEvent.this, "New record in history was added", Toast.LENGTH_SHORT).show();
                CustomDialogClass cdd = new CustomDialogClass(AddEvent.this);
                cdd.show();

                //Intent intent = new Intent(AddEvent.this, TreeHistory.class);
                //startActivity(intent);
            }

        }
    }

    public class CustomDialogClass extends Dialog implements
            android.view.View.OnClickListener {

        public Activity currentActivity;
        public Dialog d;
        public Button confirmPics, backTree;

        public CustomDialogClass(Activity a) {
            super(a);
            // Auto-generated constructor stub
            this.currentActivity = a;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.upload_picture_confirm);
            confirmPics = (Button) findViewById(R.id.btn_uploadPics);
            backTree = (Button) findViewById(R.id.btn_BackToTree);
            confirmPics.setOnClickListener(new AddPicLstr());
            backTree.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()) {
                case R.id.btn_BackToTree:
                    //dismiss();
                    intent = new Intent(AddEvent.this, TreeHistory.class);
                    startActivity(intent);
                    break;

                case R.id.btn_uploadPics:
                    confirmPics.setOnClickListener(new AddPicLstr());
/*                    TreeData treeData = TreeSession.getInstance().getTreeData();
                    DatabaseReference myref = FireBase.getInstance().getFireBaseReference(Constants.FIRBASE_TREE_DATA);
                    DatabaseReference treeRef = myref.child(treeData.getTreeId());
                    treeRef.removeValue();*/
/*                    Intent picIntent = new Intent(Intent.ACTION_PICK);
                    picIntent.setType("image*//*");
                    picIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    startActivityForResult(Intent.createChooser(picIntent, "Select Picture"), PICK_PHOTO_CODE);*/
                default:
                    break;
            }
            dismiss();
        }
    }
}
