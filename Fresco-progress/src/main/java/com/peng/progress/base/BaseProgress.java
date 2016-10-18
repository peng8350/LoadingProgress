package com.peng.progress.base;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by peng on 16-10-18.
 * The extends class of the ProgressBar
 */
public abstract class BaseProgress extends ProgressBarDrawable {
    //初始化的最大值
    public static final int MAX_VALUE = 10000;
    //进度文字大小
    private int mTextSize;
    //进度文字颜色
    private int mTextColor;
    //是否显示进度文字
    private boolean mTextShow;
    //文字的画笔
    private Paint mTextPaint;
    //进度值
    protected int mProgress;

    public BaseProgress() {
        initProperty();
    }

    /*
    初始化默认属性
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
        //判断是否显示,然后才画文字到中央
        DrawOther(canvas);
        if (mTextShow) {
            DrawText(canvas);
        }
    }

    /**
     * 画文字
     *
     * @param canvas 画布
     */
    public void DrawText(Canvas canvas) {
        Rect size = getBounds();
        Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
        int baseline = size.top + (size.bottom - size.top - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        int x = size.width() / 2;
        int y = size.height() / 2;
        canvas.drawText(mProgress / 100 + "%", size.centerX(), baseline, mTextPaint);
    }


    @Override
    protected boolean onLevelChange(int level) {
        mProgress = level;
        //10000代表加载进度100%的意思
        if (mProgress == 0||mProgress==10000||mProgress==9900) {
            invalidateSelf();
            return true;
        } else {
            return false;
        }
    }

    /**
     * 注入Fresco里面的Simpledraweeview
     *
     *
     */
    public void inject(@Nullable SimpleDraweeView draweeView) {
        GenericDraweeHierarchy hierarchy = draweeView.getHierarchy();
        hierarchy.setProgressBarImage(this);
    }

    public void setTextSize(int mTextSize) {
        this.mTextSize = mTextSize;
        mTextPaint.setTextSize(mTextSize);
    }

    public void setTextColor(int mTextColor) {
        mTextPaint.setColor(mTextColor);
        this.mTextColor = mTextColor;
    }

    public void setTextShow(boolean mTextShow) {

        this.mTextShow = mTextShow;

    }

    public Paint getmTextPaint() {
        return mTextPaint;
    }

    public abstract void DrawOther(Canvas canvas);

}
