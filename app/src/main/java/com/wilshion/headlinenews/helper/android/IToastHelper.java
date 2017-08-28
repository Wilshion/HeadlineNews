package com.wilshion.headlinenews.helper.android;

/**
 * Created by Wilshion on 2017/8/6 18:19.
 * [description : ]
 * [version : 1.0]
 */
public interface IToastHelper {
    void showToastShort(String msg);

    void showToastLong(String msg);

    void showToastDuration(String msg, int duration);
}
