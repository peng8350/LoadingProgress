package com.jpeng.demo.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.jpeng.demo.R;

/**
 * Created by peng on 16-10-19.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        Glide.get(this).clearMemory();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(MainActivity.this).clearDiskCache();
            }
        }).start();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                Intent intent1 = new Intent(this,CircleShowActivity.class);
                this.startActivity(intent1);
                break;

            case R.id.button2:
                Intent intent2 = new Intent(this,RectShowActivity.class);
                startActivity(intent2);
                break;
            case R.id.button3:
                Intent intent3 = new Intent(this,DifferentActivity.class);
                startActivity(intent3);
                break;
            case R.id.button4:
                Intent intent4 = new Intent(this,ImageListActivity.class);
                startActivity(intent4);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImagePipeline pipeline = Fresco.getImagePipeline();
        pipeline.clearCaches();
    }
}
