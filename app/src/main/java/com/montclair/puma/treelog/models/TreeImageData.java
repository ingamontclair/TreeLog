package com.montclair.puma.treelog.models;

import android.graphics.Bitmap;
import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by Supriya on 4/21/17.
 */

public class TreeImageData {
    ArrayList<Bitmap> treeImages;
    ArrayList<Uri> treeImmageUri;

    public ArrayList<Bitmap> getTreeImages() {
        return treeImages;
    }

    public void setTreeImages(ArrayList<Bitmap> treeImages) {
        this.treeImages = treeImages;
    }

    public ArrayList<Uri> getTreeImmageUri() {
        return treeImmageUri;
    }

    public void setTreeImmageUri(ArrayList<Uri> treeImmageUri) {
        this.treeImmageUri = treeImmageUri;
    }
}
