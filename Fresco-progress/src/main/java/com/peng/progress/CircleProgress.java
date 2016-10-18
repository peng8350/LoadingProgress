package com.peng.progress;

import android.graphics.*;
import android.support.annotation.Nullable;
import com.peng.progress.base.BaseBuilder;
import com.peng.progress.base.BaseProgress;

/**
 * Created by peng on 16-10-18.
 */
public class CircleProgress extends BaseProgress {


    //园环的画笔
    private Paint mRingPaint;
    //园环的大小
    private float mRingWidth;
    //园环加载中进度颜色
    private int mRingProgressColor;
    //园环加载的底部颜色
    private int mRingBottomColor;
    //园环的半径
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
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }

    @Override
    public int getOpacity() {
        return 0;
    }

    @Override
    public void DrawOther(Canvas canvas) {
        drawCircle(canvas);
        drawArc(canvas);
    }

    private void drawCircle(Canvas canvas) {
        mRingPaint.setColor(mRingBottomColor);
        Rect bounds = getBounds();
        int xpos = bounds.left + bounds.width() / 2;
        int ypos = bounds.bottom - bounds.height() / 2;
        canvas.drawCircle(xpos, ypos, mRingRadius, mRingPaint);
    }
    /*
    draw the circle
     */
    private void drawArc(Canvas canvas) {
        mRingPaint.setColor(mRingProgressColor);
        Rect bounds = getBounds();
        int xpos = bounds.left + bounds.width() / 2;
        int ypos = bounds.bottom - bounds.height() / 2;
        RectF rectF = new RectF(xpos - mRingRadius, ypos - mRingRadius, xpos + mRingRadius,
                ypos + mRingRadius);
        float degree = (float) mProgress / (float) MAX_VALUE * 360;
        canvas.drawArc(rectF, 270, degree, false, mRingPaint);
    }

    private void setmRingProgressColor(int mRingColor) {
        this.mRingProgressColor = mRingColor;
    }

    private void setmRingRadius(int mRingRadius) {
        this.mRingRadius = mRingRadius;
    }

    private void setmRingWidth(float mRingWidth) {
        this.mRingWidth = mRingWidth;
        mRingPaint.setStrokeWidth(mRingWidth);
    }

    public void setmRingBottomColor(int mRingBottomColor) {
        this.mRingBottomColor = mRingBottomColor;
    }

    public static class Builder extends BaseBuilder<CircleProgress,Builder> {
        public Builder() {
            mProgress = new CircleProgress();
        }

        public Builder setRingWidth(float width){
            mProgress.setmRingWidth(width);
            return this;
        }

        public Builder setRingRadius(int Radius){
            mProgress.setmRingRadius(Radius);
            return this;
        }

        public Builder setRingProgressColor(int Color){
            mProgress.setmRingProgressColor(Color);
            return this;
        }
        public Builder setRingBottomColor(int Color){
            mProgress.setmRingBottomColor(Color);
            return this;
        }
    }



}
