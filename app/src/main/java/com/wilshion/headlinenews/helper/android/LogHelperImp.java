package com.wilshion.headlinenews.helper.android;


import com.wilshion.utillib.util.LogUtils;

/**
 * Created by Wilshion on 2017/8/6 18:15.
 * [description : ]
 * [version : 1.0]
 */
public class LogHelperImp implements ILogHelper {
    private String TAG = getClass().getSimpleName();

    public LogHelperImp(String TAG) {
        this.TAG = TAG;
    }

    @Override
    public void logD( Object msg) {
        LogUtils.d(TAG, msg);
    }

    @Override
    public void logI(Object msg) {
        LogUtils.i(TAG, msg);
    }

    @Override
    public void logW( Object msg) {
        LogUtils.w(TAG, msg);
    }

    @Override
    public void logE( Object msg) {
        LogUtils.e(TAG, msg);
    }

    @Override
    public void logA( Object msg) {
        LogUtils.a(TAG, msg);
    }
    
}
