package com.wilshion.headlinenews.model.rxbus.event;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Wilshion on 2017/8/8 11:56.
 * [description : ]
 * [version : 1.0]
 */
public enum  EventThread {
    /**
     * 主线程
     */
    MAIN_THREAD,
    /**
     * 新的线程
     */
    NEW_THREAD,
    /**
     * 读写线程
     */
    IO,
    /**
     * 计算工作默认线程
     */
    COMPUTATION,
    /**
     * 在当前线程中按照队列方式执行
     */
    TRAMPOLINE;

    public static Scheduler getScheduler(EventThread threadMode){
        Scheduler scheduler;
        switch (threadMode){
            case MAIN_THREAD:scheduler= AndroidSchedulers.mainThread();break;
            case NEW_THREAD:scheduler= Schedulers.newThread();break;
            case IO:scheduler=Schedulers.io();break;
            case COMPUTATION:scheduler=Schedulers.computation();break;
            case TRAMPOLINE:scheduler=Schedulers.trampoline();break;
            default:scheduler= AndroidSchedulers.mainThread();
        }
        return scheduler;
    }
}
