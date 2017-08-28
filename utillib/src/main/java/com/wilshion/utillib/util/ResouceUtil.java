package com.wilshion.utillib.util;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.content.ContextCompat;

/**
 * Created by Wilshion on 2017/8/9 18:13.
 * [description : 获取 资源相关的工具类]
 * [version : 1.0]
 */
public class ResouceUtil {

    public static int getColor(int resId) {
        return ContextCompat.getColor(Utils.getContext(), resId);
    }
    
    public static ColorStateList getColorStateList(int resId){
        return ContextCompat.getColorStateList(Utils.getContext(),resId);
    }

    public static Drawable getDrawable(int resId) {
        return ContextCompat.getDrawable(Utils.getContext(), resId);
    }

    public static StateListDrawable getStateListDrawable(int resId) {
        return (StateListDrawable) ContextCompat.getDrawable(Utils.getContext(), resId);
    }
}
