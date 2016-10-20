package com.jpeng.demo.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;
import com.jpeng.demo.R;
import com.jpeng.demo.bean.RectLoadInfo;
import com.jpeng.progress.RectangleProgress;

import java.util.List;

/**
 * Created by peng on 16-10-19.
 */
public class RectganleAdapter extends QuickAdapter<RectLoadInfo> {
	public RectganleAdapter(Context context, int layoutResId, List<RectLoadInfo> data) {
		super(context, layoutResId, data);
	}

	@Override
	protected void convert(BaseAdapterHelper helper, final RectLoadInfo item) {
		helper.setText(R.id.item_list_title, item.getName());
		helper.setText(R.id.item_list_intro, item.getIntro());
		final SimpleDraweeView image = helper.getView(R.id.item_list_image);
		image.setImageURI(item.getUrl());
		generateProgress(item, image);
		helper.setOnClickListener(R.id.item_list_retry, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 重新加载图片
				ImagePipeline pipeline = Fresco.getImagePipeline();

				pipeline.evictFromCache(Uri.parse(item.getUrl()));
				image.setImageURI(item.getUrl());
			}
		});

	}

	/**
	 * generate the Progress into the Simpledraweeview, the first,you should new
	 * the RectangleProgress.Builder, the seconnd,you can set more properties to
	 * the Builder, Finally,you should use build() and inject to DraweeView
	 * 
	 * @param item
	 *            Javabean
	 * @param image
	 *            the shower
	 */
	private void generateProgress(RectLoadInfo item, SimpleDraweeView image) {
		// First.new builder of the CircleProgress
		RectangleProgress.Builder builder = new RectangleProgress.Builder();
		// Second, set Properties
		if (item.getBottomColor() != 0) {
			builder.setRecBottomColor(item.getBottomColor());
		}
		if (item.getProgress_image() != null) {
			builder.setRectProgressImage(item.getProgress_image());
		}
		if (item.getProgressColor() != 0) {
			builder.setRecProgressColor(item.getProgressColor());
		}
		if (item.getPosition() != 0) {
			builder.setPosition(item.getPosition());
		}
		// Builder will generate the Rectangle Progress,And you should inject
		// your simpledraweeview
		builder.build().inject(image);
		// ok
	}
}
