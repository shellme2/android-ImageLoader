package com.eebbk.bfc.imageload.config;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.load.Transformation;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * custom image loader config.
 */

public class ImageLoadConfig {

    private int placeHolderResId;
    private int errorResId;

    /**
     * image display way, see {@link android.widget.ImageView.ScaleType}
     */
    private ImageView.ScaleType scaleType;
    private TargetSize targetSize;
    private LoadPriority priority = LoadPriority.NORMAL;
    private CacheStrategy cacheStrategy = CacheStrategy.ALL;
    private Bitmap.Config config;
    private List<Transformation> transformations;
    private Map<String, String> customConf;
    private ImageType imageType;

    private boolean enableAnimate = true;
    private boolean isFade = false;
    /**
     * disk cache priority, now support four level strategy
     * <p>
     * {@link CacheStrategy#ALL} enable memory cache add disk cache<br/>
     * {@link CacheStrategy#MEMORY} enable memory cache<br/>
     * {@link CacheStrategy#DISK} enable disk cache<br/>
     * {@link CacheStrategy#NONE} disable memory cache add disk cache<br/>
     */
    public enum CacheStrategy {
        ALL,
        MEMORY,
        DISK,
        NONE;
    }

    /**
     * image load priority, support three level priority:
     * <p>
     * {@link LoadPriority#LOW}<br/>
     * {@link LoadPriority#NORMAL}<br/>
     * {@link LoadPriority#HIGH}<br/>
     */
    public enum LoadPriority {
        LOW,
        NORMAL,
        HIGH
    }

    public enum ImageType {
        BITMAP,
        GIF
    }

    private ImageLoadConfig(ImageLoadConfig.Builder builder) {
        this.placeHolderResId = builder.placeHolderResId;
        this.errorResId = builder.errorResId;
        this.scaleType = builder.scaleType;
        this.targetSize = builder.targetSize;
        this.priority = builder.priority;
        this.cacheStrategy = builder.cacheStrategy;
        this.config = builder.config;
        this.transformations = builder.transformations;
        this.isFade = builder.isFade;
        this.customConf = builder.customConf;
        this.imageType = builder.imageType;
        this.enableAnimate = builder.enableAnimate;
    }

    /**
     * Image loader builder
     */
    public static class Builder {
        private int placeHolderResId;
        private int errorResId;
        private ImageView.ScaleType scaleType;
        private TargetSize targetSize;
        private LoadPriority priority = LoadPriority.NORMAL;
        private CacheStrategy cacheStrategy = CacheStrategy.ALL;
        private Bitmap.Config config;
        private List<Transformation> transformations;
        private Map<String, String> customConf;

        private ImageType imageType;

        private boolean enableAnimate = true;
        private boolean isFade = false;

        /**
         * set cache strategy, see {@link CacheStrategy}
         */
        public Builder setCacheStrategy(@NonNull CacheStrategy cacheStrategy) {
            this.cacheStrategy = cacheStrategy;
            return this;
        }

        /**
         * set image load weather fade in or not
         */
        public Builder enableFade(boolean fade) {
            isFade = fade;
            return this;
        }

        /**
         * set image {@link android.graphics.Bitmap.Config}
         *
         * @deprecated nor realize
         */
        @Deprecated
        public Builder setConfig(@NonNull Bitmap.Config config) {
            this.config = config;
            return this;
        }

        /**
         * set place holder image when image load fail
         */
        public Builder setErrorResId(int errorResId) {
            this.errorResId = errorResId;
            return this;
        }

        /**
         * set place holder image when image before or during load
         */
        public Builder setPlaceHolderResId(int placeHolderResId) {
            this.placeHolderResId = placeHolderResId;
            return this;
        }

        /**
         * set current view's load priority, see {@link LoadPriority}
         */
        public Builder setPriority(@NonNull LoadPriority priority) {
            this.priority = priority;
            return this;
        }

        /**
         * set image view scale type, see {@link android.widget.ImageView.ScaleType}
         */
        public Builder setScaleType(@NonNull ImageView.ScaleType scaleType) {
            this.scaleType = scaleType;
            return this;
        }

        /**
         * Overrides the image 's width and height finally show in ImageView with the given values. see {@link TargetSize}
         * <p>
         * This is useful for thumbnails, and should only be used for other cases when you need a very specific image size.
         */
        public Builder setTargetSize(@NonNull TargetSize targetSize) {
            this.targetSize = targetSize;
            return this;
        }

        /**
         * set image transformation after image load successful.
         * <p>
         * lib supply transformation:<br/>
         * {@link jp.wasabeef.glide.transformations.BlurTransformation}<br/>
         * {@link jp.wasabeef.glide.transformations.CropCircleTransformation}<br/>
         * {@link jp.wasabeef.glide.transformations.RoundedCornersTransformation}<br/>
         */
        public Builder setTransformations(@NonNull Transformation... transformations) {
            this.transformations = Arrays.asList(transformations);
            return this;
        }

        /**
         * set custom configure info
         */
        public Builder setCustomConf(Map<String, String> customConf) {
            this.customConf = customConf;
            return this;
        }

        /**
         * set cache strategy, see {@link CacheStrategy}
         */
        public Builder setImageType(@NonNull ImageType imageType) {
            this.imageType = imageType;
            return this;
        }

        /**
         * set cache strategy, see {@link CacheStrategy}
         */
        public Builder dontAnimate() {
            this.enableAnimate = false;
            return this;
        }


        public ImageLoadConfig build() {
            return new ImageLoadConfig(this);
        }
    }

    /**
     * create configure from exists configure
     */
    public static Builder parseBuilder(ImageLoadConfig config) {
        Builder builder = new Builder();
        builder.placeHolderResId = config.placeHolderResId;
        builder.errorResId = config.errorResId;
        builder.scaleType = config.scaleType;
        builder.targetSize = config.targetSize;
        builder.priority = config.priority;
        builder.cacheStrategy = config.cacheStrategy;
        builder.config = config.config;
        builder.transformations = config.transformations;
        builder.isFade = config.isFade;
        builder.customConf = config.customConf;
        builder.imageType = config.imageType;
        builder.enableAnimate = config.enableAnimate;

        return builder;
    }

    public Bitmap.Config getConfig() {
        return config;
    }

    public int getErrorResId() {
        return errorResId;
    }

    public boolean isFade() {
        return isFade;
    }

    public int getPlaceHolderResId() {
        return placeHolderResId;
    }

    public LoadPriority getPriority() {
        return priority;
    }

    public ImageView.ScaleType getScaleType() {
        return scaleType;
    }

    public TargetSize getTargetSize() {
        return targetSize;
    }

    public List<Transformation> getTransformations() {
        return transformations;
    }

    public CacheStrategy getCacheStrategy() {
        return cacheStrategy;
    }

    public Map<String, String> getCustomConf() {
        return customConf;
    }

    public ImageType getImageType() {
        return imageType;
    }

    public boolean dontAnimate() {
        return enableAnimate;
    }

    /**
     * final size of image show in imageView
     */
    public static class TargetSize {
        private final int width;
        private final int height;

        public TargetSize(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }
}
