package com.wilshion.headlinenews.test;

import android.support.annotation.NonNull;

import com.wilshion.headlinenews.model.http.HNHttpHelper;
import com.wilshion.headlinenews.model.http.response.TestResponse;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Wilshion on 2017/8/12 21:22.
 * [description : ]
 * [version : 1.0]
 */
public class HttpTest {
    private void testHttp() {
        Observable<TestResponse> observable = HNHttpHelper.getInstance().getVideoApi().queryChatAccountBySupId(272);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                    }
                })
                .subscribe(new Observer<TestResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        
                    }

                    @Override
                    public void onNext(@NonNull TestResponse testResponse) {
                      
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        
                    }

                    @Override
                    public void onComplete() {
                        
                    }
                });


    }
}
