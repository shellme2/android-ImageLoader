package com.eebbk.bfc.imageload.inner;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.view.View;

import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.GifTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.eebbk.bfc.imageload.IImageLoadAgent;
import com.eebbk.bfc.imageload.IImageLoadStrategy;
import com.eebbk.bfc.imageload.config.ImageLoadConfig;
import com.eebbk.bfc.imageload.toolbox.ImageLoadCallBack;
import com.eebbk.bfc.imageload.tools.L;

/**
 * image load Strategy with Glide.
 * <p>
 * https://github.com/bumptech/glide
 */

public class GlideLoadStrategy implements IImageLoadStrategy {

    private static final String TAG = "GlideLoadStrategy";

    @Override
    public IImageLoadAgent load(Context context, String url, final ImageLoadConfig config, ImageLoadCallBack callBack) {
        if (config.getImageType() == null) {
            return configLoader(Glide.with(context).load(url), config, callBack);
        } else {
            switch (config.getImageType()) {
                case BITMAP:
                    return configLoader(Glide.with(context).load(url).asBitmap(), config, callBack);
                case GIF:
                    return configLoader(Glide.with(context).load(url).asGif(), config, callBack);
                default:
                    return configLoader(Glide.with(context).load(url), config, callBack);
            }
        }
    }

    @Override
    public IImageLoadAgent load(Context context, int resourceId, ImageLoadConfig config, ImageLoadCallBack callBack) {
        if (config.getImageType() == null) {
            return configLoader(Glide.with(context).load(resourceId), config, callBack);
        } else {
            switch (config.getImageType()) {
                case BITMAP:
                    return configLoader(Glide.with(context).load(resourceId).asBitmap(), config, callBack);
                case GIF:
                    return configLoader(Glide.with(context).load(resourceId).asGif(), config, callBack);
                default:
                    return configLoader(Glide.with(context).load(resourceId), config, callBack);
            }
        }
    }

    @Override
    public IImageLoadAgent load(Context context, Uri uri, ImageLoadConfig config, ImageLoadCallBack callBack) {
        if (config.getImageType() == null) {
            return configLoader(Glide.with(context).load(uri), config, callBack);
        } else {
            switch (config.getImageType()) {
                case BITMAP:
                    return configLoader(Glide.with(context).load(uri).asBitmap(), config, callBack);
                case GIF:
                    return configLoader(Glide.with(context).load(uri).asGif(), config, callBack);
                default:
                    return configLoader(Glide.with(context).load(uri), config, callBack);
            }
        }
    }

    @Override
    public IImageLoadAgent load(Fragment fragment, String url, ImageLoadConfig config, ImageLoadCallBack callBack) {
        if (config.getImageType() == null) {
            return configLoader(Glide.with(fragment).load(url), config, callBack);
        } else {
            switch (config.getImageType()) {
                case BITMAP:
                    return configLoader(Glide.with(fragment).load(url).asBitmap(), config, callBack);
                case GIF:
                    return configLoader(Glide.with(fragment).load(url).asGif(), config, callBack);
                default:
                    return configLoader(Glide.with(fragment).load(url), config, callBack);
            }
        }
    }

    @Override
    public IImageLoadAgent load(Fragment fragment, int resourceId, ImageLoadConfig config, ImageLoadCallBack callBack) {
        if (config.getImageType() == null) {
            return configLoader(Glide.with(fragment).load(resourceId), config, callBack);
        } else {
            switch (config.getImageType()) {
                case BITMAP:
                    return configLoader(Glide.with(fragment).load(resourceId).asBitmap(), config, callBack);
                case GIF:
                    return configLoader(Glide.with(fragment).load(resourceId).asGif(), config, callBack);
                default:
                    return configLoader(Glide.with(fragment).load(resourceId), config, callBack);
            }
        }
    }

    @Override
    public IImageLoadAgent load(Fragment fragment, Uri uri, ImageLoadConfig config, ImageLoadCallBack callBack) {
        if (config.getImageType() == null) {
            return configLoader(Glide.with(fragment).load(uri), config, callBack);
        } else {
            switch (config.getImageType()) {
                case BITMAP:
                    return configLoader(Glide.with(fragment).load(uri).asBitmap(), config, callBack);
                case GIF:
                    return configLoader(Glide.with(fragment).load(uri).asBitmap(), config, callBack);
                default:
                    return configLoader(Glide.with(fragment).load(uri), config, callBack);
            }
        }
    }

    private IImageLoadAgent configLoader(DrawableRequestBuilder builder, ImageLoadConfig config, final ImageLoadCallBack callBack) {
        if (config.getPlaceHolderResId() != 0) {
            builder.placeholder(config.getPlaceHolderResId());
        }
        if (config.getErrorResId() != 0) {
            builder.error(config.getErrorResId());
        }

        if (config.getTargetSize() != null) {
            builder.override(config.getTargetSize().getWidth(), config.getTargetSize().getHeight());
        }

        if (config.getPriority() != null) {
            switch (config.getPriority()) {
                case LOW:
                    builder.priority(Priority.LOW);
                    break;
                case NORMAL:
                    builder.priority(Priority.NORMAL);
                    break;
                case HIGH:
                    builder.priority(Priority.HIGH);
                    break;
            }
        }

        //TODO  realize config.getConfig()

        //TODO  realize config.getTransformations()

        if (callBack != null) {
            builder.listener(new RequestListener() {
                @Override
                public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
                    L.e("load image fail: " + e + " " + model);
                    callBack.onError(e, model);
                    return false;
                }

                @Override
                public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
                    L.i("load image " + (isFromMemoryCache ? "from memory cache " : "") + model);
                    callBack.onSuccess(model, isFromMemoryCache, isFirstResource);
                    return false;
                }
            });
        }

        if (config.isFade()) {
            builder.crossFade();
        } else {
            builder.crossFade(0);
        }

        //TODO  realize config.debug()

//        builder.bitmapTransform();
//        builder.transform();

        if (config.getTransformations() != null && !config.getTransformations().isEmpty()) {
            builder.bitmapTransform(config.getTransformations().toArray(new Transformation[]{}));
        }

        builder.skipMemoryCache(true);
        builder.diskCacheStrategy(DiskCacheStrategy.NONE);

        if (config.getCacheStrategy() != null) {
            switch (config.getCacheStrategy()) {
                case ALL:
                    builder.skipMemoryCache(false);
                    builder.diskCacheStrategy(DiskCacheStrategy.RESULT);
                    break;
                case MEMORY:
                    builder.skipMemoryCache(false);
                    builder.diskCacheStrategy(DiskCacheStrategy.RESULT);
                    break;
                case DISK:
                    builder.skipMemoryCache(true);
                    builder.diskCacheStrategy(DiskCacheStrategy.RESULT);
                    break;
                case NONE:
                    builder.skipMemoryCache(true);
                    builder.diskCacheStrategy(DiskCacheStrategy.NONE);
                    break;
            }
        }

        if(!config.dontAnimate()){
            builder.dontAnimate();
        }

        return new GlideLoadAgent(builder, config);
    }

    private IImageLoadAgent configLoader(BitmapTypeRequest builder, ImageLoadConfig config, final ImageLoadCallBack callBack) {
        if (config.getPlaceHolderResId() != 0) {
            builder.placeholder(config.getPlaceHolderResId());
        }
        if (config.getErrorResId() != 0) {
            builder.error(config.getErrorResId());
        }

        if (config.getTargetSize() != null) {
            builder.override(config.getTargetSize().getWidth(), config.getTargetSize().getHeight());
        }

        if (config.getPriority() != null) {
            switch (config.getPriority()) {
                case LOW:
                    builder.priority(Priority.LOW);
                    break;
                case NORMAL:
                    builder.priority(Priority.NORMAL);
                    break;
                case HIGH:
                    builder.priority(Priority.HIGH);
                    break;
            }
        }

        //TODO  realize config.getConfig()

        //TODO  realize config.getTransformations()

        if (callBack != null) {
            builder.listener(new RequestListener() {
                @Override
                public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
                    L.e("load image fail: " + e + " " + model);
                    callBack.onError(e, model);
                    return false;
                }

                @Override
                public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
                    L.i("load image " + (isFromMemoryCache ? "from memory cache " : "") + model);
                    callBack.onSuccess(model, isFromMemoryCache, isFirstResource);
                    return false;
                }
            });
        }

        if (config.isFade()) {
        }

        //TODO  realize config.debug()

//        builder.bitmapTransform();
//        builder.transform();

        if (config.getTransformations() != null && !config.getTransformations().isEmpty()) {
        }

        builder.skipMemoryCache(true);
        builder.diskCacheStrategy(DiskCacheStrategy.NONE);

        if (config.getCacheStrategy() != null) {
            switch (config.getCacheStrategy()) {
                case ALL:
                    builder.skipMemoryCache(false);
                    builder.diskCacheStrategy(DiskCacheStrategy.RESULT);
                    break;
                case MEMORY:
                    builder.skipMemoryCache(false);
                    builder.diskCacheStrategy(DiskCacheStrategy.RESULT);
                    break;
                case DISK:
                    builder.skipMemoryCache(true);
                    builder.diskCacheStrategy(DiskCacheStrategy.RESULT);
                    break;
                case NONE:
                    builder.skipMemoryCache(true);
                    builder.diskCacheStrategy(DiskCacheStrategy.NONE);
                    break;
            }
        }
        if(!config.dontAnimate()){
            builder.dontAnimate();
        }

        return new GlideLoadAgent(builder, config);
    }

    private IImageLoadAgent configLoader(GifTypeRequest builder, ImageLoadConfig config, final ImageLoadCallBack callBack) {
        if (config.getPlaceHolderResId() != 0) {
            builder.placeholder(config.getPlaceHolderResId());
        }
        if (config.getErrorResId() != 0) {
            builder.error(config.getErrorResId());
        }

        if (config.getTargetSize() != null) {
            builder.override(config.getTargetSize().getWidth(), config.getTargetSize().getHeight());
        }

        if (config.getPriority() != null) {
            switch (config.getPriority()) {
                case LOW:
                    builder.priority(Priority.LOW);
                    break;
                case NORMAL:
                    builder.priority(Priority.NORMAL);
                    break;
                case HIGH:
                    builder.priority(Priority.HIGH);
                    break;
            }
        }

        //TODO  realize config.getConfig()

        //TODO  realize config.getTransformations()

        if (callBack != null) {
            builder.listener(new RequestListener() {
                @Override
                public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
                    L.e("load image fail: " + e + " " + model);
                    callBack.onError(e, model);
                    return false;
                }

                @Override
                public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
                    L.i("load image " + (isFromMemoryCache ? "from memory cache " : "") + model);
                    callBack.onSuccess(model, isFromMemoryCache, isFirstResource);
                    return false;
                }
            });
        }

        if (config.isFade()) {
            builder.crossFade();
        } else {
            builder.crossFade(0);
        }

        //TODO  realize config.debug()

//        builder.bitmapTransform();
//        builder.transform();

        if (config.getTransformations() != null && !config.getTransformations().isEmpty()) {
        }

        builder.skipMemoryCache(true);
        builder.diskCacheStrategy(DiskCacheStrategy.NONE);

        if (config.getCacheStrategy() != null) {
            switch (config.getCacheStrategy()) {
                case ALL:
                    builder.skipMemoryCache(false);
                    builder.diskCacheStrategy(DiskCacheStrategy.RESULT);
                    break;
                case MEMORY:
                    builder.skipMemoryCache(false);
                    builder.diskCacheStrategy(DiskCacheStrategy.RESULT);
                    break;
                case DISK:
                    builder.skipMemoryCache(true);
                    builder.diskCacheStrategy(DiskCacheStrategy.RESULT);
                    break;
                case NONE:
                    builder.skipMemoryCache(true);
                    builder.diskCacheStrategy(DiskCacheStrategy.NONE);
                    break;
            }
        }
        if(!config.dontAnimate()){
            builder.dontAnimate();
        }
        return new GlideLoadAgent(builder, config);
    }

    @Override
    public void clearMemoryCache(Context context) {
        Glide.get(context).clearMemory();
    }

    @Override
    public void clearDiskCache(Context context) {
        Glide.get(context).clearDiskCache();
    }

    @Override
    public void pauseLoad(Context context) {
        Glide.with(context).pauseRequests();
    }

    @Override
    public void resumeLoad(Context context) {
        Glide.with(context).resumeRequests();
    }

    @Override
    public void pauseLoad(Fragment fragment) {
        Glide.with(fragment).pauseRequests();
    }

    @Override
    public void resumeLoad(Fragment fragment) {
        Glide.with(fragment).resumeRequests();
    }

    public void clear(View view) {
        Glide.clear(view);
    }
}
