package com.eebbk.bfc.imageload;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.request.FutureTarget;
import com.eebbk.bfc.imageload.toolbox.ImageTarget;

import java.util.concurrent.ExecutionException;

/**
 * Created by lzy on 2016/11/25.
 */

public interface IImageLoadAgent {

    void into(ImageView view);

    void into(ImageTarget target);

    FutureTarget<Bitmap> into(int width, int height) throws ExecutionException, InterruptedException;
}
