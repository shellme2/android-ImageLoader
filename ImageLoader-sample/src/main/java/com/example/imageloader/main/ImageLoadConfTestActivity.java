package com.example.imageloader.main;

import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.eebbk.bfc.imageload.ImageLoader;
import com.eebbk.bfc.imageload.config.ImageLoadConfig;
import com.eebbk.bfc.imageload.toolbox.ImageLoadCallBack;
import com.eebbk.bfc.imageload.tools.L;
import com.example.imageloader.R;
import com.example.imageloader.support.ConfigTools;

public class ImageLoadConfTestActivity extends FragmentActivity implements AdapterView.OnItemSelectedListener {

    private ImageView iv;
//    private ImageView iv2;

    private Spinner placeHolderResIdSpinner;
    private Spinner errorResIdSpinner;
    private Spinner scaleTypeSpinner;
    private Spinner targetSizeSpinner;
    private Spinner prioritySpinner;
    private Spinner cacheStrategySpinner;
    private Spinner configSpinner;
    private Spinner transformationsSpinner;
    private Spinner callBackSpinner;
    private Spinner isFadeSpinner;
    private Spinner debugSpinner;
    private Spinner testUrlSpinner;

    private EditText imageInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_load_conf_test);

        initView();
    }

    private void initView() {
        iv = (ImageView) findViewById(R.id.imageLoadTestIv);
//        iv2 = (ImageView) findViewById(R.id.imageLoadTestIv2);
        placeHolderResIdSpinner = (Spinner) findViewById(R.id.placeHolderResIdSpinner);
        placeHolderResIdSpinner.setSelection(0, false);
        errorResIdSpinner = (Spinner) findViewById(R.id.errorResIdSpinner);
        errorResIdSpinner.setSelection(0, false);
        scaleTypeSpinner = (Spinner) findViewById(R.id.scaleTypeSpinner);
        scaleTypeSpinner.setSelection(0, false);
        targetSizeSpinner = (Spinner) findViewById(R.id.targetSizeSpinner);
        targetSizeSpinner.setSelection(0, false);
        prioritySpinner = (Spinner) findViewById(R.id.prioritySpinner);
        prioritySpinner.setSelection(0, false);
        cacheStrategySpinner = (Spinner) findViewById(R.id.cacheStrategySpinner);
        cacheStrategySpinner.setSelection(0, false);
        configSpinner = (Spinner) findViewById(R.id.configSpinner);
        configSpinner.setSelection(0, false);
        transformationsSpinner = (Spinner) findViewById(R.id.transformationsSpinner);
        transformationsSpinner.setSelection(0, false);
        callBackSpinner = (Spinner) findViewById(R.id.callBackSpinner);
        callBackSpinner.setSelection(0, false);
        isFadeSpinner = (Spinner) findViewById(R.id.isFadeSpinner);
        isFadeSpinner.setSelection(0, false);
        debugSpinner = (Spinner) findViewById(R.id.debugSpinner);
        debugSpinner.setSelection(0, false);

        testUrlSpinner = (Spinner) findViewById(R.id.testUrlSpinner);
        testUrlSpinner.setSelection(0, false);

        imageInfo = (EditText) findViewById(R.id.imageInfo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        placeHolderResIdSpinner.setOnItemSelectedListener(this);
        errorResIdSpinner.setOnItemSelectedListener(this);
        scaleTypeSpinner.setOnItemSelectedListener(this);
        targetSizeSpinner.setOnItemSelectedListener(this);
        prioritySpinner.setOnItemSelectedListener(this);
        cacheStrategySpinner.setOnItemSelectedListener(this);
        configSpinner.setOnItemSelectedListener(this);
        transformationsSpinner.setOnItemSelectedListener(this);
        callBackSpinner.setOnItemSelectedListener(this);
        isFadeSpinner.setOnItemSelectedListener(this);
        debugSpinner.setOnItemSelectedListener(this);
        testUrlSpinner.setOnItemSelectedListener(this);
    }

    private void load(ImageLoadConfig config) {
        String url = ConfigTools.getTestUrl(testUrlSpinner.getSelectedItem().toString());

        ImageLoadCallBack callBack = null;
        if (ConfigTools.getCallBackConfig(callBackSpinner.getSelectedItem().toString())) {
            callBack = new ImageLoadCallBack() {
                @Override
                public void onSuccess(Object loadSource, boolean isFromMemoryCache, boolean isFirstResource) {
                    StringBuilder builder = new StringBuilder();
                    builder.append("loadSource : ").append(loadSource.toString()).append("\n\n");
                    builder.append("isFromMemoryCache : ").append(isFromMemoryCache).append("\n");
                    builder.append("isFirstResource : ").append(isFirstResource);
                    updateImageInfo(builder);
                }

                @Override
                public void onError(Exception e, Object loadSource) {
                    StringBuilder builder = new StringBuilder();
                    builder.append("loadSource : ").append(loadSource.toString()).append("\n\n");
                    builder.append("Exception : ").append(e);
                    updateImageInfo(builder);
                }
            };
        }

        ImageLoader.getInstance().load(this, url, config, callBack).into(iv);
//        Glide.with(this).load(url).into(iv2);
    }

    private void updateConfig() {
        imageInfo.setText("...");
        L.d("updateConfig: " + placeHolderResIdSpinner.getPrompt().toString() + "  =  " + placeHolderResIdSpinner.getSelectedItem().toString());
        L.d("updateConfig: " + errorResIdSpinner.getPrompt().toString() + "  =  " + errorResIdSpinner.getSelectedItem().toString());
        L.d("updateConfig: " + scaleTypeSpinner.getPrompt().toString() + "  =  " + scaleTypeSpinner.getSelectedItem().toString());
        L.d("updateConfig: " + targetSizeSpinner.getPrompt().toString() + "  =  " + targetSizeSpinner.getSelectedItem().toString());
        L.d("updateConfig: " + prioritySpinner.getPrompt().toString() + "  =  " + prioritySpinner.getSelectedItem().toString());
        L.d("updateConfig: " + cacheStrategySpinner.getPrompt().toString() + "  =  " + cacheStrategySpinner.getSelectedItem().toString());
        L.d("updateConfig: " + configSpinner.getPrompt().toString() + "  =  " + configSpinner.getSelectedItem().toString());
        L.d("updateConfig: " + transformationsSpinner.getPrompt().toString() + "  =  " + transformationsSpinner.getSelectedItem().toString());
        L.d("updateConfig: " + callBackSpinner.getPrompt().toString() + "  =  " + callBackSpinner.getSelectedItem().toString());
        L.d("updateConfig: " + isFadeSpinner.getPrompt().toString() + "  =  " + isFadeSpinner.getSelectedItem().toString());
        L.d("updateConfig: " + debugSpinner.getPrompt().toString() + "  =  " + debugSpinner.getSelectedItem().toString());
        L.d("updateConfig: " + testUrlSpinner.getPrompt().toString() + "  =  " + testUrlSpinner.getSelectedItem().toString());

        ImageLoadConfig.Builder builder = new ImageLoadConfig.Builder();
        builder.setPlaceHolderResId(ConfigTools.getPlaceHolderResIdConfig(placeHolderResIdSpinner.getSelectedItem().toString()))
                .setErrorResId(ConfigTools.getErrorResIdConfig(errorResIdSpinner.getSelectedItem().toString()));

        if (ConfigTools.getScaleTypeConfig(scaleTypeSpinner.getSelectedItem().toString()) == ImageView.ScaleType.MATRIX) {
            builder.setScaleType(ConfigTools.getScaleTypeConfig(scaleTypeSpinner.getSelectedItem().toString()));
            Matrix matrix = new Matrix();
            matrix.setRotate(-45, 500, 500);
            iv.setImageMatrix(matrix);
        } else {
            builder.setScaleType(ConfigTools.getScaleTypeConfig(scaleTypeSpinner.getSelectedItem().toString()));
        }
        if (ConfigTools.getTargetSizeConfig(targetSizeSpinner.getSelectedItem().toString()) != null) {
            builder.setTargetSize(ConfigTools.getTargetSizeConfig(targetSizeSpinner.getSelectedItem().toString()));
        }
        builder.setPriority(ConfigTools.getPriorityConfig(prioritySpinner.getSelectedItem().toString()))
                .setCacheStrategy(ConfigTools.getCacheStrategyConfig(cacheStrategySpinner.getSelectedItem().toString()))
                .setConfig(ConfigTools.getConfigConfig(configSpinner.getSelectedItem().toString()));
        if (ConfigTools.getTransformationsConfig(this, transformationsSpinner.getSelectedItem().toString()) != null) {
            builder.setTransformations(ConfigTools.getTransformationsConfig(this, transformationsSpinner.getSelectedItem().toString()));
        }
        builder.enableFade(ConfigTools.getIsFadeConfig(isFadeSpinner.getSelectedItem().toString()));
        if (ConfigTools.getDebugConfig(debugSpinner.getSelectedItem().toString())) {
        }

        L.i("updateConfig: load");
        load(builder.build());
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void clearMemory(View view) {
        ImageLoader.getInstance().clearMemoryCache(this);
    }

    public void clearDisk(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ImageLoader.getInstance().clearDiskCache(ImageLoadConfTestActivity.this);
            }
        }).start();
    }

    public void processLoad(View view) {
        updateConfig();
    }

    public void updateImageInfo(CharSequence msg) {
        StringBuilder builder = new StringBuilder();
        builder.append("image info:\n");
        builder.append(msg);
        imageInfo.setText(builder);
    }
}
