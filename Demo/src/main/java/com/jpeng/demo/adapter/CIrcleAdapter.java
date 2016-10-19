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
import com.jpeng.demo.bean.CircleLoadInfo;
import com.jpeng.progress.CircleProgress;

import java.util.List;

/**
 * Created by peng on 16-10-19.
 */
public class CIrcleAdapter extends QuickAdapter<CircleLoadInfo> {

	public CIrcleAdapter(Context context, int layoutResId, List<CircleLoadInfo> data) {
		super(context, layoutResId, data);
	}

	@Override
	protected void convert(BaseAdapterHelper helper, final CircleLoadInfo item) {
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
				pipeline.evictFromDiskCache(Uri.parse(item.getUrl()));

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
	private void generateProgress(CircleLoadInfo item, SimpleDraweeView image) {
		CircleProgress.Builder builder = new CircleProgress.Builder();
		if (item.getStyle() != null) {
			builder.setStyle(item.getStyle());
		}
		if (item.getGradient() != null) {
			builder.setGradientColor(item.getGradient());
		}
		if (item.getProgressColor() != 0) {
			builder.setProgressColor(item.getProgressColor());
		}
		if (item.getBottomColor() != 0) {
			builder.setBottomColor(item.getBottomColor());
		}
		if (item.getStrokeWidth() != 0) {
			builder.setCircleWidth(item.getStrokeWidth());
		}
		if (item.getRadius() != 0) {
			builder.setCircleRadius(item.getRadius());
		}
		if (item.getTextColor() != 0) {
			builder.setTextColor(item.getTextColor());
		}
		if (item.getTextSize() != 0) {
			builder.setTextSize(item.getTextSize());
		}
		builder.build().inject(image);
	}
}
