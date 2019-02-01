package com.eebbk.bfc.imageload;

import android.widget.ImageView;

import com.eebbk.bfc.imageload.config.ImageLoadConfig;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * ImageLoaderTest
 */
public class ImageLoaderTest {
    @Test
    public void initDefaultConf() throws Exception {
        ImageLoadConfig tempConfig = new ImageLoadConfig.Builder().setErrorResId(0).setPlaceHolderResId(1).setPriority(ImageLoadConfig.LoadPriority.HIGH)
                .setCacheStrategy(ImageLoadConfig.CacheStrategy.NONE).setScaleType(ImageView.ScaleType.FIT_CENTER).setImageType(ImageLoadConfig.ImageType.BITMAP)
                .setTargetSize(new ImageLoadConfig.TargetSize(100, 500)).build();
        ImageLoader.initDefaultConf(tempConfig);
        assertEquals(ImageLoader.getInstance().getDefaultConf().getPlaceHolderResId(), 1);
        assertEquals(ImageLoader.getInstance().getDefaultConf().getErrorResId(), 0);
        assertEquals(ImageLoader.getInstance().getDefaultConf().getPriority(), ImageLoadConfig.LoadPriority.HIGH);
        assertEquals(ImageLoader.getInstance().getDefaultConf().getCacheStrategy(), ImageLoadConfig.CacheStrategy.NONE);
        assertEquals(ImageLoader.getInstance().getDefaultConf().getScaleType(), ImageView.ScaleType.FIT_CENTER);
        assertEquals(ImageLoader.getInstance().getDefaultConf().getTargetSize().getHeight(), 500);
        assertEquals(ImageLoader.getInstance().getDefaultConf().getTargetSize().getWidth(), 100);
        assertEquals(ImageLoader.getInstance().getDefaultConf().getImageType(), ImageLoadConfig.ImageType.BITMAP);
    }

}