package com.jpeng.progress.base;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peng on 16-10-18. The extends class of the ProgressBar
 */
public abstract class BaseProgress extends Drawable {
	// init Max value is 10000
    protected long mMaxValue=10000;
	// The size of text
	private int				mTextSize;
	// The color of text
	private int				mTextColor;
	// the X offset of text
	private int             mTextXOffset;
	private int             mTextYOffset;
	//The text typeface
	private Typeface mTypeface;
	// THe visiable of text
	private boolean			mTextShow;
	// The paint of text
	private Paint			mTextPaint;
	// The Progress Value
	protected long			mProgress;
	// target ImageVIew
	private ImageView		mTarget;
	//Custom String
	private String mCustomStr;


	/**
	 * Creates a new layer drawable with the list of specified layers.
	 *
	 */
	public BaseProgress() {
		initProperty();
	}

	/**
	 * Creates a new fade drawable. The first layer is displayed with full
	 * opacity whereas all other layers are invisible.
	 */

	/*
	 * init the default Property
	 */
	public void initProperty() {
		mTextPaint = new Paint();
		mTextSize = 20;
		mTextColor = Color.WHITE;
		mTextShow = true;
		mTextPaint = new Paint();
		mTextPaint.setAntiAlias(true);
		mTextPaint.setColor(mTextColor);
		mTextPaint.setTextSize(mTextSize);
		mTextPaint.setTextAlign(Paint.Align.CENTER);
	}

	@Override
	public void draw(Canvas canvas) {
		// if progress = 0 ,not show
		 if (mProgress == 0||(int)((double)((double)mProgress/(double)mMaxValue)*100)==100) {
			 return;
		 }
		DrawOther(canvas);
		if (mTextShow) {
			DrawText(canvas);
		}
	}

	/**
	 * draw text on the center of the canvas
	 *
	 * @param canvas
	 *            画布
	 */
	public void DrawText(Canvas canvas) {
		Rect size = getBounds();
		Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
		int baseline = size.top + (size.bottom - size.top - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
		canvas.drawText(mCustomStr==null?(int)((double)((double)mProgress/(double)mMaxValue)*100) + "%":mCustomStr, size.centerX()+mTextXOffset, baseline+mTextYOffset, mTextPaint);
	}


	/**
	 * The method will add this progress to the imageview And it will in the top
	 * of the imageview
	 */
	public void AddToImageView(boolean haveDrawable) {
		List<Drawable> layer_arrays = new ArrayList<>();
		if (haveDrawable) {
			Drawable drawable = mTarget.getDrawable();
			layer_arrays.add(drawable);
		}
		layer_arrays.add(this);
		LayerDrawable levelDrawable = new LayerDrawable(layer_arrays.toArray(new Drawable[layer_arrays.size()]));
		mTarget.setImageDrawable(levelDrawable);
	}

	@Override
	protected boolean onLevelChange(int level) {
		if (mTarget != null) {
			if (mTarget.getDrawable() == null) {
				AddToImageView(false);
			} else if (mProgress < mMaxValue) {
				if (!mTarget.getDrawable().getClass().equals(LayerDrawable.class))
					AddToImageView(true);
			}
		}
		long origin = mProgress;
		mProgress = level;
		if (mProgress != 0 && origin != mProgress) {
			invalidateSelf();
			return true;
		} else {
			return false;
		}
	}


	/*
 	* Clear progress
 	* bacause listview cache
	*/
	private void ClearProgress() {
		if (mTarget==null||mTarget.getDrawable()==null||!mTarget.getDrawable().getClass().equals(LayerDrawable.class))return;
			LayerDrawable levelDrawable = (LayerDrawable) mTarget.getDrawable();
				if (levelDrawable.getNumberOfLayers()==2){
					if(levelDrawable.getDrawable(1).getClass().isAssignableFrom(BaseProgress.class)&&!levelDrawable.getDrawable(1).equals(this)) {
						//let the progress not show
						((BaseProgress) levelDrawable.getDrawable(1)).setLevel(0);
						// let the origin progress's target imageview point to null
						((BaseProgress) levelDrawable.getDrawable(1)).inject(null);
						//reinject new target
						mTarget.setImageDrawable(levelDrawable.getDrawable(0));
					}
				}
	}

	/*
	注入Simpledraweeview
	 */
	public void injectFresco(@NonNull SimpleDraweeView DraweeView){
		GenericDraweeHierarchy hierarchy = DraweeView.getHierarchy();
		hierarchy.setProgressBarImage(this);
	}

	/*
	 * inject into ImageView
	 */
	public void inject(ImageView imageView) {
		mTarget = imageView;
		ClearProgress();
	}

	public void setTextSize(int mTextSize) {
		this.mTextSize = mTextSize;
		mTextPaint.setTextSize(mTextSize);
	}

	public void setTextColor(int mTextColor) {
		mTextPaint.setColor(mTextColor);
		this.mTextColor = mTextColor;
	}

	public void setTextColorRes(@ColorRes int ColorRes, Context context) {
		mTextPaint.setColor(context.getResources().getColor(ColorRes));
	}

	public void setTextShow(boolean mTextShow) {
		this.mTextShow = mTextShow;
	}

	public void setTypeface(Typeface mTypeface) {
		this.mTypeface = mTypeface;
		mTextPaint.setTypeface(mTypeface);
	}

	public void setCustomText(String text){
		mCustomStr = text;
	}

	public void setTextXOffset(int offset){
		mTextXOffset = offset;
	}

	public void setTextYOffset(int offset){
		mTextYOffset= offset;
	}

	public void setMaxValue(long value){
        this.mMaxValue = value;
    }

	@Override
	public void setAlpha(int alpha) {

	}

	@Override
	public void setColorFilter(@NonNull ColorFilter colorFilter) {

	}

	@Override
	public int getOpacity() {
		return 0;
	}

	public Paint getmTextPaint() {
		return mTextPaint;
	}


	public abstract void DrawOther(Canvas canvas);


}
