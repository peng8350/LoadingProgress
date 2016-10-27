package com.jpeng.progress;

import android.graphics.*;
import com.jpeng.progress.base.BaseBuilder;
import com.jpeng.progress.base.BaseProgress;

import android.support.annotation.ColorInt;

/**
 * Created by peng on 16-10-18.
 */
public class RectangleProgress extends BaseProgress {
	// Position
	public static final int	CENTER				= 0;

	public static final int	TOP					= 1;

	public static final int	BOTTOM				= 2;

	private Paint			mRecPaint;

	private int				mRecHeight			= 3;

	private int				mRecBottomColor		= 0xaa59c8cc;

	private int				mRecProgressColor	= 0xff59c8cc;

	private int            mBottomWidth = 2;

	private Bitmap			mBitmap;

	private int				mPosition;

	@Override
	public void initProperty() {
		super.initProperty();
		mRecPaint = new Paint();
		mRecPaint.setAntiAlias(true);
	}

	@Override
	public void DrawOther(Canvas canvas) {
		Rect bound = getBounds();
		drawBottom(canvas, bound);
		drawProgress(canvas, bound);
	}

	/*
	 * draw the progress rectangle
	 */
	private void drawProgress(Canvas canvas, Rect rect) {
		mRecPaint.setColor(mRecProgressColor);
		canvas.drawRoundRect(new RectF(rect.left + rect.width() / 10, rect.centerY() - mRecHeight,
				(float) ((rect.width() - rect.width() / 5) * (double) ((double) mProgress / (double) mMaxValue))
						+ rect.width() / 10,
				rect.centerY() + mRecHeight),10,10, mRecPaint);
	}

	/*
	 * draw the bottom rectangle
	 */
	private void drawBottom(Canvas canvas, Rect rect) {
		mRecPaint.setColor(mRecBottomColor);
		canvas.drawRect(rect.left + rect.width() / 10, rect.centerY() - mRecHeight , rect.right - rect.width() / 10,
				rect.centerY() + mRecHeight , mRecPaint);
	}

	/*
	 * draw text from Super
	 */
	@Override
	public void DrawText(Canvas canvas) {
		Rect size = getBounds();
		if (mBitmap == null) {

			Paint.FontMetricsInt fontMetrics = getmTextPaint().getFontMetricsInt();
			int baseline = size.top + (size.bottom - size.top - fontMetrics.bottom + fontMetrics.top) / 2
					- fontMetrics.top;
			canvas.drawText((int) ((double) ((double) mProgress / (double) mMaxValue) * 100) + "%",
					(float) ((size.width() - size.width() / 5) * (double) ((double) mProgress / (double) mMaxValue))
							+ size.width() / 10 + 15,
					mPosition == CENTER ? baseline
							: mPosition == BOTTOM ? baseline - fontMetrics.top : baseline + fontMetrics.top,
					getmTextPaint());
		} else {
			canvas.drawBitmap(mBitmap,
					(float) ((size.width() - size.width() / 5) * (double) ((double) mProgress / (double) mMaxValue))
							+ size.width() / 10,
					mPosition == CENTER ? size.centerY() - mBitmap.getHeight() / 2
							: mPosition == TOP ? size.centerY() - mBitmap.getHeight() : size.centerY() + 10,
					mRecPaint);
		}
	}

	public static class Builder extends BaseBuilder<RectangleProgress, Builder> {

		public Builder() {
			mProgress = new RectangleProgress();
		}

		public Builder setRecBottomColor(@ColorInt int color) {
			mProgress.mRecBottomColor = color;
			return this;
		}

		public Builder setRecProgressColor(@ColorInt int color) {
			mProgress.mRecProgressColor = color;
			return this;
		}

		public Builder setRecHeight(int height) {
			mProgress.mRecHeight = height;
			return this;
		}

		public Builder setRectProgressImage(Bitmap bitmap) {
			mProgress.mBitmap = bitmap;
			return this;
		}

		public Builder setPosition(int position) {
			mProgress.mPosition = position;
			return this;
		}

	}

}
