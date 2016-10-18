package com.peng.progress;

import android.graphics.*;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import com.peng.progress.base.BaseBuilder;
import com.peng.progress.base.BaseProgress;

/**
 * Created by peng on 16-10-18.
 * The CircleProgress class
 * extends BaseProgress
 */
public class CircleProgress extends BaseProgress {

    //The Paint of the Ring
    private Paint mRingPaint;
    //The Size of the Ring
    private float mRingWidth;
    //The progress Color for the Ring
    private int mRingProgressColor;
    //The bottom color for the Ring
    private int mRingBottomColor;
    //the radius of the Ring
    private int mRingRadius;




    /*
        initCircleProgress
     */

    @Override
    public void initProperty() {
        super.initProperty();
        //init Mringproerty
        mRingProgressColor= 0xaa59c8cc;
        mRingBottomColor =  0x80000000;
        mRingPaint  = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRingPaint.setStyle(Paint.Style.STROKE);
        mRingPaint.setStrokeWidth(10);
        mRingWidth=4;
        mRingRadius=100;

    }

    @Override
    public void DrawOther(Canvas canvas) {
        drawCircle(canvas);
        drawArc(canvas);
    }

    /*
        draw the circle background
     */
    private void drawCircle(Canvas canvas) {
        mRingPaint.setColor(mRingBottomColor);
        mRingPaint.setStrokeWidth((float)(mRingWidth/2));
        Rect bounds = getBounds();

        int xpos = bounds.left + bounds.width() / 2;
        int ypos = bounds.bottom - bounds.height() / 2;
        canvas.drawCircle(xpos, ypos, mRingRadius, mRingPaint);
    }
    /*
    draw the arc for the progress
     */
    private void drawArc(Canvas canvas) {
        mRingPaint.setColor(mRingProgressColor);
        mRingPaint.setStrokeWidth(mRingWidth);
        Rect bounds = getBounds();
        int xpos = bounds.left + bounds.width() / 2;
        int ypos = bounds.bottom - bounds.height() / 2;
        RectF rectF = new RectF(xpos - mRingRadius, ypos - mRingRadius, xpos + mRingRadius,
                ypos + mRingRadius);
        float degree = (float) mProgress / (float) MAX_VALUE * 360;
        canvas.drawArc(rectF, 270, degree, false, mRingPaint);
    }



    public static class Builder extends BaseBuilder<CircleProgress,Builder> {
        public Builder() {
            mProgress = new CircleProgress();
        }

        public Builder setRingWidth(float width){
            mProgress.mRingWidth = width;
            return this;
        }

        public Builder setRingRadius(int Radius){
            mProgress.mRingRadius = Radius;
            return this;
        }

        public Builder setRingProgressColor(@ColorInt int Color){
            mProgress.mRingProgressColor = Color;
            return this;
        }
        public Builder setRingBottomColor(@ColorInt int Color){
            mProgress.mRingBottomColor = Color;
            return this;
        }
    }


    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }

    @Override
    public int getOpacity() {
        return 0;
    }

}
