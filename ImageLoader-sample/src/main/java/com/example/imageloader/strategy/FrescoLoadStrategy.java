package com.example.imageloader.strategy;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.eebbk.bfc.imageload.IImageLoadAgent;
import com.eebbk.bfc.imageload.IImageLoadStrategy;
import com.eebbk.bfc.imageload.config.ImageLoadConfig;
import com.eebbk.bfc.imageload.toolbox.ImageLoadCallBack;

/**
 * notice: if using this realize in BfcImageLoader, you need to add {@code Fresco.initialize(this);} in application
 */

public class FrescoLoadStrategy implements IImageLoadStrategy {
    @Override
    public IImageLoadAgent load(Context context, String url, ImageLoadConfig config, ImageLoadCallBack callBack) {
        return null;
    }

    @Override
    public IImageLoadAgent load(Context context, int resourceId, ImageLoadConfig config, ImageLoadCallBack callBack) {
        return null;
    }

    @Override
    public IImageLoadAgent load(Context context, Uri uri, ImageLoadConfig config, ImageLoadCallBack callBack) {
        return null;
    }

    @Override
    public IImageLoadAgent load(Fragment fragment, String url, ImageLoadConfig config, ImageLoadCallBack callBack) {
        return null;
    }

    @Override
    public IImageLoadAgent load(Fragment fragment, int resourceId, ImageLoadConfig config, ImageLoadCallBack callBack) {
        return null;
    }

    @Override
    public IImageLoadAgent load(Fragment fragment, Uri uri, ImageLoadConfig config, ImageLoadCallBack callBack) {
        return null;
    }

    @Override
    public void clearMemoryCache(Context context) {

    }

    @Override
    public void clearDiskCache(Context context) {

    }

    @Override
    public void pauseLoad(Context context) {

    }

    @Override
    public void resumeLoad(Context context) {

    }

    @Override
    public void pauseLoad(Fragment fragment) {

    }

    @Override
    public void resumeLoad(Fragment fragment) {

    }

    @Override
    public void clear(View view) {

    }
}
