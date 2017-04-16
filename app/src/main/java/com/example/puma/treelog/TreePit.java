package com.example.puma.treelog;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

public class TreePit extends AppCompatActivity {
    private Button btnNext;
    private EditText editComments;
    ImageView imageView;
    private int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_pit);
        TreeData treeData = TreeSession.getInstance().getTreeData();
        imageView = (ImageView) findViewById(R.id.img_pic);
        btnNext = (Button)findViewById(R.id.btn_pit_next);
        btnNext.setOnClickListener(new HealthNextLstr());
        editComments = (EditText)findViewById(R.id.edit_comments);
        if (treeData != null) {
            editComments.setText(treeData.getTreePitComments());

        }
    }

    //upload selected pic from gallery
    public void uploadTreePic(View view) {
        Intent intent = new Intent();
// Show only images, no videos or anything else
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //To delete selected pic
    public void deleteTreePic(View view) {
        //Icon icon="@drawable/noimageicon23";
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.noimageicon23));
    }

    class HealthNextLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_pit_next) {
                TreeData treeData = TreeSession.getInstance().getTreeData();
                treeData.setTreePitComments(editComments.getText().toString());
//TODO: validate all fields and show alerts
                Intent intent = new Intent(TreePit.this, TreeCondition.class);
                startActivity(intent);
            }
        }
    }
}
