package com.example.imageloader.support;

import android.net.Uri;
import android.util.Log;

import com.example.imageloader.R;

/**
 * Created by lzy on 2016/11/2.
 */

public class TestConfig {

    private static String url = "http://i.imgur.com/CqmBjo5.jpg";

    public static int getTestResource() {
        return R.drawable.xml;
    }

    public static Uri getTestUrl() {
        return Uri.parse("res:///" + R.drawable.xml);
    }

    public static String getTestNetResource() {
        int index = (int) (Math.random() * Data.LARGE_URLS.length);
        Log.d("test", "getTestNetResource: " + index);
        return Data.LARGE_URLS[index];
    }

    public static String getFirstNetResource() {
        return Data.LARGE_URLS[0];
    }

    public static String[] getNetResource() {
        return Data.LARGE_URLS;
    }
}
