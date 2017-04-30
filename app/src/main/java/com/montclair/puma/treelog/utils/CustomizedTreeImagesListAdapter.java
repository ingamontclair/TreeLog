package com.montclair.puma.treelog.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.montclair.puma.treelog.R;
import com.montclair.puma.treelog.models.TreeImages;
import com.montclair.puma.treelog.models.TreeSession;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Supriya on 4/29/17.
 */

public class CustomizedTreeImagesListAdapter extends ArrayAdapter<TreeImages> {
    private Activity activity;
    private ArrayList<TreeImages> data;
    private static LayoutInflater inflater=null;
    private String treeNm;

    public CustomizedTreeImagesListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<TreeImages> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        View view =convertView;
//        if(convertView==null)
//            view = inflater.inflate(R.layout.show_pic_row, null);

        convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.show_pic_row, parent, false);
       // TextView treeName=(TextView)convertView.findViewById(R.id.tree_name);
        ImageView msgPic=(ImageView)convertView.findViewById(R.id.list_image);

       // treeName.setText(treeNm);

        TreeImages treeImages= (TreeImages) getItem(position);

        //Log.d("Get Image Url",""+treeImages.getTreeImageURL().toString());

        if(treeImages.getTreeImageURL() !=null){
            Picasso.with(msgPic.getContext())
                    .load(treeImages.getTreeImageURL())
                    .resize(50,50)
                    .centerCrop()
                    .into(msgPic);
        }

        return convertView;
    }
}
