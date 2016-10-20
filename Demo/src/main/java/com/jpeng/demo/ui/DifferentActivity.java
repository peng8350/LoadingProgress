package com.jpeng.demo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import com.jpeng.demo.R;
import com.jpeng.demo.adapter.DifferentAdapter;
import com.jpeng.demo.bean.DifferentInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peng on 16-10-20.
 */
public class DifferentActivity extends AppCompatActivity {

    private ListView mListView;

	private List<DifferentInfo>	differents;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		mListView = (ListView) findViewById(R.id.myListview);
		initData();
	}

	private void initData() {
		differents = new ArrayList<>();
		DifferentInfo info1 = new DifferentInfo("UIL加载", "使用UIL进行图片加载",
				"http://pic.paopaoche.net/up/2015-6/201569164043.jpg");
		info1.setType(4);
		differents.add(info1);
		DifferentInfo info2 = new DifferentInfo("UIL加载", "使用UIL进行图片加载",
				"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1476972196&di=60882c9afa5b37b09a422d6adb506924&src=http://img1.pconline.com.cn/piclib/200806/30/batch/1/4874/12148410917491gh2j6vdfe.jpg");
		info2.setType(4);
		differents.add(info2);
		DifferentInfo info3 = new DifferentInfo("UIL加载", "使用UIL进行图片加载",
				"http://attach.bbs.miui.com/forum/201511/07/132520d88z6gtzb6uxv3nr.jpg");
		info3.setType(4);
		differents.add(info3);
		DifferentInfo info4 = new DifferentInfo("UIL加载", "使用UIL进行图片加载",
				"http://pic31.nipic.com/20130802/13345615_083202734154_2.jpg");
		info4.setType(4);
		differents.add(info4);
//		DifferentInfo info2 = new DifferentInfo("Picasso加载", "使用Picasso进行图片加载",
//				"http://pic.paopaoche.net/up/2015-6/201569164043.jpg");
//		info2.setType(2);
//		differents.add(info2);
//		DifferentInfo info3 = new DifferentInfo("XUTILS2加载", "使用XUTILS2进行图片加载","http://pic.paopaoche.net/up/2015-6/201569164043.jpg");
//		info3.setType(3);
//		differents.add(info3);

//			DifferentInfo info4 = new DifferentInfo("GLIDE加载", "使用GLIDE进行图片加载",
//					"http://pic.paopaoche.net/up/2015-6/201569164043.jpg");
//			info4.setType(4);
//			differents.add(info4);

//		DifferentInfo info5 = new DifferentInfo("XUTILS3加载", "使用XUTILS3进行图片加载","http://pic.paopaoche.net/up/2015-6/201569164043.jpg");
//		info5.setType(5);
//		differents.add(info5);
        mListView.setAdapter(new DifferentAdapter(this,R.layout.item_list2,differents));
    }
}
