package com.wilshion.headlinenews.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.wilshion.utillib.util.LogUtils;

/**
 * Created by Wilshion on 2017/9/8 10:08.
 * [description : ]
 * [version : 1.0]
 */
public class UIZoomHeaderView extends ScrollView {
    private View mHeaderView;
    private int mHeaderViewWidth, mHeaderViewHeight;
    private float mScrollZoomFactor = 0.4f;
    private float mScrollMaxTimes = 2.0f;

    private boolean mIsPulling;
    private int mLastY;
    //    回弹时间系数，系数越小，回弹越快
    private float mReplyRatio = 0.5f;

    public UIZoomHeaderView(Context context) {
        this(context, null);
    }

    public UIZoomHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UIZoomHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setOverScrollMode(OVER_SCROLL_NEVER);
        if (mHeaderView == null) {
            View childParent = getChildAt(0);
            if (childParent instanceof ViewGroup) {
                mHeaderView = ((ViewGroup) childParent).getChildAt(0);
            }
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
//        LogUtils.e("onWindowFocusChanged " + mHeaderView.getMeasuredWidth());
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
//        LogUtils.e("onFocusChanged " + mHeaderView.getMeasuredWidth());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        LogUtils.e("onSizeChanged " + mHeaderView.getMeasuredWidth());
        mHeaderViewWidth = mHeaderView.getMeasuredWidth();
        mHeaderViewHeight = mHeaderView.getMeasuredHeight();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mHeaderView == null)
            return super.onTouchEvent(ev);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (!mIsPulling) {
                    if (getScrollY() == 0) {
                        mLastY = (int) ev.getY();
                    } else break;
                }
                int distance = (int) (ev.getY() - mLastY);
                if (distance < 0)
                    return super.onTouchEvent(ev);

                mIsPulling = true;
                zoomHeader(distance * mScrollZoomFactor);
                return true;
            case MotionEvent.ACTION_UP:
                mIsPulling = false;
                LogUtils.e(" mHeaderViewWidth =   " + mHeaderViewWidth);
                scaleBack();
                break;
        }
        return super.onTouchEvent(ev);

    }

    private void zoomHeader(float distance) {
        float scaleTimes = (float) ((mHeaderViewWidth + distance) / (mHeaderViewWidth * 1.0));

        if (scaleTimes > mScrollMaxTimes) {

            return;
        }
        ViewGroup.LayoutParams lp = mHeaderView.getLayoutParams();
        lp.width = (int) (mHeaderViewWidth + distance);
        lp.height = (int) (mHeaderViewHeight * ((mHeaderViewWidth + distance) / mHeaderViewWidth));
        ((MarginLayoutParams) lp).setMargins(0, 0, -(lp.width - mHeaderViewWidth) / 2, 0);
        mHeaderView.setLayoutParams(lp);
    }

    private void scaleBack() {
        float distance = mHeaderView.getWidth() - mHeaderViewWidth;
        ValueAnimator animator = ValueAnimator.ofFloat(distance, 0.0f)
                .setDuration((long) (distance * mReplyRatio));
        animator.addUpdateListener(animation -> zoomHeader((Float) animation.getAnimatedValue()));
        animator.start();
    }
}
