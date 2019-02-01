package com.example.imageloader.main;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.eebbk.bfc.imageload.ImageLoader;
import com.eebbk.bfc.imageload.config.ImageLoadConfig;
import com.eebbk.bfc.imageload.toolbox.ImageLoadCallBack;
import com.eebbk.bfc.imageload.toolbox.ImageTarget;
import com.example.imageloader.R;
import com.example.imageloader.support.CircleImageView;

import java.util.concurrent.ExecutionException;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class ImageLoadTestActivity extends Activity {
    private static final String TAG = "ImageLoadTestActivity";

    ImageView imageView;
    ImageLoadConfig conf = new ImageLoadConfig.Builder()
        .setImageType(ImageLoadConfig.ImageType.BITMAP)
        .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_load_test);

        imageView = (ImageView) findViewById(R.id.mine_above_avatar);
        Log.d(TAG, "ImageLoad: start ");

        ImageLoader.getInstance().load(this, R.drawable.jpg, conf, null).into(new ImageTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource) {
                imageView.setImageBitmap(resource);
            }
        });

        new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    Bitmap bitmap = ImageLoader.getInstance().load(ImageLoadTestActivity.this, R.drawable.jpg, conf, null).into(500, 500).get();
                    Log.d(TAG, "run: " + bitmap.getWidth() + "  " + bitmap.getHeight());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }.start();

//        try {
//            Bitmap bitmap = Glide.with(this).load(R.drawable.jpg).asBitmap().into(500, 500).get();
//            imageView.setImageBitmap(bitmap);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        ImageLoader.getInstance().load(this, R.drawable.jpg, imageView);

//        ImageLoader.getInstance().load(this,
//                "http://7xj5ov.com2.z0.glb.clouddn.com/vtraining/2017/01/12/500S239000144/113854928_b8f8d02733a76130.png",
//                new ImageLoadConfig.Builder().setPlaceHolderResId(R.drawable.temp).setErrorResId(R.drawable.error).dontAnimate().enableFade(true).build(), null).into(imageView);
//"http://file.eebbk.net/server-studyui/cloudIDN/studyui/2016/11/14/195446085_1047b46c7b00ffe5.jpeg";
//        Glide.with(this).load("http://7xj5ov.com2.z0.glb.clouddn.com/vtraining/2017/01/12/500S239000144/113854928_b8f8d02733a76130.png")
//                .placeholder(R.drawable.temp)
//                .dontAnimate()
//                .into(imageView);
    }
}
