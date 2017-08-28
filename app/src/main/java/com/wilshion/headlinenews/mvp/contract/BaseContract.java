package com.wilshion.headlinenews.mvp.contract;

/**
 * Created by Wilshion on 2017/8/8 18:42.
 * [description : ]
 * [version : 1.0]
 */
public interface BaseContract {
    interface  View{
        void showError(String msg);
    }
    
    interface Presenter<T extends View>{
        void attachView(T view);

        void detachView();
    }
}
