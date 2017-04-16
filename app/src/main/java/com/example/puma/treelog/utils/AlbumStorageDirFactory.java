package com.example.puma.treelog.utils;

import java.io.File;

/**
 * Created by rahul on 4/14/2017.
 */

public abstract class AlbumStorageDirFactory {
    public abstract File getAlbumStorageDir(String albumName);
}

