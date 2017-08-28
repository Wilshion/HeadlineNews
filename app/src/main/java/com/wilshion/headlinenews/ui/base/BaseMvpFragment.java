package com.wilshion.headlinenews.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wilshion.headlinenews.mvp.contract.BaseContract;

/**
 * Created by Wilshion on 2017/8/8 14:33.
 * [description : ]
 * [version : 1.0]
 */
public abstract class BaseMvpFragment<T extends BaseContract.Presenter> extends BaseFragment
        implements BaseContract.View {
    private T mPresenter;

    @Override
    public void showError(String msg) {
        
    }
    

    public void requestData() {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }

    @Override
    protected void initViews() {
        if (mPresenter == null)
            mPresenter = createPresenter();
        mPresenter.attachView(this);

        requestData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
    }

    protected abstract T createPresenter();



    public T getPresenter() {
        return mPresenter;
    }


}
