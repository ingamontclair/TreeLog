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

    public CustomizedTreeImagesListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<TreeImages> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.show_pic_row, parent, false);

        ImageView msgPic=(ImageView)convertView.findViewById(R.id.list_image);


        TreeImages treeImages=getItem(position);



        if(treeImages.getTreeImageURL() !=null){
            Picasso.with(msgPic.getContext())
                    .load(treeImages.getTreeImageURL())
                    .resize(200,200)
                    .centerCrop()
                    .into(msgPic);
        }

        return convertView;
    }
}
