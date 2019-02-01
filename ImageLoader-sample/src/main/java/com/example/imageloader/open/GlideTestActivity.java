package com.example.imageloader.open;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.imageloader.R;
import com.example.imageloader.support.TestConfig;

public class GlideTestActivity extends Activity {

    private static final String TAG = "GlideTestActivity";

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_test);

        iv = (ImageView) findViewById(R.id.glideTestIv);
        Log.d(TAG, "glide: start ");
        final long glideTime = System.currentTimeMillis();
        Glide.with(this).load(TestConfig.getTestNetResource()).into(iv);

//        Glide.with(this).load(TestConfig.getTestNetResource()).into(new GlideDrawableImageViewTarget(iv) {
//            @Override
//            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
//                super.onResourceReady(resource, animation);
//                Log.d(TAG, "glide: end " + (System.currentTimeMillis() - glideTime));
//            }
//        });
    }
}
