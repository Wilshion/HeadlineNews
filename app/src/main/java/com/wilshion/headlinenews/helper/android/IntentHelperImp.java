package com.wilshion.headlinenews.helper.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;

import com.wilshion.headlinenews.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wilshion on 2017/8/7 11:13.
 * [description : ]
 * [version : 1.0]
 */
public class IntentHelperImp implements IIntentHelper {
    public static final String INTENT_INT = "intent_int";
    public static final String INTENT_STRING = "intent_string";
    public static final String INTENT_STRING_LIST = "intent_string_list";
    public static final String INTENT_PARCELABLE = "intent_parcelable";
    public static final String INTENT_PARCELABLE_LIST = "intent_parcelable_list";

    private Activity mActivity;

    public IntentHelperImp(Activity activity) {
        mActivity = activity;
    }

    @Override
    public void startActivity(Class<? extends BaseActivity> tartgetAc) {
        mActivity.startActivity(new Intent(mActivity, tartgetAc));
    }

    @Override
    public void startActivity(Class<? extends BaseActivity> tartgetAc, int value) {
        Intent intent = new Intent(mActivity, tartgetAc);
        intent.putExtra(INTENT_INT, value);
        mActivity.startActivity(intent);
    }

    @Override
    public void startActivity(Class<? extends BaseActivity> tartgetAc, String value) {
        Intent intent = new Intent(mActivity, tartgetAc);
        intent.putExtra(INTENT_STRING, value);
        mActivity.startActivity(intent);
    }

    @Override
    public void startActivity(Class<? extends BaseActivity> tartgetAc, List<CharSequence> value) {
        Intent intent = new Intent(mActivity, tartgetAc);
        intent.putCharSequenceArrayListExtra(INTENT_STRING_LIST, (ArrayList<CharSequence>) value);
        mActivity.startActivity(intent);
    }

    @Override
    public void startActivity(Class<? extends BaseActivity> tartgetAc, Parcelable value) {
        Intent intent = new Intent(mActivity, tartgetAc);
        intent.putExtra(INTENT_PARCELABLE, value);
        mActivity.startActivity(intent);
    }

    @Override
    public void startActivity(Class<? extends BaseActivity> tartgetAc, ArrayList<Parcelable> parcelableList) {
        Intent intent = new Intent(mActivity, tartgetAc);
        intent.putParcelableArrayListExtra(INTENT_PARCELABLE_LIST, parcelableList);
        mActivity.startActivity(intent);
    }

    public void release() {
        if (mActivity != null)
            mActivity = null;
    }
}
