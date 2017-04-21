package com.example.puma.treelog.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.puma.treelog.R;
import com.example.puma.treelog.models.TreeImages;

import java.util.ArrayList;

/**
 * Created by Agni on 4/20/2017.
 */

public class CustomizedNewPicsAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<TreeImages> data;
    private static LayoutInflater inflater=null;
    //public ImageLoader imageLoader;

    public CustomizedNewPicsAdapter(Activity a, ArrayList<TreeImages> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view =convertView;
        if(convertView==null)
            view = inflater.inflate(R.layout.add_pic_row, null);
        EditText editDescription = (EditText)view.findViewById(R.id.edit_imageDescription);
        //ImageView thumb_image=(ImageView)view.findViewById(R.id.list_image); // thumb image

        TreeImages treeImages = data.get(position);

        // Setting all values in listview
        //tree_name.setText("tree Name");
        editDescription.setText(treeImages.getTreeDescription());

        //imageLoader.DisplayImage(song.get(CustomizedListView.KEY_THUMB_URL), thumb_image);
        return view;
    }
}
