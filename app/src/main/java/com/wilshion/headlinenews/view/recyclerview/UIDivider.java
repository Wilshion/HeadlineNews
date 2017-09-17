package com.wilshion.headlinenews.view.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Wilshion on 2017/9/5 09:34.
 * [description : ]
 * [version : 1.0]
 */
public class UIDivider extends RecyclerView.ItemDecoration {
    private Paint mPaint;
    private int mOrientation;
    private int mDividerColor;
    private Drawable mDividerDrawable;
    private int mDividerWidth;
    private int mMarginLeft;
    private int mMarginRight;

    public UIDivider(Context context, int orientation, int dividerColor, Drawable dividerDrawable,
                     int dividerWidth,int marginLeft,int marginRight) {
        if (orientation != OrientationHelper.HORIZONTAL && orientation !=OrientationHelper.VERTICAL){
            throw new IllegalArgumentException("分割线 方向出错");
        }
        if (dividerWidth <0)
            throw new IllegalArgumentException("分割线 尺寸出错");
        mOrientation = orientation;
        mDividerColor = dividerColor;
        mDividerDrawable = dividerDrawable;
        mDividerWidth = dividerWidth;
        mMarginLeft = marginLeft;
        mMarginRight = marginRight;
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mDividerColor);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
       if (mOrientation == OrientationHelper.HORIZONTAL){
           drawHorizontal(c,parent);
       }else {
           drawVertical(c,parent); 
       }
    }

    /**
     * 绘制垂直分割线
     */
    private void drawVertical(Canvas canvas, RecyclerView parent) {
        int left = parent.getPaddingLeft() + mMarginLeft; 
        int right = parent.getMeasuredWidth() - parent.getPaddingRight() - mMarginRight;
        int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + layoutParams.bottomMargin;
            int bottom = top + mDividerWidth;

            if (mDividerDrawable != null) {
                //如果是图片分割线，绘制图片
                mDividerDrawable.setBounds(left, top, right, bottom);
                mDividerDrawable.draw(canvas);
            } else {
                //绘制矩形
                canvas.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }

    /**
     * 绘制横向水平分割线
     */
    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        int top = parent.getPaddingTop();
        int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom();
        int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + layoutParams.rightMargin;
            int right = left + mDividerWidth;

            if (mDividerDrawable != null) {
                //如果是图片分割线，绘制图片
                mDividerDrawable.setBounds(left, top, right, bottom);
                canvas.drawPaint(mPaint);
                mDividerDrawable.draw(canvas);
            } else {
                //绘制矩形
                canvas.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }

 

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == LinearLayoutManager.VERTICAL) {
            outRect.set(0, 0, 0, mDividerWidth);
        } else {
            outRect.set(0, 0, mDividerWidth, 0);
        }
    }

    public void setOrientation(int orientation) {
        mOrientation = orientation;
    }

    public void setDividerColor(int dividerColor) {
        mDividerColor = dividerColor;
    }

    public void setDividerDrawable(Drawable dividerDrawable) {
        mDividerDrawable = dividerDrawable;
    }

    public void setDividerWidth(int dividerWidth) {
        mDividerWidth = dividerWidth;
    }
}
