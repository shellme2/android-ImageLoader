package com.eebbk.bfc.imageload.config;

import android.graphics.Bitmap;
import android.widget.ImageView;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * ImageLoadConfigTest
 */
public class ImageLoadConfigTest {

    private ImageLoadConfig config = new ImageLoadConfig.Builder().build();

    @Test
    public void parseBuilder() throws Exception {
        ImageLoadConfig tempConfig = new ImageLoadConfig.Builder().setErrorResId(0).setPlaceHolderResId(1).setPriority(ImageLoadConfig.LoadPriority.HIGH)
                .setCacheStrategy(ImageLoadConfig.CacheStrategy.NONE).setScaleType(ImageView.ScaleType.FIT_CENTER)
                .setTargetSize(new ImageLoadConfig.TargetSize(100, 500)).build();
        ImageLoadConfig currentConfig = ImageLoadConfig.parseBuilder(tempConfig).build();

        assertEquals(currentConfig.getPlaceHolderResId(), 1);
        assertEquals(currentConfig.getErrorResId(), 0);
        assertEquals(currentConfig.getPriority(), ImageLoadConfig.LoadPriority.HIGH);
        assertEquals(currentConfig.getCacheStrategy(), ImageLoadConfig.CacheStrategy.NONE);
        assertEquals(currentConfig.getScaleType(), ImageView.ScaleType.FIT_CENTER);
        assertEquals(currentConfig.getTargetSize().getHeight(), 500);
        assertEquals(currentConfig.getTargetSize().getWidth(), 100);
    }

    @Test
    public void getConfig() throws Exception {
        assertEquals(config.getConfig(), null);
        ImageLoadConfig currentConfig = ImageLoadConfig.parseBuilder(config).setConfig(Bitmap.Config.ARGB_8888).build();
        assertEquals(currentConfig.getConfig(), Bitmap.Config.ARGB_8888);
    }

    @Test
    public void getErrorResId() throws Exception {
        assertEquals(config.getErrorResId(), 0);
        ImageLoadConfig currentConfig = ImageLoadConfig.parseBuilder(config).setErrorResId(1024).build();
        assertEquals(currentConfig.getErrorResId(), 1024);
        currentConfig = ImageLoadConfig.parseBuilder(config).setErrorResId(0).build();
        assertEquals(currentConfig.getErrorResId(), 0);
        currentConfig = ImageLoadConfig.parseBuilder(config).setErrorResId(-1).build();
        assertEquals(currentConfig.getErrorResId(), -1);
        currentConfig = ImageLoadConfig.parseBuilder(config).setErrorResId(Integer.MAX_VALUE).build();
        assertEquals(currentConfig.getErrorResId(), Integer.MAX_VALUE);
    }

    @Test
    public void isFade() throws Exception {
        assertEquals(config.isFade(), false);
        ImageLoadConfig currentConfig = ImageLoadConfig.parseBuilder(config).enableFade(true).build();
        assertEquals(currentConfig.isFade(), true);
        currentConfig = ImageLoadConfig.parseBuilder(config).enableFade(false).build();
        assertEquals(currentConfig.isFade(), false);
    }

    @Test
    public void getPlaceHolderResId() throws Exception {
        assertEquals(config.getPlaceHolderResId(), 0);
        ImageLoadConfig currentConfig = ImageLoadConfig.parseBuilder(config).setPlaceHolderResId(1024).build();
        assertEquals(currentConfig.getPlaceHolderResId(), 1024);
        currentConfig = ImageLoadConfig.parseBuilder(config).setPlaceHolderResId(0).build();
        assertEquals(currentConfig.getPlaceHolderResId(), 0);
        currentConfig = ImageLoadConfig.parseBuilder(config).setPlaceHolderResId(-1).build();
        assertEquals(currentConfig.getPlaceHolderResId(), -1);
        currentConfig = ImageLoadConfig.parseBuilder(config).setPlaceHolderResId(Integer.MAX_VALUE).build();
        assertEquals(currentConfig.getPlaceHolderResId(), Integer.MAX_VALUE);
    }

    @Test
    public void getPriority() throws Exception {
        assertEquals(config.getPriority(), ImageLoadConfig.LoadPriority.NORMAL);
        ImageLoadConfig currentConfig = ImageLoadConfig.parseBuilder(config).setPriority(ImageLoadConfig.LoadPriority.LOW).build();
        assertEquals(currentConfig.getPriority(), ImageLoadConfig.LoadPriority.LOW);
        currentConfig = ImageLoadConfig.parseBuilder(config).setPriority(ImageLoadConfig.LoadPriority.NORMAL).build();
        assertEquals(currentConfig.getPriority(), ImageLoadConfig.LoadPriority.NORMAL);
        currentConfig = ImageLoadConfig.parseBuilder(config).setPriority(ImageLoadConfig.LoadPriority.HIGH).build();
        assertEquals(currentConfig.getPriority(), ImageLoadConfig.LoadPriority.HIGH);
    }

    @Test
    public void getScaleType() throws Exception {
        assertEquals(config.getScaleType(), null);
        ImageLoadConfig currentConfig = ImageLoadConfig.parseBuilder(config).setScaleType(ImageView.ScaleType.CENTER).build();
        assertEquals(currentConfig.getScaleType(), ImageView.ScaleType.CENTER);
        currentConfig = ImageLoadConfig.parseBuilder(config).setScaleType(ImageView.ScaleType.CENTER_CROP).build();
        assertEquals(currentConfig.getScaleType(), ImageView.ScaleType.CENTER_CROP);
        currentConfig = ImageLoadConfig.parseBuilder(config).setScaleType(ImageView.ScaleType.CENTER_INSIDE).build();
        assertEquals(currentConfig.getScaleType(), ImageView.ScaleType.CENTER_INSIDE);
        currentConfig = ImageLoadConfig.parseBuilder(config).setScaleType(ImageView.ScaleType.FIT_CENTER).build();
        assertEquals(currentConfig.getScaleType(), ImageView.ScaleType.FIT_CENTER);
        currentConfig = ImageLoadConfig.parseBuilder(config).setScaleType(ImageView.ScaleType.FIT_END).build();
        assertEquals(currentConfig.getScaleType(), ImageView.ScaleType.FIT_END);
        currentConfig = ImageLoadConfig.parseBuilder(config).setScaleType(ImageView.ScaleType.FIT_START).build();
        assertEquals(currentConfig.getScaleType(), ImageView.ScaleType.FIT_START);
    }

    @Test
    public void getTargetSize() throws Exception {
        assertEquals(config.getTargetSize(), null);
        ImageLoadConfig currentConfig = ImageLoadConfig.parseBuilder(config).setTargetSize(new ImageLoadConfig.TargetSize(500, 500)).build();
        assertEquals(currentConfig.getTargetSize().getHeight(), 500);
        assertEquals(currentConfig.getTargetSize().getWidth(), 500);
    }

    @Test
    public void getTransformations() throws Exception {

    }

    @Test
    public void getCacheStrategy() throws Exception {
        assertEquals(config.getCacheStrategy(), ImageLoadConfig.CacheStrategy.ALL);
        ImageLoadConfig currentConfig = ImageLoadConfig.parseBuilder(config).setCacheStrategy(ImageLoadConfig.CacheStrategy.ALL).build();
        assertEquals(currentConfig.getCacheStrategy(), ImageLoadConfig.CacheStrategy.ALL);
        currentConfig = ImageLoadConfig.parseBuilder(config).setCacheStrategy(ImageLoadConfig.CacheStrategy.NONE).build();
        assertEquals(currentConfig.getCacheStrategy(), ImageLoadConfig.CacheStrategy.NONE);
        currentConfig = ImageLoadConfig.parseBuilder(config).setCacheStrategy(ImageLoadConfig.CacheStrategy.MEMORY).build();
        assertEquals(currentConfig.getCacheStrategy(), ImageLoadConfig.CacheStrategy.MEMORY);
        currentConfig = ImageLoadConfig.parseBuilder(config).setCacheStrategy(ImageLoadConfig.CacheStrategy.DISK).build();
        assertEquals(currentConfig.getCacheStrategy(), ImageLoadConfig.CacheStrategy.DISK);
    }

    @Test
    public void getCustomConf() throws Exception {
        assertEquals(config.getCustomConf(), null);
    }

}