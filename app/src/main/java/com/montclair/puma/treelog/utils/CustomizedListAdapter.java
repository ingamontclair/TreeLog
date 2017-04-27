package com.montclair.puma.treelog.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.montclair.puma.treelog.R;
import com.montclair.puma.treelog.models.TreeData;

import java.util.ArrayList;

/**
 * Created by Agni on 4/16/2017.
 */

public class CustomizedListAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<TreeData> data;
    private static LayoutInflater inflater=null;
    //public ImageLoader imageLoader;

    public CustomizedListAdapter(Activity a, ArrayList<TreeData> d) {
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
            view = inflater.inflate(R.layout.list_row, null);

        TextView tree_name = (TextView)view.findViewById(R.id.tv_list_tree_name); // title
        TextView street_address = (TextView)view.findViewById(R.id.tv_list_streetaddress); // artist name
        ImageView thumb_image=(ImageView)view.findViewById(R.id.list_image); // thumb image

        TreeData treeData = data.get(position);

        // Setting all values in listview
        tree_name.setText(treeData.getTreeName());
        street_address.setText(treeData.getStreetAddress());
        //imageLoader.DisplayImage(song.get(CustomizedListView.KEY_THUMB_URL), thumb_image);
        return view;
    }
}
