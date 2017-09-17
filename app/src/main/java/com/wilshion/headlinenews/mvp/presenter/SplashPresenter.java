package com.wilshion.headlinenews.mvp.presenter;


import android.support.annotation.NonNull;

import com.wilshion.headlinenews.mvp.RxPresenter;
import com.wilshion.headlinenews.mvp.contract.SplashContract;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Wilshion on 2017/8/8 16:26.
 * [description : ]
 * [version : 1.0]
 */
public class SplashPresenter extends RxPresenter<SplashContract.View> implements SplashContract.Presenter {
    private final int DELAY = 1;
    private Disposable mDisposable;
    @Override
    public void getAdData() {
      Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(DELAY)
                .map(aLong -> DELAY - aLong.intValue())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {

                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        getView().countDown(integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getView().showError(e.getMessage());
                        mDisposable.dispose();
                    }

                    @Override
                    public void onComplete() {
                        mDisposable.dispose();
                        getView().goToMainActivity();
                    }
                });

        addDisposable(mDisposable);
    }
}
