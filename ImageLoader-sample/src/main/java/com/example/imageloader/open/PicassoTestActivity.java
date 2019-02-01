package com.example.imageloader.open;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.imageloader.R;
import com.example.imageloader.support.TestConfig;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class PicassoTestActivity extends Activity {

    private static final String TAG = "PicassoTestActivity";

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso_test);

        iv = (ImageView) findViewById(R.id.picassoTestIv);
        Picasso.with(this).setIndicatorsEnabled(true); //
        Log.d(TAG, "picasso: start ");
        final long picassoTime = System.currentTimeMillis();
        Picasso.with(this).load(TestConfig.getTestNetResource()) //
//                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
//                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                .into(iv, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.d(TAG, "picasso: end " + (System.currentTimeMillis() - picassoTime));
                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
