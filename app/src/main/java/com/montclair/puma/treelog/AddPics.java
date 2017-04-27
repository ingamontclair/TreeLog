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

import com.google.android.gms.maps.model.ButtCap;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.montclair.puma.treelog.models.TreeHistoryData;
import com.montclair.puma.treelog.models.TreeImageData;
import com.montclair.puma.treelog.models.TreeImages;
import com.montclair.puma.treelog.models.TreeSession;
import com.montclair.puma.treelog.utils.CustomizedNewPicsAdapter;

import java.util.ArrayList;

public class AddPics extends AppCompatActivity {
    ListView treeNewImagesList;
    FirebaseStorage mFirebaseStorage;
    StorageReference mStorageReference;
    CustomizedNewPicsAdapter adapter;
   ArrayList<TreeImages> treeImages  = new ArrayList<TreeImages>();
    ArrayList<Bitmap> treeBitmapImages;
    ArrayList<Uri> treeImageUri;
    String photoURI;
    ArrayList<String> photoUriList=new ArrayList<String>();
    TreeHistoryData treeHistoryData;
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

                storageReference.putFile(treeImageUri.get(j)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                                                                       @Override
                                                                                       public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                                                                           photoURI = taskSnapshot.getDownloadUrl().toString();
                                                                                Log.d("Photouri",photoURI);// URl from firebase Storage for each image

                                                                                       }
                                                                                   }
                );

                photoUriList.add(photoURI);// Trying to add firebase image url to List
            }

            //treeHistoryData.setTreeImageUri(photoUriList);
            //TreeSession.getInstance().setTreeHistoryData(treeHistoryData);

            Log.d("in Add pics","list"+photoUriList.get(1));// its not coming

            Intent intent=new Intent(AddPics.this,AddEvent.class);
            startActivity(intent);
        }


    }

}
