package com.example.imageloader.open;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.imageloader.R;
import com.example.imageloader.support.TestConfig;
import com.facebook.drawee.view.SimpleDraweeView;

public class FrescoTestActivity extends Activity {

    private static final String TAG = "FrescoTestActivity";

    SimpleDraweeView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_test);

        iv = (SimpleDraweeView) findViewById(R.id.frescoTestIv);

//        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());
//        GenericDraweeHierarchy hierarchy = builder
////                .setFailureImage(TestConfig.getTestResource())
////                .setPlaceholderImage(R.drawable.placeholder)
////                .setProgressBarImage(R.drawable.error)
////                .setRetryImage(R.drawable.placeholder)
//                .build();
//        iv.setHierarchy(hierarchy);

        Log.d(TAG, "fresco: start ");
//        final long frescoTime = System.currentTimeMillis();
//        DraweeController controller = Fresco.newDraweeControllerBuilder()
//                .setUri(TestConfig.getTestNetResource())
//                .setControllerListener(new BaseControllerListener<ImageInfo>() {
//                    @Override
//                    public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
//                        Log.d(TAG, "fresco: end a" + (System.currentTimeMillis() - frescoTime));
//                    }
//                })
//                .setAutoPlayAnimations(true)
//                .build();
//        iv.setController(controller);
        iv.setImageURI(TestConfig.getTestNetResource());

    }
}
