package com.jpeng.progress.base;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;

/**
 * Created by peng on 16-10-18. The Father Builder to the Progress
 * The public father class Builder
 */
public abstract class BaseBuilder<T extends BaseProgress, H extends BaseBuilder> {
	// The Progress Instance
	protected T mProgress;

	/*
	* set the Progress Text Color
	 */
	public H setTextColor(int color) {
		mProgress.setTextColor(color);
		return (H) this;
	}
	/*
	* set the Progress Text Size
	 */
	public H setTextSize(int size) {
		mProgress.setTextSize(size);
		return (H) this;
	}

	/*
	* set the visiably of text
	 */
	public H setTextShow(boolean Show) {
		mProgress.setTextShow(Show);
		return (H) this;
	}

	/*
	* The second way to set the text color
	 */
	public H setTextColorRes(@ColorRes int color, @NonNull Context context){
		mProgress.setTextColor(context.getResources().getColor(color));
		return (H) this;
	}

	/*
	* set the max value for the progress
 	*/
	public H setMaxValue(long max){
		mProgress.setMaxValue(max);
		return (H) this;
	}

	/*
	* set the Progress text type
 	*/
	public H setTextType(Typeface type){
		mProgress.setTypeface(type);
		return (H)this;
	}

	/**
	 * set Custom string
	 * @return
	 */
	public H setCustomText(String text){
		mProgress.setCustomText(text);
		return (H) this;
	}

	/**
	 * set the TextXoffset
	 * @return
	 */
	public H setTextXOffset( int Xoffset){
		mProgress.setTextXOffset(Xoffset);
		return (H) this;
	}

	/**
	 * set the TextYoffset
	 * @return
	 */
	public H setTextYOffset( int Xoffset){
		mProgress.setTextYOffset(Xoffset);
		return (H) this;
	}

	/*
	*generate a new object for the Progress
	 */
	public T build() {
		return mProgress;
	}
}
