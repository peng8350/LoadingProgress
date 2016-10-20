package com.jpeng.progress.base;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;

/**
 * Created by peng on 16-10-18. The Father Builder to the Progress
 */
public abstract class BaseBuilder<T extends BaseProgress, H extends BaseBuilder> {
	protected T mProgress;

	public H setTextColor(int color) {
		mProgress.setTextColor(color);
		return (H) this;
	}

	public H setTextSize(int size) {
		mProgress.setTextSize(size);
		return (H) this;
	}

	public H setTextShow(boolean Show) {
		mProgress.setTextShow(Show);
		return (H) this;
	}

	public H setTextColorRes(@ColorRes int color, @NonNull Context context){
		mProgress.setTextColor(context.getResources().getColor(color));
		return (H) this;
	}

	public H setMaxValue(long max){
		mProgress.setMaxValue(max);
		return (H) this;
	}

	public BaseProgress build() {
		return mProgress;
	}
}
