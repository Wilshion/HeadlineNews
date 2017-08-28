package com.wilshion.headlinenews.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.wilshion.headlinenews.helper.android.IIntentHelper;
import com.wilshion.headlinenews.helper.android.ILogHelper;
import com.wilshion.headlinenews.helper.android.IToastHelper;
import com.wilshion.headlinenews.helper.android.IntentHelperImp;
import com.wilshion.headlinenews.helper.android.LogHelperImp;
import com.wilshion.headlinenews.helper.android.ToastHelperImp;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by Wilshion on 2017/8/6 18:08.
 * [description : activity 基类 提供最基本的 log、toast,intent跳转 方法]
 * [version : 1.0]
 */
public class BaseActivity extends SupportActivity implements ILogHelper, IToastHelper,IIntentHelper {
    private ILogHelper mLogHelper;
    private IToastHelper mToastHelper;
    private IntentHelperImp mIntentHelper;

// =============================================================================================
//                                     生命周期
// =============================================================================================

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseAll();
    }

    // =============================================================================================
//                                     protect 方法
// =============================================================================================

    /**
     * 子类 可重载此方法
     * @return
     */
    protected String getTAG() {
        return getClass().getSimpleName();
    }




    // =============================================================================================
//                                     private 方法
// =============================================================================================
    private void releaseAll(){
        releaseLogHelper();
        releaseToastHelper();
        releaseIntentHelper();
    }
    private void releaseLogHelper(){
        if (mLogHelper != null)
            mLogHelper = null;
    }

    private void releaseToastHelper(){
        if (mToastHelper != null)
            mToastHelper = null;
    }

    private void releaseIntentHelper(){
        if (mIntentHelper != null)
            mIntentHelper.release();
    }
    

    // =============================================================================================
//                                     IToast 实现方法
// =============================================================================================
    @Override
    public void showToastShort(String msg) {
        getToastHelper().showToastShort(msg);
    }

    @Override
    public void showToastLong(String msg) {
        getToastHelper().showToastLong(msg);
    }

    @Override
    public void showToastDuration(String msg, int duration) {
        getToastHelper().showToastDuration(msg, duration);
    }

    // =============================================================================================
//                                     ILog 实现方法
// =============================================================================================

    @Override
    public void logD(Object msg) {
        getLogHelper().logD(msg);
    }

    @Override
    public void logI(Object msg) {
        getLogHelper().logI(msg);
    }

    @Override
    public void logW(Object msg) {
        getLogHelper().logW(msg);
    }

    @Override
    public void logE(Object msg) {
        getLogHelper().logE(msg);
    }

    @Override
    public void logA(Object msg) {
        getLogHelper().logA(msg);
    }


// =============================================================================================
//                               IIntentHelper 实现方法
// =============================================================================================

    @Override
    public void startActivity(Class<? extends BaseActivity> tartgetAc) {
        getIntentHelper().startActivity(tartgetAc);
    }

    @Override
    public void startActivity(Class<? extends BaseActivity> tartgetAc, int value) {
        getIntentHelper().startActivity(tartgetAc,value);
    }

    @Override
    public void startActivity(Class<? extends BaseActivity> tartgetAc, String value) {
        getIntentHelper().startActivity(tartgetAc,value);
    }

    @Override
    public void startActivity(Class<? extends BaseActivity> tartgetAc, List<CharSequence> value) {
        getIntentHelper().startActivity(tartgetAc,value);
    }

    @Override
    public void startActivity(Class<? extends BaseActivity> tartgetAc, Parcelable value) {
        getIntentHelper().startActivity(tartgetAc,value);
    }

    @Override
    public void startActivity(Class<? extends BaseActivity> tartgetAc, ArrayList<Parcelable> parcelableList) {
        getIntentHelper().startActivity(tartgetAc,parcelableList);
    }


// =============================================================================================
//                                     懒加载
// =============================================================================================

    public ILogHelper getLogHelper() {
        if (mLogHelper == null)
            mLogHelper = new LogHelperImp(getTAG());
        return mLogHelper;
    }

    public IToastHelper getToastHelper() {
        if (mToastHelper == null)
            mToastHelper = new ToastHelperImp();
        return mToastHelper;
    }

    public IIntentHelper getIntentHelper() {
        if (mIntentHelper == null)
            mIntentHelper = new IntentHelperImp(this);
        return mIntentHelper;
    }

    // =============================================================================================
//               当页面有 键盘时候，触摸其他地方，键盘消失的两个方法，被动
// =============================================================================================

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

   
}
