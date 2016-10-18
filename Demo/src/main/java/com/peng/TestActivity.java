package com.peng;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.peng.frescoprogress.R;
import com.peng.progress.CircleProgress;

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
        test_image.setImageURI("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1476777368&di=d1a23b61728cc0de8233ccc030e2907a&src=http://img4q.duitang.com/uploads/item/201507/14/20150714105000_zMeTj.thumb.700_0.jpeg");

        new CircleProgress.Builder()
                .setTextShow(true)
                .setRingWidth(7f)
                .setRingRadius(40)
                .setTextColor(Color.GREEN)
                .setTextSize(25)
                .build().inject(test_image);
    }

    @Override
    protected void onDestroy() {
        ImagePipeline pipeline = Fresco.getImagePipeline();
        pipeline.clearCaches();
        super.onDestroy();
    }
}
