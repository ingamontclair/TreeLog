package com.montclair.puma.treelog.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Zee on 4/23/2017.
 */

public class Utilities {
    public static String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm:ss");
        return format.format(Calendar.getInstance().getTime());
    }
}
