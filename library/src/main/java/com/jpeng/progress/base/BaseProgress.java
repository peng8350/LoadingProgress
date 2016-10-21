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
	// THe visiable of text
	private boolean			mTextShow;
	// The paint of text
	private Paint			mTextPaint;
	// The Progress Value
	protected long			mProgress;
	// target ImageVIew
	private ImageView		mTarget;

	/**
	 * Creates a new fade drawable. The first layer is displayed with full
	 * opacity whereas all other layers are invisible.
	 */
	public BaseProgress() {
		initProperty();
	}

	/*
	 * init the default Property
	 */
	public void initProperty() {
		mTextPaint = new Paint();
		mTextSize = 20;
		mTextColor = Color.WHITE;
		mTextShow = true;
		mTextPaint = new Paint();
		mTextPaint.setColor(mTextColor);
		mTextPaint.setTextSize(mTextSize);
		mTextPaint.setTextAlign(Paint.Align.CENTER);
	}

	@Override
	public void draw(Canvas canvas) {
		// if progress = 0 ,not show
		 if (mProgress == 0)
		 return;
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
		int x = size.width() / 2;
		int y = size.height() / 2;
		canvas.drawText((int)((double)((double)mProgress/(double)mMaxValue)*100) + "%", size.centerX(), baseline, mTextPaint);
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
		levelDrawable.setFilterBitmap(true);
		mTarget.setImageDrawable(levelDrawable);
	}

	@Override
	protected boolean onLevelChange(int level) {
		if (mTarget != null) {
			if (mTarget.getDrawable() == null) {
				AddToImageView(false);
			} else if (mTarget.getDrawable() != null && !mTarget.getDrawable().getClass().equals(LayerDrawable.class)
					&& mProgress < mMaxValue) {
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

	/**
	 * inject into the Fresco of the Simpledraweeview
	 */
	public void inject(@NonNull SimpleDraweeView draweeView) {
		GenericDraweeHierarchy hierarchy = draweeView.getHierarchy();
		hierarchy.setProgressBarImage(this);
	}

	/*
	 * inject into ImageView
	 */
	public void inject(@NonNull ImageView imageView) {
		mTarget = imageView;
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
