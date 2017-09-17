package com.wilshion.headlinenews.mvp;

import com.wilshion.headlinenews.mvp.contract.BaseContract;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Wilshion on 2017/8/6 19:42.
 * [description : ]
 * [version : 1.0]
 */
public class RxPresenter<T extends BaseContract.View> implements BaseContract.Presenter<T> {
    private T mView;
    protected CompositeDisposable mCompositeDisposable;

    protected void unDisposable() {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed())
            mCompositeDisposable.dispose();
    }
    

    protected void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null)
            mCompositeDisposable = new CompositeDisposable();
        mCompositeDisposable.add(disposable);
    }

    public T getView() {
        return mView;
    }

    @Override
    public void attachView(T view) {
//        LogUtils.e("attachView   " + view.toString());
        mView = view;
    }

    @Override
    public void detachView() {
//        LogUtils.e("detachView " + mView.toString());
        mView = null;
        unDisposable();
    }
}
