package com.eebbk.bfc.imageload.toolbox;

/**
 * image load call back.
 */
public interface ImageLoadCallBack {

    /**
     * load success
     *
     * @param loadSource        image source
     * @param isFromMemoryCache is image load from memory
     * @param isFirstResource   is image load from disk or net
     */
    void onSuccess(Object loadSource, boolean isFromMemoryCache, boolean isFirstResource);

    /**
     * load fail
     *
     * @param e          exception
     * @param loadSource image source
     */
    void onError(Exception e, Object loadSource);
}
