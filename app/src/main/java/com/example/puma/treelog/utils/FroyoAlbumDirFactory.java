package com.example.puma.treelog.utils;

import android.annotation.SuppressLint;
import android.os.Environment;

import java.io.File;

/**
 * Created by rahul on 4/14/2017.
 */

public class FroyoAlbumDirFactory extends AlbumStorageDirFactory {
    @SuppressLint("NewApi")
    @Override
    public File getAlbumStorageDir(String albumName) {
        // TODO Auto-generated method stub
        return new File(
                Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES
                ),
                albumName
        );
    }
}
