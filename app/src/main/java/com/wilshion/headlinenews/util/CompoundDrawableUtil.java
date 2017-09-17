package com.wilshion.headlinenews.util;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.wilshion.utillib.util.LogUtils;
import com.wilshion.utillib.util.ResouceUtil;
import com.wilshion.utillib.util.SizeUtils;

/**
 * Created by Wilshion on 2017/9/14 14:07.
 * [description : ]
 * [version : 1.0]
 */
public class CompoundDrawableUtil {
    /**
     * 修改 textview 的 compoundDrawable
     *
     * @param textView
     * @param where
     * @param resId
     * @return
     * @see TextView#setCompoundDrawables(Drawable, Drawable, Drawable, Drawable)
     */
    public static void changeCompoundDrawable(TextView textView, int where, int resId) {
        if (where < 0 || where > 3)
            return;
        Drawable[] drawables = textView.getCompoundDrawables();
        Drawable exptectedDrawable = ResouceUtil.getDrawable(resId);
        drawables[where] = exptectedDrawable;
        textView.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }


    /**
     * 修改 textview 的drawable size
     * <p>
     *
     * @param textView   textview
     * @param where      上下左右    0-左  1-上  2-右  3- 下
     * @param expectedWh 宽高，单位 dp
     *                   </p>
     * @see TextView#getCompoundDrawables()
     */
    public static void changeCompoundDrawableSize(TextView textView, int where, int expectedWh) {
        if (where < 0 || where > 3)
            return;
        Drawable[] drawables = textView.getCompoundDrawables();
        Drawable drawable = drawables[where];
        if (drawable == null)
            return;
        int wh = SizeUtils.dp2px(expectedWh);
        Rect rect = new Rect(0, 0, wh, wh);
        drawable.setBounds(rect);
        drawables[where] = drawable;
        textView.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }


    public static void changeCompoundDrawablePadding(TextView textView, int expectedPadding) {
        textView.setCompoundDrawablePadding(SizeUtils.dp2px(expectedPadding));
    }

    public static void changeCompoundDrawableWithPadding(TextView textView, int where, int resId, int drawablePadding) {
        if (where < 0 || where > 3)
            return;
        if (resId <= 0) {
            LogUtils.e("changeCompoundDrawableWithPadding  resId = " + resId + " is error.....");
            return;
        }
        Drawable[] drawables = textView.getCompoundDrawables();
        Drawable exptectedDrawable = ResouceUtil.getDrawable(resId);
       
        drawables[where] = exptectedDrawable;
        textView.setCompoundDrawablePadding(SizeUtils.dp2px(drawablePadding));
        textView.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }
}
