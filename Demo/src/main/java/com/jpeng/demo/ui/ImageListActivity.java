package com.jpeng.demo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import com.jpeng.demo.R;
import com.jpeng.demo.adapter.TestListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ImageListActivity extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testlist);
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(new TestListAdapter(this,initData()));
    }

    private List<String> initData() {
        List<String> params = new ArrayList<>();
        params.add("http://img3.imgtn.bdimg.com/it/u=3836523636,1289548375&fm=21&gp=0.jpg");
        params.add("http://pic29.nipic.com/20130527/9908282_161422661106_2.jpg");
        params.add("http://pic31.nipic.com/20130802/13345615_083202734154_2.jpg");
        params.add("http://attach.bbs.miui.com/forum/201511/07/132520d88z6gtzb6uxv3nr.jpg");
        params.add("http://pic36.nipic.com/20131225/7430301_105108679169_2.jpg");
        params.add("http://image.tianjimedia.com/uploadImages/2011/256/SNF6L00G93G1.jpg");
        params.add("http://img1.pconline.com.cn/piclib/200806/30/batch/1/4874/12148410917504g81oh43u8.jpg");
        params.add("http://img1.imgtn.bdimg.com/it/u=13628483,3477655028&fm=21&gp=0.jpg");
        params.add("http://online.sccnn.com/desk/411/apple1440_1005.jpg");
        params.add("http://img4.imgtn.bdimg.com/it/u=673908225,1659058747&fm=21&gp=0.jpg");
        params.add("http://imgsrc.baidu.com/forum/pic/item/7c1ed21b0ef41bd5fd7e58ba51da81cb39db3d4a.jpg");
        params.add("http://img2.imgtn.bdimg.com/it/u=778813359,3457257917&fm=21&gp=0.jpg");
        params.add("http://img4.imgtn.bdimg.com/it/u=1403571317,917141231&fm=21&gp=0.jpg");
        params.add("http://img0.imgtn.bdimg.com/it/u=890628440,4072434260&fm=21&gp=0.jpg");
        params.add("http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1309/05/c4/25279801_1378348357336.jpg");
        params.add("http://photo.enterdesk.com/2008-7-12/200807120039381593.jpg");

        return params;
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}