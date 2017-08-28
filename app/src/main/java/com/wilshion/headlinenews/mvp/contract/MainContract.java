package com.wilshion.headlinenews.mvp.contract;

/**
 * Created by Wilshion on 2017/8/12 21:10.
 * [description : ]
 * [version : 1.0]
 */
public interface MainContract {
    interface View extends BaseContract.View {
        void showFragment(int position);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        
    }
}
