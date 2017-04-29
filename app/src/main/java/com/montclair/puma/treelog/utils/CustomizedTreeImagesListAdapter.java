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
import com.montclair.puma.treelog.models.TreeImages;
import com.montclair.puma.treelog.models.TreeSession;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Supriya on 4/29/17.
 */

public class CustomizedTreeImagesListAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<TreeImages> data;
    private static LayoutInflater inflater=null;
    private String treeNm;

    public CustomizedTreeImagesListAdapter(Activity activity, ArrayList<TreeImages> data) {
        this.activity = activity;
        this.data = data;
        treeNm= TreeSession.getInstance().getTreeData().getTreeName().toString();
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view =convertView;
        if(convertView==null)
            view = inflater.inflate(R.layout.show_pic_row, null);
        TextView treeName=(TextView)convertView.findViewById(R.id.tree_name);
        ImageView msgPic=(ImageView)convertView.findViewById(R.id.list_image);

        treeName.setText(treeNm);

        TreeImages treeImages= (TreeImages) getItem(position);

        if(treeImages.getTreeImageURL() !=null){
            Picasso.with(msgPic.getContext())
                    .load(treeImages.getTreeImageURL())
                    .resize(50,50)
                    .centerCrop()
                    .into(msgPic);
        }

        return view;
    }
}
