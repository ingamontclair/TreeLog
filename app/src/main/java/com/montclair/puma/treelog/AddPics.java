package com.montclair.puma.treelog;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.ButtCap;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.montclair.puma.treelog.models.TreeData;
import com.montclair.puma.treelog.models.TreeHistoryData;
import com.montclair.puma.treelog.models.TreeImageData;
import com.montclair.puma.treelog.models.TreeImages;
import com.montclair.puma.treelog.models.TreeSession;
import com.montclair.puma.treelog.utils.Constants;
import com.montclair.puma.treelog.utils.CustomizedNewPicsAdapter;
import com.montclair.puma.treelog.utils.FireBase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AddPics extends AppCompatActivity {
    ListView treeNewImagesList;
    FirebaseStorage mFirebaseStorage;
    StorageReference mStorageReference;
    CustomizedNewPicsAdapter adapter;
    ArrayList<TreeImages> treeImages  = new ArrayList<TreeImages>();
    ArrayList<Bitmap> treeBitmapImages;
    ArrayList<Uri> treeImageUri;
    String photoURI;
    //ArrayList<String> photoUriList=new ArrayList<String>();
    //TreeHistoryData treeHistoryData;
    private Button addPics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pics);
        treeNewImagesList = (ListView)findViewById(R.id.list_new_pics);
       // treeHistoryData=new TreeHistoryData();

        addPics=(Button)findViewById(R.id.btn_addPicsToDB) ;
        addPics.setOnClickListener(new SaveImageLstr());


        mFirebaseStorage= FirebaseStorage.getInstance();
        mStorageReference=mFirebaseStorage.getReference().child("images");

//Get TreeImage Data from Session and add it to List View
        TreeImageData treeImageData=TreeSession.getInstance().getTreeImageData();
        treeBitmapImages=treeImageData.getTreeImages();
        treeImageUri=treeImageData.getTreeImmageUri();


        for(int i=0;i<treeBitmapImages.size();i++){
            TreeImages treeImageObj=new TreeImages("");
            treeImageObj.setTreeBitmapImage(treeBitmapImages.get(i));
            treeImageObj.setTreeImageURL(treeImageUri.get(i).toString());

            treeImages.add(treeImageObj);
        }


        adapter = new CustomizedNewPicsAdapter(this, treeImages);
        treeNewImagesList.setAdapter(adapter);


    }


    class SaveImageLstr implements View.OnClickListener{

        @Override
        public void onClick(View v) {


            for (int j = 0; j < treeBitmapImages.size(); j++) {
                StorageReference storageReference = mStorageReference
                        .child(treeImageUri.get(j).getLastPathSegment());

                storageReference.putFile(treeImageUri.get(j)).addOnSuccessListener(
                        new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                photoURI = taskSnapshot.getDownloadUrl().toString();
                                Log.d("Photouri", photoURI);// URl from firebase Storage for each image
                                //push right here to firebase
  /*This Storage call is asynchronous, we need to process the results in on successful listener method */
                                TreeHistoryData treeHistoryData = TreeSession.getInstance().getTreeHistoryData();
                                TreeImages treeImages = new TreeImages();
                                treeImages.setTreeHistoryID(treeHistoryData.getTreeHistoryID());
                                treeImages.setTreeID(treeHistoryData.getTreeID());
                                treeImages.setTreeImageURL(photoURI);
                                Calendar c = Calendar.getInstance();
                                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
                                String datePhotoUploaded = df.format(c.getTime());
                                treeImages.setTreeImageUploadDate(datePhotoUploaded);
                                treeImages.setTreeDescription("");
                                DatabaseReference imageRef = FireBase.getInstance().getFireBaseReference(Constants.FIRBASE_TREE_IMAGES);
                                imageRef.push().setValue(treeImages);
                                //Toast.makeText(AddPics.this, "url "+photoURI, Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }

/*PICTURES ARE NOT YET STORED. WE WILL KNOW WHEN THE PICTURES ARE STORED IN THE OnSuccessListener WE REGISTERED EARLIER*/

            Intent intent=new Intent(AddPics.this,TreeHistory.class);
            startActivity(intent);
        }


    }

}
