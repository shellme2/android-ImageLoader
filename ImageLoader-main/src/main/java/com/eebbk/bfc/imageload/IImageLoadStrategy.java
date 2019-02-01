package com.eebbk.bfc.imageload;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.view.View;

import com.eebbk.bfc.imageload.config.ImageLoadConfig;
import com.eebbk.bfc.imageload.toolbox.ImageLoadCallBack;

/**
 * image load Strategy Pattern interface.
 */
public interface IImageLoadStrategy {

    /**
     * load net image resource
     *
     * @param context {@link Context}
     * @param url     image link
     * @param config  image load configure {@link ImageLoadConfig}
     */
    IImageLoadAgent load(Context context, String url, ImageLoadConfig config, ImageLoadCallBack callBack);

    /**
     * load local image resource
     *
     * @param context    {@link Context}
     * @param resourceId resource id
     * @param config     image load configure {@link ImageLoadConfig}
     */
    IImageLoadAgent load(Context context, int resourceId, ImageLoadConfig config, ImageLoadCallBack callBack);

    /**
     * load image with {@link Uri}
     *
     * @param context {@link Context}
     * @param uri     image uri
     * @param config  image load configure {@link ImageLoadConfig}
     */
    IImageLoadAgent load(Context context, Uri uri, ImageLoadConfig config, ImageLoadCallBack callBack);

    /**
     * load net image resource
     *
     * @param fragment {@link Fragment}
     * @param url      image link
     * @param config   image load configure {@link ImageLoadConfig}
     */
    IImageLoadAgent load(Fragment fragment, String url, ImageLoadConfig config, ImageLoadCallBack callBack);

    /**
     * load local image resource
     *
     * @param fragment   {@link Fragment}
     * @param resourceId resource id
     * @param config     image load configure {@link ImageLoadConfig}
     */
    IImageLoadAgent load(Fragment fragment, int resourceId, ImageLoadConfig config, ImageLoadCallBack callBack);

    /**
     * load image with {@link Uri}
     *
     * @param fragment {@link Fragment}
     * @param uri      image uri
     * @param config   image load configure {@link ImageLoadConfig}
     */
    IImageLoadAgent load(Fragment fragment, Uri uri, ImageLoadConfig config, ImageLoadCallBack callBack);

    /**
     * clear memory cache
     */
    void clearMemoryCache(Context context);

    /**
     * clear disk cache
     * <p>
     * This method should be called on a background thread, since it is a blocking call.
     */
    void clearDiskCache(Context context);

    /**
     * Cancels any in progress loads, but does not clear resources of completed loads.
     */
    void pauseLoad(Context context);

    /**
     * Restarts any loads that have not yet completed.
     */
    void resumeLoad(Context context);

    /**
     * Cancels any in progress loads, but does not clear resources of completed loads.
     */
    void pauseLoad(Fragment fragment);

    /**
     * Restarts any loads that have not yet completed.
     */
    void resumeLoad(Fragment fragment);

    /**
     * clear load request
     */
    void clear(View view);
}
