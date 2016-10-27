package com.jpeng.progress;

import android.content.Context;
import android.graphics.*;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import com.jpeng.progress.base.BaseBuilder;
import com.jpeng.progress.base.BaseProgress;
import com.jpeng.progress.enums.CircleStyle;
import com.jpeng.progress.enums.GradientType;

/**
 * Created by peng on 16-10-18. The CircleProgress class extends BaseProgress
 */
public class CircleProgress extends BaseProgress {

	// The Paint of the Ring
	private Paint		mCirclePaint;
	// The Size of the Ring
	private float		mCircleWidth;
	// The progress Color for the Ring
	private int			mCircleProgressColor;
	// The bottom color for the Ring
	private int			mCircleBottomColor;
	//THe bottom circle width
	private int mCircleBottomWidth;
	// the radius of the Ring
	private int			mCircleRadius;
	// Padding of line padding int the fan style
	private int mFanPadding;
	// The Style of the Circle
	private CircleStyle mCircleStyle	= CircleStyle.RING;
	// The linear color
	private int[]		mGradientColor;
	//The Gradient Type
	private GradientType mGradientType=GradientType.LINEAR;

	/*
	 * initCircleProgress
	 */
	@Override
	public void initProperty() {
		super.initProperty();
		// init mCircleproerty

		mCirclePaint = new Paint();

		mCirclePaint.setAntiAlias(true);
		mCirclePaint.setStrokeWidth(10);
		mCirclePaint.setStrokeCap(Paint.Cap.ROUND);

		mCircleProgressColor = 0xaa59c8cc;
		mCircleBottomColor = 0xdddddddd;
		mCircleWidth = 8;
		mCircleRadius = 50;
		mFanPadding=5;
		mCircleBottomWidth=2;
	}

	@Override
	public void DrawOther(Canvas canvas) {
		drawCircle(canvas);
		drawArc(canvas);
	}

	/*
	 * draw the circle background
	 */
	private void drawCircle(Canvas canvas) {

		Rect bounds = getBounds();
		int xpos = bounds.left + bounds.width() / 2;
		int ypos = bounds.bottom - bounds.height() / 2;

		mCirclePaint.setColor(mCircleBottomColor);
		mCirclePaint.setStrokeWidth(mCircleStyle==CircleStyle.RING?mCircleWidth:mCircleBottomWidth);
		mCirclePaint.setStyle(Paint.Style.STROKE);
		mCirclePaint.setShader(null);

		canvas.drawCircle(xpos, ypos, mCircleStyle == CircleStyle.RING ? mCircleRadius : mCircleRadius + mFanPadding,
				mCirclePaint);

	}

	/*
	 * draw the arc for the progress
	 */
	private void drawArc(Canvas canvas) {
		mCirclePaint.setStyle(mCircleStyle==CircleStyle.RING? Paint.Style.STROKE: Paint.Style.FILL);
		Rect bounds = getBounds();
		int xpos = bounds.left + bounds.width() / 2;
		int ypos = bounds.bottom - bounds.height() / 2;
		RectF rectF = new RectF(xpos - mCircleRadius, ypos - mCircleRadius, xpos + mCircleRadius, ypos + mCircleRadius);
		float degree = (float) mProgress / (float) mMaxValue * 360;

		mCirclePaint.setStrokeWidth(mCircleWidth);
		if (mGradientColor != null) {
			if(mGradientType==GradientType.LINEAR)
			mCirclePaint.setShader(new LinearGradient(bounds.centerX(), bounds.centerY() - mCircleRadius,
					bounds.centerX(), bounds.centerY() + mCircleRadius, mGradientColor, null, Shader.TileMode.MIRROR));
			else{
				mCirclePaint.setShader(new SweepGradient(bounds.centerX(),bounds.centerY(),mGradientColor,null));
			}

			mCirclePaint.setColor(0xffffffff);
		} else {
			mCirclePaint.setColor(mCircleProgressColor);
		}

		canvas.drawArc(rectF, 270, degree, mCircleStyle == CircleStyle.FAN, mCirclePaint);
	}

	public static class Builder extends BaseBuilder<CircleProgress, Builder> {
		public Builder() {
			mProgress = new CircleProgress();
		}

		/*
		* set The Circle line width
		 */
		public Builder setCircleWidth(float width) {
			mProgress.mCircleWidth = width;
			return this;
		}

		/*
		* set the Circle radius
		 */
		public Builder setCircleRadius(int Radius) {
			mProgress.mCircleRadius = Radius;

			return this;
		}

		/**
		 *
		 * set the GradientType：SWEEP,LINEAR
		 * @param type
		 * @return Builder
		 */
		public Builder setGradientType(GradientType type){
			mProgress.mGradientType =type;
			return this;
		}

		/*
		* set the Progress Circle Color
		*/
		public Builder setProgressColor(@ColorInt int Color) {
			mProgress.mCircleProgressColor = Color;

			return this;
		}

		/*
    	* set the Bottom Circle Color
     	*/
		public Builder setBottomColor(@ColorInt int Color) {
			mProgress.mCircleBottomColor = Color;
			return this;
		}
		/*
    	* set the Bottom Circle Color with resource
     	*/
		public Builder setBottomColorRes(@ColorRes int ColorRes, Context context) {
			mProgress.mCircleBottomColor = context.getResources().getColor(ColorRes);
			return this;
		}
		/*
    	* set the Progress Circle Color
     	*/

		public Builder setProgressColorRes(@ColorRes int ColorRes, Context context) {
			mProgress.mCircleProgressColor = context.getResources().getColor(ColorRes);
			return this;
		}

		/*
		*set the Progress CircleStyle
		 */
		public Builder setStyle(CircleStyle style) {
			mProgress.mCircleStyle = style;
			return this;
		}

		/*
		* set the Gradient Colors
		 */
		public Builder setGradientColor(int[] colors) {
			mProgress.mGradientColor = colors;
			return this;
		}

		/**
		 *
		 * set the fan style padding
		 * @param padding
		 * @return
		 */
		public Builder setFanPadding(int padding){
			mProgress.mFanPadding = padding;
			return this;
		}

		/**
		 *
		 * set the Bottom Stroke Width
		 * @return
		 */
		public Builder setBottomWidth(int width){
			mProgress.mCircleBottomWidth = width;
			return this;
		}

	}

}
