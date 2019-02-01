package com.eebbk.bfc.imageload;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import android.support.v4.app.Fragment;
import android.view.View;

import com.eebbk.bfc.imageload.config.ImageLoadConfig;
import com.eebbk.bfc.imageload.inner.GlideLoadStrategy;
import com.eebbk.bfc.imageload.toolbox.ImageLoadCallBack;
import com.eebbk.bfc.imageload.tools.L;

/**
 * image load lib operate API class.
 */

public class ImageLoader {

    private static ImageLoadConfig defaultConfig = new ImageLoadConfig.Builder().build();

    public static boolean DEBUG = false;

    private static ImageLoader mInstance;
    private IImageLoadStrategy mStrategy;

    private ImageLoader() {
        mStrategy = new GlideLoadStrategy();
    }

    public static ImageLoader getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader();
                    return mInstance;
                }
            }
        }
        return mInstance;
    }

    /**
     * load net image resource
     *
     * @param context       {@link Context}
     * @param url           image link
     * @param placeholderId place holder image when image before or during load
     * @param errorId       place holder image when image load fail
     */
    public IImageLoadAgent load(@NonNull Context context, @NonNull String url, @DrawableRes int placeholderId, @DrawableRes int errorId, ImageLoadCallBack callBack) {
        return load(context, url, ImageLoadConfig.parseBuilder(defaultConfig).setPlaceHolderResId(placeholderId).setErrorResId(errorId).build(), callBack);
    }

    /**
     * load net image resource
     *
     * @param context {@link Context}
     * @param url     image link
     * @param config  image load configure {@link ImageLoadConfig}
     */
    public IImageLoadAgent load(@NonNull Context context, @NonNull String url, ImageLoadConfig config, ImageLoadCallBack callBack) {
        L.v("load image from url: " + url);
        if (config == null) {
            config = defaultConfig;
        }
        return mStrategy.load(context, url, config, callBack);
    }

    /**
     * load local image resource
     *
     * @param context    {@link Context}
     * @param resourceId resource id
     * @param config     image load configure {@link ImageLoadConfig}
     */
    public IImageLoadAgent load(@NonNull Context context, @DrawableRes int resourceId, ImageLoadConfig config, ImageLoadCallBack callBack) {
        L.v("load image from resource: " + resourceId);
        if (config == null) {
            config = defaultConfig;
        }
        return mStrategy.load(context, resourceId, config, callBack);
    }

    /**
     * load image with {@link Uri}
     *
     * @param context {@link Context}
     * @param uri     image uri
     * @param config  image load configure {@link ImageLoadConfig}
     */
    public IImageLoadAgent load(@NonNull Context context, @NonNull Uri uri, ImageLoadConfig config, ImageLoadCallBack callBack) {
        L.v("load image from Uri: " + uri.toString());
        if (config == null) {
            config = defaultConfig;
        }
        return mStrategy.load(context, uri, config, callBack);
    }

    /**
     * load net image resource
     *
     * @param fragment      {@link Fragment}
     * @param url           image link
     * @param placeholderId place holder image when image before or during load
     * @param errorId       place holder image when image load fail
     */
    public IImageLoadAgent load(@NonNull Fragment fragment, @NonNull String url, @DrawableRes int placeholderId, @DrawableRes int errorId, ImageLoadCallBack callBack) {
        return load(fragment, url, ImageLoadConfig.parseBuilder(defaultConfig).setPlaceHolderResId(placeholderId).setErrorResId(errorId).build(), callBack);
    }

    /**
     * load net image resource
     *
     * @param fragment {@link Fragment}
     * @param url      image link
     * @param config   image load configure {@link ImageLoadConfig}
     */
    public IImageLoadAgent load(@NonNull Fragment fragment, @NonNull String url, ImageLoadConfig config, ImageLoadCallBack callBack) {
        L.v("load image from url: " + url);
        if (config == null) {
            config = defaultConfig;
        }
        return mStrategy.load(fragment, url, config, callBack);
    }

    /**
     * load local image resource
     *
     * @param fragment   {@link Fragment}
     * @param resourceId resource id
     * @param config     image load configure {@link ImageLoadConfig}
     */
    public IImageLoadAgent load(@NonNull Fragment fragment, @DrawableRes int resourceId, ImageLoadConfig config, ImageLoadCallBack callBack) {
        L.v("load image from resource: " + resourceId);
        if (config == null) {
            config = defaultConfig;
        }
        return mStrategy.load(fragment, resourceId, config, callBack);
    }

    /**
     * load image with {@link Uri}
     *
     * @param fragment {@link Fragment}
     * @param uri      image uri
     * @param config   image load configure {@link ImageLoadConfig}
     */
    public IImageLoadAgent load(@NonNull Fragment fragment, @NonNull Uri uri, ImageLoadConfig config, ImageLoadCallBack callBack) {
        L.v("load image from Uri: " + uri.toString());
        if (config == null) {
            config = defaultConfig;
        }
        return mStrategy.load(fragment, uri, config, callBack);
    }

    /**
     * clear memory cache
     */
    public void clearMemoryCache(@NonNull Context context) {
        L.v("clearMemoryCache");
        mStrategy.clearMemoryCache(context);
    }

    /**
     * clear disk cache.
     * <p>
     * This method should be called on a background thread, since it is a blocking call.
     */
    @WorkerThread
    public void clearDiskCache(@NonNull Context context) {
        L.v("clearDiskCache");
        mStrategy.clearDiskCache(context);
    }

    /**
     * Cancels any in progress loads, but does not clear resources of completed loads.
     */
    public void pauseLoad(@NonNull Context context) {
        L.v("pauseLoad");
        mStrategy.pauseLoad(context);
    }

    /**
     * Restarts any loads that have not yet completed.
     */
    public void resumeLoad(@NonNull Context context) {
        L.v("resumeLoad");
        mStrategy.resumeLoad(context);
    }

    /**
     * Cancels any in progress loads, but does not clear resources of completed loads.
     */
    public void pauseLoad(@NonNull Fragment fragment) {
        L.v("pauseLoad");
        mStrategy.pauseLoad(fragment);
    }

    /**
     * Restarts any loads that have not yet completed.
     */
    public void resumeLoad(@NonNull Fragment fragment) {
        L.v("resumeLoad");
        mStrategy.resumeLoad(fragment);
    }

    /**
     * enable user custom image load realize, which implement {@link IImageLoadStrategy} interface.
     *
     * @param strategy user custom image load realize
     */
    public void setImageLoadStrategy(@NonNull IImageLoadStrategy strategy) {
        mStrategy = strategy;
    }

    /**
     * clear load request
     */
    public void clear(@NonNull View view) {
        L.v("clear request");
        mStrategy.clear(view);
    }

    /**
     * custom set default image load configure
     */
    public static void initDefaultConf(ImageLoadConfig config) {
        defaultConfig = ImageLoadConfig.parseBuilder(config).build();
    }

    @VisibleForTesting
    ImageLoadConfig getDefaultConf() {
        return defaultConfig;
    }
}
