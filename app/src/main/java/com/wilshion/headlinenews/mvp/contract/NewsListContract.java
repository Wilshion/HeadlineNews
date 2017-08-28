package com.wilshion.headlinenews.mvp.contract;

import com.wilshion.headlinenews.model.bean.News;

import java.util.List;

/**
 * Created by Wilshion on 2017/8/13 15:02.
 * [description : ]
 * [version : 1.0]
 */
public interface NewsListContract {
    interface View extends BaseContract.View {
        void showNewsList(List<News> newsList, boolean isPullToRefresh, String msg);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void requestNewsList(String channelCode,int page);
    }
}
