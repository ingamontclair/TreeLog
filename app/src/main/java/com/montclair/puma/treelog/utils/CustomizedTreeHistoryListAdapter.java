package com.montclair.puma.treelog.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.montclair.puma.treelog.R;
import com.montclair.puma.treelog.models.TreeHistoryData;
import com.montclair.puma.treelog.models.TreeImages;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Agni on 4/29/2017.
 */

public class CustomizedTreeHistoryListAdapter extends ArrayAdapter<TreeHistoryData> {
    private Activity activity;
    private ArrayList<TreeHistoryData> data;
    private static LayoutInflater inflater=null;

    public CustomizedTreeHistoryListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<TreeHistoryData> objects) {
        super(context, resource, objects);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.history_row, parent, false);
 /*Declare section*/
        TextView dateModified=(TextView)convertView.findViewById(R.id.tv_date_modified);
        TextView volModified=(TextView)convertView.findViewById(R.id.tv_modified_vol);
        TextView diametr=(TextView)convertView.findViewById(R.id.tv_mod_diametr);
        TextView size=(TextView)convertView.findViewById(R.id.tv_mod_size);
        TextView biotic=(TextView)convertView.findViewById(R.id.tv_mod_biotic_damage);
        TextView a_biotic=(TextView)convertView.findViewById(R.id.tv_mod_a_biotic_damage);
        TextView hazard=(TextView)convertView.findViewById(R.id.tv_mod_tree_hazard);

        TreeHistoryData treeHistoryData= getItem(position);

/*Sey value section*/
        dateModified.setText(treeHistoryData.getEntryDate());
        volModified.setText("Volunteer");
        diametr.setText(treeHistoryData.getTreeHistoryDiametr());
        size.setText(treeHistoryData.getTreeHistorySize());
        biotic.setText(treeHistoryData.getTreeHistoryBiotic());
        a_biotic.setText(treeHistoryData.getTreeHistory_a_Biotic());
        hazard.setText(treeHistoryData.getTreeHistoryHazard());
        return convertView;
    }
}
