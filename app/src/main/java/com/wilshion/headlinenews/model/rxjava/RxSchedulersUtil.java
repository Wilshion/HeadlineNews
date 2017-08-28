package com.wilshion.headlinenews.model.rxjava;

import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Wilshion on 2017/8/8 14:25.
 * [description : 封装了 线程调度]
 * [version : 1.0]
 */
public class RxSchedulersUtil {
    public static <T> FlowableTransformer<T,T> io_main(){
        return ob -> ob.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
