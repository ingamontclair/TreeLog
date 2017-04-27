package com.montclair.puma.treelog;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.montclair.puma.treelog.models.TreeImageData;
import com.montclair.puma.treelog.models.TreeImages;
import com.montclair.puma.treelog.models.TreeSession;
import com.montclair.puma.treelog.utils.CustomizedNewPicsAdapter;

import java.util.ArrayList;

public class AddPics extends AppCompatActivity {
    ListView treeNewImagesList;
    CustomizedNewPicsAdapter adapter;
   ArrayList<TreeImages> treeImages  = new ArrayList<TreeImages>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pics);
        treeNewImagesList = (ListView)findViewById(R.id.list_new_pics);
//Get TreeImage Data from Session and add it to List View
        TreeImageData treeImageData=TreeSession.getInstance().getTreeImageData();
        ArrayList<Bitmap> treeBitmapImages=treeImageData.getTreeImages();
        ArrayList<Uri> treeImageUri=treeImageData.getTreeImmageUri();

        for(int i=0;i<treeBitmapImages.size();i++){
            TreeImages treeImageObj=new TreeImages("");
            treeImageObj.setTreeBitmapImage(treeBitmapImages.get(i));
            treeImageObj.setTreeImageURL(treeImageUri.get(i).toString());

            treeImages.add(treeImageObj);
        }


        adapter = new CustomizedNewPicsAdapter(this, treeImages);
        treeNewImagesList.setAdapter(adapter);
/*        treeNewImagesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(AddPics.this, "Image item clicked " + position, Toast.LENGTH_SHORT).show();
                //TreeSession.getInstance().setTreeData(treeImages.get(position));
                //Intent intent = new Intent(TreeImagesList.this,BigPicture.class);
                //startActivity(intent);
            }
        });*/
    }

}
