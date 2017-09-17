package com.wilshion.headlinenews.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.nukc.stateview.StateView;
import com.wilshion.headlinenews.R;
import com.wilshion.headlinenews.helper.android.IIntentHelper;
import com.wilshion.headlinenews.helper.android.ILogHelper;
import com.wilshion.headlinenews.helper.android.IToastHelper;
import com.wilshion.headlinenews.helper.android.IntentHelperImp;
import com.wilshion.headlinenews.helper.android.LogHelperImp;
import com.wilshion.headlinenews.helper.android.ToastHelperImp;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Wilshion on 2017/8/6 19:06.
 * [description : ]
 * [version : 1.0]
 */
public abstract class BaseFragment extends SupportFragment implements ILogHelper, IToastHelper, IIntentHelper {
    private final String TAG = getClass().getSimpleName();
    private View mView;
    private LogHelperImp mLogHelper;
    private IntentHelperImp mIntentHelper;
    private ToastHelperImp mToastHelper;
    private StateView mStateView;
    
    

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        logI("onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logI("onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), container, false);
//        FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.base_layout, container, false);
//        frameLayout.addView(mView);
        mStateView = StateView.inject(mView);
        if (mStateView != null) {
            mStateView.setLoadingResource(R.layout.page_loading);
            mStateView.setRetryResource(R.layout.page_net_error);
        }
        logI("onCreateView");
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logI("onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        logI("onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        logI("onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        logI("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        logI("onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        logI("onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        logI("onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        logI("onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        logI("onDetach");
    }

    /*
     * *********************************************
     *              abstract 方法 
     * *********************************************
     */
    protected abstract int getLayoutId();

    protected abstract void initViews();


    /*
     * *********************************************
     *              公开 方法 
     * *********************************************
     */
    protected View findViewById(int resId) {
        return getView().findViewById(resId);
    }

    protected View getRootView() {
        return mView;
    }

    protected StateView getStateView() {
        return mStateView;
    }

    /*
     * *********************************************     
     *              IToast 方法 
     * *********************************************
     */
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

    /*
     * *********************************************     
     *              ILog 方法 
     * *********************************************
     */
    @Override
    public void logD(Object msg) {
        getLogHelper().logD(msg);
    }

    @Override
    public void logI(Object msg) {
//        getLogHelper().logI(msg);
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
        getIntentHelper().startActivity(tartgetAc, value);
    }

    @Override
    public void startActivity(Class<? extends BaseActivity> tartgetAc, String value) {
        getIntentHelper().startActivity(tartgetAc, value);
    }

    @Override
    public void startActivity(Class<? extends BaseActivity> tartgetAc, List<CharSequence> value) {
        getIntentHelper().startActivity(tartgetAc, value);
    }

    @Override
    public void startActivity(Class<? extends BaseActivity> tartgetAc, Parcelable value) {
        getIntentHelper().startActivity(tartgetAc, value);
    }

    @Override
    public void startActivity(Class<? extends BaseActivity> tartgetAc, ArrayList<Parcelable> parcelableList) {
        getIntentHelper().startActivity(tartgetAc, parcelableList);
    }

     /*
     * *********************************************     
     *              ISupport 方法 
     * *********************************************
     */

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        /** 次方法会在 onSupportVisible 之后执行*/
        super.onLazyInitView(savedInstanceState);
        logI("onLazyInitView");
        initViews();
    }

    @Override
    public void onSupportVisible() {
        /** 次方法会在 onResume 之后执行*/
        super.onSupportVisible();
        logI("onSupportVisible");
    }

    @Override
    public void onSupportInvisible() {
        /** 次方法会在 onPause 之前执行*/
        super.onSupportInvisible();
        logI("onSupportInvisible");
    }

    @Override
    public boolean onBackPressedSupport() {
        return super.onBackPressedSupport();
    }
    
    
    /*
     * *********************************************     
     *              懒加载 
     * *********************************************
     */

    public LogHelperImp getLogHelper() {
        if (mLogHelper == null)
            mLogHelper = new LogHelperImp(TAG);
        return mLogHelper;
    }

    public ToastHelperImp getToastHelper() {
        if (mToastHelper == null)
            mToastHelper = new ToastHelperImp();
        return mToastHelper;
    }

    public IIntentHelper getIntentHelper() {
        if (mIntentHelper == null)
            mIntentHelper = new IntentHelperImp(getActivity());
        return mIntentHelper;
    }
}
