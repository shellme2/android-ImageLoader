package com.eebbk.bfc.imageload.inner;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.GifTypeRequest;
import com.bumptech.glide.request.FutureTarget;
import com.eebbk.bfc.imageload.IImageLoadAgent;
import com.eebbk.bfc.imageload.config.ImageLoadConfig;
import com.eebbk.bfc.imageload.toolbox.ImageTarget;

import java.util.concurrent.ExecutionException;

/**
 * load agent
 * <p>
 * Proxy pattern
 */

public class GlideLoadAgent implements IImageLoadAgent {

    private Object builder;
    private ImageLoadConfig config;

    private GlideLoadAgent() {
    }

    public GlideLoadAgent(DrawableRequestBuilder builder, ImageLoadConfig config) {
        this.builder = builder;
        this.config = config;
    }

    public GlideLoadAgent(BitmapTypeRequest builder, ImageLoadConfig config) {
        this.builder = builder;
        this.config = config;
    }

    public GlideLoadAgent(GifTypeRequest builder, ImageLoadConfig config) {
        this.builder = builder;
        this.config = config;
    }

    @Override
    public void into(ImageView imageView) {
        if (config.getScaleType() != null) {
            imageView.setScaleType(config.getScaleType());
        }
        if (builder instanceof DrawableRequestBuilder) {
            ((DrawableRequestBuilder) builder).into(imageView);
        } else if (builder instanceof BitmapTypeRequest) {
            ((BitmapTypeRequest) builder).into(imageView);
        } else if (builder instanceof GifTypeRequest) {
            ((GifTypeRequest) builder).into(imageView);
        }
    }

    @Override
    public void into(final ImageTarget target) {
        if (builder instanceof DrawableRequestBuilder) {
            ((DrawableRequestBuilder) builder).into(target);
        } else if (builder instanceof BitmapTypeRequest) {
            ((BitmapTypeRequest) builder).into(target);
        } else if (builder instanceof GifTypeRequest) {
            ((GifTypeRequest) builder).into(target);
        }
    }

    @Override
    public FutureTarget<Bitmap> into(int width, int height) throws ExecutionException, InterruptedException {
        if (builder instanceof BitmapTypeRequest) {
            return ((BitmapTypeRequest) builder).into(width, height);
        } else {
            throw new RuntimeException("type must be bitmap, you have to call setImageType(ImageLoadConfig.ImageType.BITMAP)");
        }
    }
}
