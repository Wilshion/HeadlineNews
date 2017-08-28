package com.wilshion.headlinenews.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.nukc.stateview.StateView;
import com.wilshion.headlinenews.R;
import com.wilshion.headlinenews.helper.android.ILogHelper;
import com.wilshion.headlinenews.helper.android.IToastHelper;
import com.wilshion.headlinenews.helper.android.LogHelperImp;
import com.wilshion.headlinenews.helper.android.ToastHelperImp;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Wilshion on 2017/8/6 19:06.
 * [description : ]
 * [version : 1.0]
 */
public abstract class BaseFragment extends SupportFragment implements ILogHelper, IToastHelper {
    private final String TAG = getClass().getSimpleName();
    private View mView;
    private LogHelperImp mLogHelper;
    private ToastHelperImp mToastHelper;
    private StateView mStateView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        logD("onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logD("onCreate");
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
        logD("onCreateView");
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logD("onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        logD("onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        logD("onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        logD("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        logD("onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        logD("onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        logD("onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        logD("onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        logD("onDetach");
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
    
    
     /*
     * *********************************************     
     *              ISupport 方法 
     * *********************************************
     */

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        /** 次方法会在 onSupportVisible 之后执行*/
        super.onLazyInitView(savedInstanceState);
        logD("onLazyInitView");
        initViews();
    }

    @Override
    public void onSupportVisible() {
        /** 次方法会在 onResume 之后执行*/
        super.onSupportVisible();
        logD("onSupportVisible");
    }

    @Override
    public void onSupportInvisible() {
        /** 次方法会在 onPause 之前执行*/
        super.onSupportInvisible();
        logD("onSupportInvisible");
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
}
