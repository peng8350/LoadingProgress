package com.jpeng.progress;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import com.jpeng.progress.base.BaseBuilder;
import com.jpeng.progress.base.BaseProgress;

/**
 * Created by peng on 16-10-23.
 */
public class RotateProgress extends BaseProgress {
    //rotate right
    public static final int RIGHT = 0;
    //rotate left
    public static final int LEFT = 1;

    //the image of user
    private Bitmap mImage;
    //the roatate speed;
    private int mSpeed=3;
    //the direction of rotate
    private int mDirection = 0;

    private int mAngle=0;


    /*
    * if don't
    * update ,the rotate is not useful
     */
    public int setmAngle(int mAngle) {
        this.mAngle = mAngle;
        invalidateSelf();
        return mAngle;
    }

    @Override
    public void DrawOther(Canvas canvas) {
        RotateBitmap(canvas);
    }

    private void RotateBitmap(Canvas canvas) {

        Rect bounds = getBounds();
        int xpos = bounds.left + bounds.width() / 2;
        int ypos = bounds.bottom - bounds.height() / 2;

        Matrix matrix = new Matrix();
        // 向左旋转45度，参数为正则向右旋转
        matrix.postRotate(mDirection==LEFT?setmAngle(mAngle-mSpeed):setmAngle(mAngle+mSpeed));
        Bitmap rteBitmap = Bitmap.createBitmap(mImage,0,0,mImage.getWidth(),mImage.getHeight(),matrix,true);

        canvas.drawBitmap(rteBitmap,xpos-rteBitmap.getWidth()/2,ypos-rteBitmap.getHeight()/2,null);
    }

    public static class Builder extends BaseBuilder<RotateProgress,RotateProgress.Builder>{

        public Builder(@NonNull  Bitmap image){

            mProgress = new RotateProgress();

            mProgress.mImage = image;
        }

        public Builder setSpeed(int speed){
            mProgress.mSpeed = speed;

            return this;
        }

        public Builder setDirection(int direction){
            mProgress.mDirection = direction;
            return this;
        }
    }
}
