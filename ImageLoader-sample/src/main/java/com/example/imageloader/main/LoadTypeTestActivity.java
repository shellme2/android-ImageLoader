package com.example.imageloader.main;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.eebbk.bfc.imageload.ImageLoader;
import com.example.imageloader.R;
import com.example.imageloader.support.TestConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;

public class LoadTypeTestActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "LoadTypeTestActivity";

    ImageView picassoIv;
    SimpleDraweeView frescoIv;
    ImageView glideIv;
    ImageView imageLoadView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_type_test);
        picassoIv = (ImageView) findViewById(R.id.picasso);
        frescoIv = (SimpleDraweeView) findViewById(R.id.fresco);
        glideIv = (ImageView) findViewById(R.id.glide);
        imageLoadView = (ImageView) findViewById(R.id.imageLoader);
        findViewById(R.id.processJpeg).setOnClickListener(this);
        findViewById(R.id.processPng).setOnClickListener(this);
        findViewById(R.id.processWebp).setOnClickListener(this);
        findViewById(R.id.processXml).setOnClickListener(this);
        findViewById(R.id.processGif).setOnClickListener(this);
        findViewById(R.id.processUrl).setOnClickListener(this);
        findViewById(R.id.processR).setOnClickListener(this);
        findViewById(R.id.processAssets).setOnClickListener(this);
        findViewById(R.id.processFile).setOnClickListener(this);
        Picasso.with(this).setIndicatorsEnabled(true);
    }

    public void process(String url) {
        Log.d(TAG, "picasso: start ");
        long time = System.currentTimeMillis();
        Picasso.with(LoadTypeTestActivity.this).load(url)
                .into(picassoIv);
        Log.d(TAG, "picasso: end " + (System.currentTimeMillis() - time));

        Log.d(TAG, "fresco: start ");
        time = System.currentTimeMillis();
        frescoIv.setImageURI(url);
        Log.d(TAG, "fresco: end " + (System.currentTimeMillis() - time));

        Log.d(TAG, "glide: start ");
        time = System.currentTimeMillis();
        Glide.with(LoadTypeTestActivity.this).load(url).into(glideIv);
        Log.d(TAG, "glide: end " + (System.currentTimeMillis() - time));

        Log.d(TAG, "ImageLoader: start ");
        time = System.currentTimeMillis();
        ImageLoader.getInstance().load(LoadTypeTestActivity.this, url, R.drawable.placeholder, R.drawable.error, null).into(imageLoadView);
        Log.d(TAG, "ImageLoader: end " + (System.currentTimeMillis() - time));
    }

    public void process(int id) {
        Log.d(TAG, "picasso: start ");
        final long picassoTime = System.currentTimeMillis();
        Picasso.with(LoadTypeTestActivity.this).load(id).into(picassoIv, new Callback() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "picasso: end " + (System.currentTimeMillis() - picassoTime));
            }

            @Override
            public void onError() {
            }
        });

        Log.d(TAG, "fresco: start ");
        final long frescoTime = System.currentTimeMillis();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse("res:///" + id))
                .setControllerListener(new BaseControllerListener<ImageInfo>() {
                    @Override
                    public void onIntermediateImageSet(String id, ImageInfo imageInfo) {
                        super.onIntermediateImageSet(id, imageInfo);
                        Log.d(TAG, "fresco: end " + (System.currentTimeMillis() - frescoTime));
                    }
                })
                .setAutoPlayAnimations(true)
                .build();
        frescoIv.setController(controller);


        Log.d(TAG, "glide: start ");
        final long glideTime = System.currentTimeMillis();
        Glide.with(LoadTypeTestActivity.this).load(id).into(new GlideDrawableImageViewTarget(glideIv) {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                super.onResourceReady(resource, animation);
                Log.d(TAG, "glide: end " + (System.currentTimeMillis() - glideTime));
            }
        });

        Log.d(TAG, "ImageLoader: start ");
        final long ImageLoaderTime = System.currentTimeMillis();
        ImageLoader.getInstance().load(LoadTypeTestActivity.this, id, null, null).into(imageLoadView);
        Log.d(TAG, "ImageLoader: end " + (System.currentTimeMillis() - ImageLoaderTime));
    }

    public void process(Uri uri) {
        Log.d(TAG, "picasso: start ");
        final long picassoTime = System.currentTimeMillis();
        Picasso.with(LoadTypeTestActivity.this).load(uri).into(picassoIv, new Callback() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "picasso: end " + (System.currentTimeMillis() - picassoTime));
            }

            @Override
            public void onError() {
            }
        });

        frescoIv.setImageURI(uri);

        Log.d(TAG, "glide: start ");
        final long glideTime = System.currentTimeMillis();
        Glide.with(LoadTypeTestActivity.this).load(uri).into(new GlideDrawableImageViewTarget(glideIv) {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                super.onResourceReady(resource, animation);
                Log.d(TAG, "glide: end " + (System.currentTimeMillis() - glideTime));
            }
        });

        Log.d(TAG, "ImageLoader: start ");
        final long ImageLoaderTime = System.currentTimeMillis();
        ImageLoader.getInstance().load(LoadTypeTestActivity.this, uri, null, null).into(imageLoadView);
        Log.d(TAG, "ImageLoader: end " + (System.currentTimeMillis() - ImageLoaderTime));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.processJpeg:
                process(R.drawable.jpg);
                break;
            case R.id.processPng:
                process(R.drawable.png);
                break;
            case R.id.processWebp:
                process(R.drawable.webp);
                break;
            case R.id.processXml:
                process(R.drawable.xml);
                break;
            case R.id.processGif:
                process(R.drawable.gif);
                break;
            case R.id.processUrl:
                process(TestConfig.getTestNetResource());
                break;
            case R.id.processR:
                process(R.drawable.r);
                break;
            case R.id.processAssets:
                Uri assetsUri = Uri.parse("file:///android_asset/assets.jpg");
                process(assetsUri);
                break;
            case R.id.processFile:
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/file_test.jpg";
                process(path);
                break;
        }
    }

    private void clearCache() {
        Picasso.with(this).shutdown();
        Fresco.shutDown();
        Glide.get(this).clearDiskCache();
        Glide.get(this).clearMemory();
    }
}
