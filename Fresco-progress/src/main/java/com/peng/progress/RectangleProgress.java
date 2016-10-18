package com.peng.progress;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import com.peng.progress.base.BaseBuilder;
import com.peng.progress.base.BaseProgress;

/**
 * Created by peng on 16-10-18.
 */
public class RectangleProgress extends BaseProgress {

    private Paint mRecPaint;
    private int mRecHeight = 5;
    private int mRecBottomColor = 0xaa59c8cc;
    private int mRecProgressColor = 0xff59c8cc;


    @Override
    public void initProperty() {
        super.initProperty();
        mRecPaint = new Paint();
    }

    @Override
    public void DrawOther(Canvas canvas) {
        Rect bound = getBounds();
        drawBottom(canvas, bound);
        drawProgress(canvas, bound);
    }

    /*
        draw the progress rectangle
     */
    private void drawProgress(Canvas canvas, Rect rect) {
        if (mProgress < 100) return;
        mRecPaint.setColor(mRecProgressColor);
        canvas.drawRect(rect.left + rect.width() / 10, rect.centerY() - mRecHeight, (float) ((rect.width() - rect.width() / 5) * (double) ((double) mProgress / (double) MAX_VALUE)) + rect.width() / 10, rect.centerY() + mRecHeight, mRecPaint);
    }

    /*
        draw the bottom rectangle
     */
    private void drawBottom(Canvas canvas, Rect rect) {
        mRecPaint.setColor(mRecBottomColor);
        canvas.drawRect(rect.left + rect.width() / 10, rect.centerY() - mRecHeight / 2, rect.right - rect.width() / 10, rect.centerY() + mRecHeight / 2,mRecPaint);
    }

    /*
        draw text from Super
     */

    @Override
    public void DrawText(Canvas canvas) {
        Rect size = getBounds();
        Paint.FontMetricsInt fontMetrics = getmTextPaint().getFontMetricsInt();
        int baseline = size.top + (size.bottom - size.top - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        canvas.drawText(mProgress / 100 + "%", (float) ((size.width() - size.width() / 5) * (double) ((double) mProgress / (double) MAX_VALUE)) + size.width() / 10 + 15, baseline, getmTextPaint());
    }

    public static class Builder extends BaseBuilder<RectangleProgress, RectangleProgress.Builder> {

        public Builder() {
            mProgress = new RectangleProgress();
        }

        public Builder setRecBottomColor(@ColorInt int color) {
            mProgress.mRecBottomColor = color;
            return  this;
        }

        public Builder setRecProgressColor(@ColorInt int color) {
            mProgress.mRecProgressColor = color;
            return this;
        }

        public Builder setRecHeight(int height) {
            mProgress.mRecHeight = height;
            return this;
        }

    }

}
