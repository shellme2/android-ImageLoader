package com.example.imageloader.support;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.load.Transformation;
import com.eebbk.bfc.imageload.config.ImageLoadConfig;
import com.example.imageloader.R;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by lzy on 2016/11/15.
 */

public class ConfigTools {

    public static int getPlaceHolderResIdConfig(String configString) {
        switch (configString) {
            case "true":
                return R.drawable.placeholder;
            case "false":
                return 0;
        }
        throw new IllegalArgumentException("wrong config");
    }

    public static int getErrorResIdConfig(String configString) {
        switch (configString) {
            case "true":
                return R.drawable.error;
            case "false":
                return 0;
        }
        throw new IllegalArgumentException("wrong config");
    }

    public static ImageView.ScaleType getScaleTypeConfig(String configString) {
        switch (configString) {
            case "MATRIX":
                return ImageView.ScaleType.MATRIX;
            case "FIT_XY":
                return ImageView.ScaleType.FIT_XY;
            case "FIT_START":
                return ImageView.ScaleType.FIT_START;
            case "FIT_CENTER":
                return ImageView.ScaleType.FIT_CENTER;
            case "FIT_END":
                return ImageView.ScaleType.FIT_END;
            case "CENTER":
                return ImageView.ScaleType.CENTER;
            case "CENTER_CROP":
                return ImageView.ScaleType.CENTER_CROP;
            case "CENTER_INSIDE":
                return ImageView.ScaleType.CENTER_INSIDE;
        }
        throw new IllegalArgumentException("wrong config");
    }

    public static ImageLoadConfig.TargetSize getTargetSizeConfig(String configString) {
        switch (configString) {
            case "100*100":
                return new ImageLoadConfig.TargetSize(100, 100);
            case "200*200":
                return new ImageLoadConfig.TargetSize(200, 200);
            case "300*300":
                return new ImageLoadConfig.TargetSize(300, 300);
            case "500*500":
                return new ImageLoadConfig.TargetSize(500, 500);
            case "原图":
                return null;
        }
        throw new IllegalArgumentException("wrong config");
    }

    public static ImageLoadConfig.LoadPriority getPriorityConfig(String configString) {
        switch (configString) {
            case "LOW":
                return ImageLoadConfig.LoadPriority.LOW;
            case "NORMAL":
                return ImageLoadConfig.LoadPriority.NORMAL;
            case "HIGH":
                return ImageLoadConfig.LoadPriority.HIGH;
        }
        throw new IllegalArgumentException("wrong config");
    }

    public static ImageLoadConfig.CacheStrategy getCacheStrategyConfig(String configString) {
        switch (configString) {
            case "ALL":
                return ImageLoadConfig.CacheStrategy.ALL;
            case "MEMORY":
                return ImageLoadConfig.CacheStrategy.MEMORY;
            case "DISK":
                return ImageLoadConfig.CacheStrategy.DISK;
            case "NONE":
                return ImageLoadConfig.CacheStrategy.NONE;
        }
        throw new IllegalArgumentException("wrong config");
    }

    public static Bitmap.Config getConfigConfig(String configString) {
        return null;
    }

    public static Transformation getTransformationsConfig(Context context, String configString) {
        switch (configString) {
            case "null":
                return null;
            case "模糊":
                return new BlurTransformation(context, 50);
            case "圆角":
                return new RoundedCornersTransformation(context, 30, 0);
            case "圆形":
                return new CropCircleTransformation(context);
        }
        throw new IllegalArgumentException("wrong config");
    }

    public static boolean getCallBackConfig(String configString) {
        switch (configString) {
            case "true":
                return true;
            case "false":
                return false;
        }
        throw new IllegalArgumentException("wrong config");
    }

    public static boolean getIsFadeConfig(String configString) {
        switch (configString) {
            case "true":
                return true;
            case "false":
                return false;
        }
        throw new IllegalArgumentException("wrong config");
    }

    public static boolean getDebugConfig(String configString) {
        switch (configString) {
            case "true":
                return true;
            case "false":
                return false;
        }
        throw new IllegalArgumentException("wrong config");
    }

    public static String getTestUrl(String configString) {
        switch (configString) {
            case "true":
                return TestConfig.getTestNetResource();
            case "false":
                return TestConfig.getFirstNetResource();
        }
        throw new IllegalArgumentException("wrong config");
    }
}
