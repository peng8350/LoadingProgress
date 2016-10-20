package com.jpeng.demo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;
import com.jpeng.demo.R;
import com.jpeng.demo.bean.DifferentInfo;
import com.jpeng.progress.CircleProgress;
import com.jpeng.progress.glide.ProgressModelLoader;
import com.jpeng.progress.picasso.SquareUtils;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.bitmap.callback.DefaultBitmapLoadCallBack;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import org.xutils.common.Callback;

import java.util.List;

import static org.xutils.x.image;

/**
 * Created by peng on 16-10-20.
 */
public class DifferentAdapter extends QuickAdapter<DifferentInfo> {
	private Handler handler = new Handler();
	public DifferentAdapter(Context context, int layoutResId, List<DifferentInfo> data) {
		super(context, layoutResId, data);
		handler = new Handler();
	}

	@Override
	protected void convert(BaseAdapterHelper helper, final DifferentInfo item) {
		helper.setText(R.id.item_list_title, item.getName());
		helper.setText(R.id.item_list_intro, item.getIntro());
		final ImageView image = helper.getView(R.id.item_list_image);
		final CircleProgress progress = generateProgress(image);

		progress.inject(image);
		loadImage(item, progress, image);
		helper.setVisible(R.id.item_list_retry,false);
	}

	/*
	 * change the progress
	 */
	private void loadImage(DifferentInfo info, final CircleProgress progress, final ImageView imageView) {
		if (info.getType() == 1) {
			DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(R.mipmap.loading2)
					.cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565)
					.displayer(new RoundedBitmapDisplayer(20)).build();

			ImageLoader.getInstance().displayImage(info.getUrl(), imageView, options, new ImageLoadingListener() {
				@Override
				public void onLoadingStarted(String s, View view) {

				}

				@Override
				public void onLoadingFailed(String s, View view, FailReason failReason) {

				}

				@Override
				public void onLoadingComplete(String s, View view, Bitmap bitmap) {
				}

				@Override
				public void onLoadingCancelled(String s, View view) {

				}
			}, new ImageLoadingProgressListener() {
				@Override
				public void onProgressUpdate(String s, View view, int i, int i1) {
					progress.setLevel(i);
                    progress.setMaxValue(i1);
				}
			});
		} else if (info.getType() == 2) {

			SquareUtils.getPicasso(context, new SquareUtils.ProgressListener() {

				@Override
				public void update(final long current, final long total) {
					handler.post(new Runnable() {
						@Override
						public void run() {
							progress.setMaxValue(total);
							progress.setLevel((int) current);
						}
					});

				}
			}).load(info.getUrl()).config(Bitmap.Config.ARGB_4444).placeholder(R.mipmap.loading2).into(imageView);
		}
		else if (info.getType() == 3) {
			BitmapUtils Utils = new BitmapUtils(context);
			Utils.display(imageView, info.getUrl(),new DefaultBitmapLoadCallBack<ImageView>(){


                @Override
                public void onLoadCompleted(ImageView container, String uri, Bitmap bitmap, BitmapDisplayConfig config, BitmapLoadFrom from) {

                    imageView.setImageBitmap(bitmap);
                }

                @Override
                public void onLoading(ImageView container, String uri, BitmapDisplayConfig config, final long total, final long current) {

//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            progress.setLevel((int) current);
//                            progress.setMaxValue(total);
//                        }
//                    });
                }
            });
		} else if (info.getType() == 4) {
			Glide.with(context).using(new ProgressModelLoader(new Handler() {
				@Override
				public void handleMessage(Message msg) {
					progress.setLevel(msg.arg1);
                    progress.setMaxValue(msg.arg2);
				}
			})).load(info.getUrl()).into(imageView);
		} else {
			image().bind(imageView, info.getUrl(), new Callback.CommonCallback<Drawable>() {
				@Override
				public void onSuccess(Drawable result) {

				}

				@Override
				public void onError(Throwable ex, boolean isOnCallback) {

				}

				@Override
				public void onCancelled(CancelledException cex) {

				}

				@Override
				public void onFinished() {

				}
			});
		}
	}

	/**
	 * generate the Progress into the Simpledraweeview, the first,you should new
	 * the RectangleProgress.Builder, the seconnd,you can set more properties to
	 * the Builder, Finally,you should use build() and inject to DraweeView
	 *
	 *
	 * @param image
	 *            the shower
	 */
	private CircleProgress generateProgress(ImageView image) {
		CircleProgress.Builder builder = new CircleProgress.Builder();
		return (CircleProgress) builder.build();
	}
}
