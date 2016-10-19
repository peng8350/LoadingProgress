package com.jpeng.demo;

import android.app.Application;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by peng on 16-10-18.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
