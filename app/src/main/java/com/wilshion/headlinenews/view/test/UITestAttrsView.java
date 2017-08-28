package com.wilshion.headlinenews.view.test;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.wilshion.headlinenews.R;

/**
 * Created by Wilshion on 2017/8/23 10:10.
 * [description : ]
 * [version : 1.0]
 */
public class UITestAttrsView extends View {
    public UITestAttrsView(Context context) {
        this(context, null);
    }

    public UITestAttrsView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UITestAttrsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs, defStyleAttr);
    }

    private void initAttrs(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {


        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.UITestAttrsView);
        
        Drawable testDrawable = ta.getDrawable(R.styleable.UITestAttrsView_attr_name_1);
        boolean testBool = ta.getBoolean(R.styleable.UITestAttrsView_attr_name_2, false);
        int testColor = ta.getColor(R.styleable.UITestAttrsView_attr_name_3, Color.WHITE);
        int testDimension = ta.getDimensionPixelSize(R.styleable.UITestAttrsView_attr_name_4, 0);
        int testEnum = ta.getInt(R.styleable.UITestAttrsView_attr_name_5, 1);
        int testFlag = ta.getInt(R.styleable.UITestAttrsView_attr_name_6, 1);
        float testFraction = ta.getFraction(R.styleable.UITestAttrsView_attr_name_7, 1, 100, 0.0f);
        float testFloat = ta.getFloat(R.styleable.UITestAttrsView_attr_name_8, 1.0f);
        int testInteger = ta.getInteger(R.styleable.UITestAttrsView_attr_name_9, 1);


/**
 * enum 的获取方式
 *
 @see LinearLayout#setOrientation(int)
 int index = a.getInt(com.android.internal.R.styleable.LinearLayout_orientation, -1);
 if (index >= 0) {
 setOrientation(index);
 }
 */

/**
 * 
 * flag 位或运算
 * 获取方式
 @see View#setScrollBarStyle(int) 
 case R.styleable.View_scrollbars:
 final int scrollbars = a.getInt(attr, SCROLLBARS_NONE);
 if (scrollbars != SCROLLBARS_NONE) {
 viewFlagValues |= scrollbars;
 viewFlagMasks |= SCROLLBARS_MASK;
 initializeScrollbars = true;
 }
 */

    }
}
