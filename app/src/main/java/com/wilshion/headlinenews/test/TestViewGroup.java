package com.wilshion.headlinenews.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * Created by Wilshion on 2017/8/29 21:53.
 * [description : ]
 * [version : 1.0]
 */
public class TestViewGroup extends ViewGroup{

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
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
