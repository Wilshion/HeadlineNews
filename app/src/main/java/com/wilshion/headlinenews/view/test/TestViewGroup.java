package com.wilshion.headlinenews.view.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Wilshion on 2017/9/8 15:34.
 * [description : ]
 * [version : 1.0]
 */
public class TestViewGroup extends ViewGroup {
    public TestViewGroup(Context context) {
        super(context);
    }

    public TestViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int centerY = getMeasuredHeight() / 2;

        int childCount = getChildCount();

        int childTotalWidth = 0;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE)
                continue;
            childTotalWidth += child.getMeasuredWidth();
        }
        /** child 之间的间隔 */
        int marginX = (getMeasuredWidth() - childTotalWidth) / (childCount + 1);

        int mLeft = marginX;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE)
                continue;
            int top = centerY - child.getMeasuredHeight() / 2;
            child.layout(mLeft, top, child.getMeasuredWidth() + mLeft, top + child.getMeasuredHeight());

            mLeft = mLeft + child.getMeasuredWidth() + marginX;
        }
    }
}
