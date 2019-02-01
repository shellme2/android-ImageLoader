package com.example.imageloader.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.eebbk.bfc.imageload.ImageLoader;
import com.eebbk.bfc.imageload.config.ImageLoadConfig;
import com.example.imageloader.R;
import com.example.imageloader.support.Data;
import com.example.imageloader.support.TestConfig;

public class LoadPriorityTestActivity extends Activity {

    ImageView imageViewLow;
    ImageView imageViewNormal;
    ImageView imageViewHigh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_priority_test);

        imageViewLow = (ImageView) findViewById(R.id.priorityTestLow);
        imageViewNormal = (ImageView) findViewById(R.id.priorityTestNormal);
        imageViewHigh = (ImageView) findViewById(R.id.priorityTestHigh);
        findViewById(R.id.processPriorityTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageLoadConfig config = new ImageLoadConfig.Builder().setPriority(ImageLoadConfig.LoadPriority.LOW)
                        .setCacheStrategy(ImageLoadConfig.CacheStrategy.NONE)
                        .setPlaceHolderResId(R.drawable.placeholder)
                        .setErrorResId(R.drawable.error)
                        .build();
                ImageLoader.getInstance().load(LoadPriorityTestActivity.this, Data.URLS[0], config, null).into(imageViewLow);
                config = new ImageLoadConfig.Builder().setPriority(ImageLoadConfig.LoadPriority.NORMAL)
                        .setCacheStrategy(ImageLoadConfig.CacheStrategy.NONE)
                        .setPlaceHolderResId(R.drawable.placeholder)
                        .setErrorResId(R.drawable.error)
                        .build();
                ImageLoader.getInstance().load(LoadPriorityTestActivity.this, Data.URLS[1], config, null).into(imageViewNormal);
                config = new ImageLoadConfig.Builder().setPriority(ImageLoadConfig.LoadPriority.HIGH)
                        .setCacheStrategy(ImageLoadConfig.CacheStrategy.NONE)
                        .setPlaceHolderResId(R.drawable.placeholder)
                        .setErrorResId(R.drawable.error)
                        .build();
                ImageLoader.getInstance().load(LoadPriorityTestActivity.this, Data.URLS[2], config, null).into(imageViewHigh);
            }
        });
    }
}
