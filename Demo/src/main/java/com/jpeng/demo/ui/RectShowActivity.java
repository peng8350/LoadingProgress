package com.jpeng.demo.ui;


import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import com.jpeng.demo.R;
import com.jpeng.demo.adapter.RectganleAdapter;
import com.jpeng.demo.bean.RectLoadInfo;
import com.jpeng.progress.RectangleProgress;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peng on 16-10-19.
 */
public class RectShowActivity extends AppCompatActivity {
    private List<RectLoadInfo> mDatas;
    private ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mListView = (ListView) findViewById(R.id.myListview);
        initData();
    }

    private void initData() {
        mDatas = new ArrayList<>();
        RectLoadInfo info1 = new RectLoadInfo("默认不设置的加载", "什么都不设置,默认的长方加载", "http://pic.paopaoche.net/up/2015-6/201569164043.jpg");
        mDatas.add(info1);
        RectLoadInfo info2 = new RectLoadInfo("进度条颜色变化", "可以改变进度条底部以及进度颜色", "http://pic.paopaoche.net/up/2015-6/201569164043.jpg");
        info2.setBottomColor(0x884A96D8);
        info2.setProgressColor(0xaaA9D84A);
        mDatas.add(info2);
        RectLoadInfo info3 = new RectLoadInfo("图片指示器", "使用图片,代替文字展示方式,一旦使用图片,文字将不会改变", "http://pic.paopaoche.net/up/2015-6/201569164043.jpg");
        info3.setProgress_image(BitmapFactory.decodeResource(getResources(),R.drawable.plane));
        mDatas.add(info3);

        RectLoadInfo info4 = new RectLoadInfo("指示器位置改变", "文字和图片可以改变适当的位置,上,中，下", "http://pic.paopaoche.net/up/2015-6/201569164043.jpg");
        info4.setProgress_image(BitmapFactory.decodeResource(getResources(),R.drawable.plane));
        info4.setPosition(RectangleProgress.TOP);
        mDatas.add(info4);
        mListView.setAdapter(new RectganleAdapter(this, R.layout.item_list, mDatas));
    }
}
