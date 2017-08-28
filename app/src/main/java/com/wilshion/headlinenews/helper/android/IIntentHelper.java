package com.wilshion.headlinenews.helper.android;

import android.os.Parcelable;

import com.wilshion.headlinenews.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wilshion on 2017/8/7 11:09.
 * [description : ]
 * [version : 1.0]
 */
public interface IIntentHelper {
    void startActivity(Class<? extends BaseActivity> tartgetAc);
    void startActivity(Class<? extends BaseActivity> tartgetAc,int value);
    void startActivity(Class<? extends BaseActivity> tartgetAc,String value);
    void startActivity(Class<? extends BaseActivity> tartgetAc,List<CharSequence> value);
    void startActivity(Class<? extends BaseActivity> tartgetAc,Parcelable value);
    void startActivity(Class<? extends BaseActivity> tartgetAc, ArrayList<Parcelable> parcelableList);

  
}
