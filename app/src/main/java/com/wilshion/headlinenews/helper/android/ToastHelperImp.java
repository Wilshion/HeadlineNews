package com.wilshion.headlinenews.helper.android;


import com.wilshion.utillib.util.ToastUtils;

/**
 * Created by Wilshion on 2017/8/6 18:20.
 * [description : ]
 * [version : 1.0]
 */
public class ToastHelperImp implements IToastHelper {
    @Override
    public void showToastShort(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void showToastLong(String msg) {
        ToastUtils.showLong(msg);
    }

    @Override
    public void showToastDuration(String msg, int duration) {
        ToastUtils.showDuration(msg, duration);
    }
}
