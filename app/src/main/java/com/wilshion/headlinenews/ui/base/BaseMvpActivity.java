package com.wilshion.headlinenews.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.wilshion.headlinenews.mvp.contract.BaseContract;

/**
 * Created by Wilshion on 2017/8/7 11:03.
 * [description : mvp 基类]
 * [version : 1.0]
 */
public abstract class BaseMvpActivity<T extends BaseContract.Presenter> extends BaseActivity implements BaseContract.View {
    private T mPresenter;
    private View mContentView;

    @Override
    public void showError(String msg) {
        logE(msg);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContentView = LayoutInflater.from(this).inflate(getLayoutId(), null);
        setContentView(mContentView);

        initPresenter();
        initViews();
        initData(getIntent());
        requestData();
    }
    
    private void initPresenter(){
        if (mPresenter == null)
            mPresenter = createPresenter();
        if (mPresenter != null)
            mPresenter.attachView(this);
    }


    @Override
    protected void initData(Intent preIntent) {

    }

    @Override
    protected void requestData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
    }

    protected abstract int getLayoutId();

    protected abstract T createPresenter();

    public T getPresenter() {
        if (mPresenter == null)
            mPresenter = createPresenter();
        return mPresenter;
    }

    public View getContentView() {
        return mContentView;
    }
}
