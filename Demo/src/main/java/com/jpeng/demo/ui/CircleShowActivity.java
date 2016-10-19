package com.jpeng.demo.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import com.jpeng.demo.R;
import com.jpeng.demo.adapter.CIrcleAdapter;
import com.jpeng.demo.bean.CircleLoadInfo;
import com.jpeng.progress.CircleStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peng on 16-10-19.
 */
public class CircleShowActivity extends AppCompatActivity {

    private List<CircleLoadInfo> mDatas;
    private ListView mListView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mListView = (ListView) findViewById(R.id.myListview);
        initData();
    }

    private void initData() {
        List<CircleLoadInfo> list =new ArrayList<>();
        CircleLoadInfo info1 = new CircleLoadInfo("默认加载", "不设置任何属性默认状态", "http://www.miui.com/forum.php?mod=attachment&aid=MzQ1MzI4NnwwMWM0Y2QyNHwxNDc2ODc1NjI5fDB8MzA5NzI1OQ%3D%3D&nothumb=yes");
        list.add(info1);
        CircleLoadInfo info2 = new CircleLoadInfo("字体可修改", "可改变字体大小和颜色", "http://www.miui.com/forum.php?mod=attachment&aid=MzQ1MzI4NnwwMWM0Y2QyNHwxNDc2ODc1NjI5fDB8MzA5NzI1OQ%3D%3D&nothumb=yes");
        info2.setTextColor(0xaaD84A88);
        info2.setTextSize(28);
        list.add(info2);

        CircleLoadInfo info3 = new CircleLoadInfo("园环特性", "可改变园环宽度和半径,颜色", "http://www.miui.com/forum.php?mod=attachment&aid=MzQ1MzI4NnwwMWM0Y2QyNHwxNDc2ODc1NjI5fDB8MzA5NzI1OQ%3D%3D&nothumb=yes");
        info3.setRadius(60);
        info3.setStrokeWidth(10);
        info3.setProgressColor(0xaaD74AD8);
        list.add(info3);

        CircleLoadInfo info4 = new CircleLoadInfo("园环渐变", "可增加渐变给园环", "http://www.miui.com/forum.php?mod=attachment&aid=MzQ1MzI4NnwwMWM0Y2QyNHwxNDc2ODc1NjI5fDB8MzA5NzI1OQ%3D%3D&nothumb=yes");
        info4.setGradient(new int[]{0xaaD74AD8,0xaaD84A88,0x884A96D8});
        list.add(info4);

        //两种类型
        CircleLoadInfo info5 = new CircleLoadInfo("园环风格", "两种不同的园风格", "http://www.miui.com/forum.php?mod=attachment&aid=MzQ1MzI4NnwwMWM0Y2QyNHwxNDc2ODc1NjI5fDB8MzA5NzI1OQ%3D%3D&nothumb=yes");
        info5.setStyle(CircleStyle.FAN);
        list.add(info5);
        mListView.setAdapter(new CIrcleAdapter(this,R.layout.item_list,list));
    }
}
