package com.wilshion.headlinenews.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by Wilshion on 2017/9/17 20:07.
 * [description : ]
 * [version : 1.0]
 */
public class UIScrollView extends ScrollView {
    private OnScrollChangeListener mOnScrollChangeListener;
    /**
     * 当前 scrollview 的内容总高度
     */
    private int mContentSize;
    /**
     * 当前 内容滑动距离
     */
    private int mContentOffset;

    public UIScrollView(Context context) {
        this(context, null);
    }

    public UIScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UIScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        mContentOffset = t;
        if (mOnScrollChangeListener != null)
            mOnScrollChangeListener.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        mContentSize = getChildAt(0).getHeight();
    }

    public int getContentOffset() {
        return mContentOffset;
    }

    public int getContentSize() {
        return mContentSize;
    }

    public void setOnScrollChangeListener(OnScrollChangeListener onScrollChangeListener) {
        mOnScrollChangeListener = onScrollChangeListener;
    }

    public interface OnScrollChangeListener {
        void onScrollChanged(int l, int t, int oldl, int oldt);
    }
}
