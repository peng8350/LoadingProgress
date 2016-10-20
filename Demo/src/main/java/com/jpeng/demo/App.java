package com.jpeng.demo;

import android.app.Application;
import android.content.Context;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import org.xutils.x;

/**
 * Created by peng on 16-10-18.
 */
public class App extends Application {

    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
        x.Ext.init(this);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();

        //Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
        Fresco.initialize(this);
    }
}
