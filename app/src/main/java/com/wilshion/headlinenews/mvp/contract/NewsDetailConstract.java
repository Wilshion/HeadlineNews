package com.wilshion.headlinenews.mvp.contract;

import com.wilshion.headlinenews.model.bean.NewsDetail;
import com.wilshion.headlinenews.model.http.response.CommentResponse;

/**
 * Created by Wilshion on 2017/9/14 15:32.
 * [description : ]
 * [version : 1.0]
 */
public interface NewsDetailConstract extends BaseContract {
    interface Presenter extends BaseContract.Presenter<View> {
        void getNewsDetail(String url);

        void getComment(String groupId, String itemId, int pageNow);
    }

    interface View extends BaseContract.View {
        void showNewsDetail(NewsDetail newsDetail);

        void showComment(CommentResponse commentResponse);

    }
}
