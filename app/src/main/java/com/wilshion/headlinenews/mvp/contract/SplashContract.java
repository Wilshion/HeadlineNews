package com.wilshion.headlinenews.mvp.contract;

/**
 * Created by Wilshion on 2017/8/8 16:22.
 * [description : ]
 * [version : 1.0]
 */
public interface SplashContract {
    interface View extends BaseContract.View {
        /**
         * 进入主界面
         */
        void goToMainActivity();

        /**
         * 进入广告详情页面
         */
        void goToAdDetailActivity();

        /**
         * 展示广告内容
         */
        void showAdContent();
        
        void countDown(int senconds);
    }
    
    interface Presenter extends BaseContract.Presenter<View>{
        void getAdData();
    }
}
