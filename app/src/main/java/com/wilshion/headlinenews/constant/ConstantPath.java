package com.wilshion.headlinenews.constant;

import android.os.Environment;

import com.wilshion.headlinenews.HeadlineNewsApplication;

import java.io.File;

/**
 * Created by Wilshion on 2017/8/7 14:49.
 * [description : 路径 常量]
 * [version : 1.0]
 */
public class ConstantPath {
    public static final String PATH_DATA = HeadlineNewsApplication.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
    public static final String PATH_CACHE = PATH_DATA + "/NetCache";
    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "wilshion" + File.separator + "HeadlineNews";
}
