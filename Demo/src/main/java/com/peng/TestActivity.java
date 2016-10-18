package com.peng;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.peng.frescoprogress.R;
import com.peng.progress.CircleProgress;
import com.peng.progress.CircleStyle;

/**
 * Created by peng on 16-10-18.
 */
public class TestActivity extends Activity {

    SimpleDraweeView test_image;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test_image = (SimpleDraweeView) findViewById(R.id.test_image);
        test_image.setImageURI("http://45.79.105.220:8080/images/mbuntu-11.jpg");

        new CircleProgress.Builder().setCircleStyle(CircleStyle.FAN).setCircleRadius(30).build().inject(test_image);
    }

    @Override
    protected void onDestroy() {
        ImagePipeline pipeline = Fresco.getImagePipeline();
        pipeline.clearCaches();
        super.onDestroy();
    }
}
