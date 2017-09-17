package com.wilshion.headlinenews.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2017/2/7 0007.
 */

public class HeaderZoomLayout extends ScrollView {

    public HeaderZoomLayout(@NonNull Context context) {
        this(context,null);
    }

    public HeaderZoomLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HeaderZoomLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
