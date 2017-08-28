package com.wilshion.headlinenews.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wilshion.headlinenews.mvp.contract.BaseContract;

/**
 * Created by Wilshion on 2017/8/7 11:03.
 * [description : mvp 基类]
 * [version : 1.0]
 */
public abstract class BaseMvpActivity<T extends BaseContract.Presenter> extends BaseActivity implements BaseContract.View {
    private T mPresenter;

    @Override
    public void showError(String msg) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        if (mPresenter == null)
            mPresenter = createPresenter();
        mPresenter.attachView(this);
        initViews();
        requestData();
    }


    public void requestData() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    protected abstract T createPresenter();

    protected abstract int getLayoutId();

    protected abstract void initViews();

    public T getPresenter() {
        return mPresenter;
    }
}
