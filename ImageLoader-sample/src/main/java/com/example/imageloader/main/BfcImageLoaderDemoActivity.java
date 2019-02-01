package com.example.imageloader.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.eebbk.bfc.imageload.ImageLoader;
import com.eebbk.bfc.imageload.SDKVersion;
import com.example.imageloader.R;
import com.example.imageloader.open.FrescoTestActivity;
import com.example.imageloader.open.GlideTestActivity;
import com.example.imageloader.open.PicassoTestActivity;
import com.example.imageloader.support.BaseActivity;

public class BfcImageLoaderDemoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bfc_image_loader_test);

        TextView mBuildTimeTv = (TextView) findViewById(R.id.buildTime);
        StringBuilder builder = new StringBuilder();
        builder.append("版本信息：\n");
        builder.append("版本名：").append(SDKVersion.getVersionName()).append("\n");
        builder.append("构建信息").append(SDKVersion.getBuildName()).append("\n");
        builder.append("构建时间：").append(SDKVersion.getBuildTime()).append("\n");
        builder.append("版本号：").append(SDKVersion.getSDKInt());
        mBuildTimeTv.setText(builder);
    }

    public void picassoTest(View view) {
        Intent intent = new Intent(this, PicassoTestActivity.class);
        startActivity(intent);
    }

    public void frescoTest(View view) {
        Intent intent = new Intent(this, FrescoTestActivity.class);
        startActivity(intent);
    }

    public void glideTest(View view) {
        Intent intent = new Intent(this, GlideTestActivity.class);
        startActivity(intent);
    }

    public void BfcImageLoaderTest(View view) {
        Intent intent = new Intent(this, ImageLoadTestActivity.class);
        startActivity(intent);
    }

    public void loadTypeTest(View view) {
        Intent intent = new Intent(this, LoadTypeTestActivity.class);
        startActivity(intent);
    }

    public void loadPriorityTest(View view) {
        Intent intent = new Intent(this, LoadPriorityTestActivity.class);
        startActivity(intent);
    }

    public void gridViewTest(View view) {
        Intent intent = new Intent(this, SampleGridViewActivity.class);
        startActivity(intent);
    }

    public void BfcImageLoaderConfTest(View view) {
        Intent intent = new Intent(this, ImageLoadConfTestActivity.class);
        startActivity(intent);
    }

    public void clearMemory(View view) {
        ImageLoader.getInstance().clearMemoryCache(this);
    }

    public void clearDisk(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ImageLoader.getInstance().clearDiskCache(BfcImageLoaderDemoActivity.this);
            }
        }).start();
    }
}
