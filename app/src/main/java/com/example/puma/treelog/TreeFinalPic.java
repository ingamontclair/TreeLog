package com.example.puma.treelog;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.puma.treelog.models.TreeData;
import com.example.puma.treelog.models.TreeSession;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.example.puma.treelog.utils.FireBase;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class TreeFinalPic extends AppCompatActivity {
    private Button btnCommit;
    private TextView tvDateCreated;
    private int PICK_FINAL_IMAGE_REQUEST = 1;
    ImageView imgFinal,imgSmall;
    Uri uriFinalImage;
    //DatabaseReference myref;
    FirebaseStorage mFirebaseStorage;
    StorageReference mStorageReference;
    String finalPhotoURI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_final_pic);

        imgFinal=(ImageView)findViewById(R.id.final_tree_pic);
        imgSmall=(ImageView) findViewById(R.id.small_tree_pic);

        mFirebaseStorage= FirebaseStorage.getInstance();
        mStorageReference=mFirebaseStorage.getReference().child("images");

        TreeData treeData = TreeSession.getInstance().getTreeData();
        btnCommit = (Button)findViewById(R.id.btn_commit);
        btnCommit.setOnClickListener(new CommitLstr());
        tvDateCreated = (TextView)findViewById(R.id.tv_dateCreated);


        if (treeData != null) {

            tvDateCreated.setText(treeData.getDateCreated());

        }

    }

    public void uploadFinalTreePic(View view) {
        Intent intent = new Intent();
// Show only images, no videos or anything else
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_FINAL_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FINAL_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            uriFinalImage = data.getData();
            StorageReference storageReference= mStorageReference
                    .child(uriFinalImage.getLastPathSegment());

            storageReference.putFile(uriFinalImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                                                   @Override
                                                                   public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                                                       finalPhotoURI=taskSnapshot.getDownloadUrl().toString();




                                                                   }
                                                               }
            );

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriFinalImage);

                imgFinal.setImageBitmap(bitmap);
                imgSmall.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteFinalTreePic(View view) {
        imgFinal.setImageDrawable(getResources().getDrawable(R.drawable.noimageicon23));
        imgSmall.setImageDrawable(getResources().getDrawable(R.drawable.noimageicon23));
    }


    class CommitLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_commit){
                TreeData treeData = TreeSession.getInstance().getTreeData();
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
                String dateCreated = df.format(c.getTime());
                treeData.setDateCreated(dateCreated);
                treeData.setPhotoMainURL(finalPhotoURI.toString());
//all TreeData is ready for commit - place for commit !!!
                DatabaseReference myref= FireBase.getInstance().getFireBaseReference("Tree");
                myref.push().setValue(treeData);

            }
        }
    }
}
