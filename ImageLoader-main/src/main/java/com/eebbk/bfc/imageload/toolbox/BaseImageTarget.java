package com.eebbk.bfc.imageload.toolbox;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * Created by lzy on 2016/12/8.
 */

public abstract class BaseImageTarget<T> extends SimpleTarget<T> {

    @Override
    public void onResourceReady(T resource, GlideAnimation<? super T> glideAnimation) {
        onResourceReady(resource);
    }

    public abstract void onResourceReady(T resource);
}
