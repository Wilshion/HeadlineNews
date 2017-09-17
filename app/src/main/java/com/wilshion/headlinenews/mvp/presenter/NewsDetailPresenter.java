package com.wilshion.headlinenews.mvp.presenter;

import android.support.annotation.NonNull;

import com.wilshion.headlinenews.constant.Constant;
import com.wilshion.headlinenews.model.bean.NewsDetail;
import com.wilshion.headlinenews.model.http.HNHttpHelper;
import com.wilshion.headlinenews.model.http.response.CommentResponse;
import com.wilshion.headlinenews.model.http.response.ResultResponse;
import com.wilshion.headlinenews.mvp.RxPresenter;
import com.wilshion.headlinenews.mvp.contract.NewsDetailConstract;
import com.wilshion.utillib.util.LogUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Wilshion on 2017/9/14 15:34.
 * [description : ]
 * [version : 1.0]
 */
public class NewsDetailPresenter extends RxPresenter<NewsDetailConstract.View> implements NewsDetailConstract.Presenter {
    private Disposable mNewsDetailDisposable, mCommentDisposable;

    @Override
    public void getNewsDetail(String url) {
        Observable<ResultResponse<NewsDetail>> observable = HNHttpHelper.getInstance().getHomeApi().getNewsDetail(url);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultResponse<NewsDetail>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mNewsDetailDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull ResultResponse<NewsDetail> newsDetailResultResponse) {
                        getView().showNewsDetail(newsDetailResultResponse.data);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getView().showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        addDisposable(mNewsDetailDisposable);
    }

    @Override
    public void getComment(String groupId, String itemId, int pageNow) {
        int offset = (pageNow - 1) * Constant.COMMENT_PAGE_SIZE;
        
        Observable<CommentResponse> observable = HNHttpHelper.getInstance().getHomeApi().getComment(groupId, itemId,
                offset + "", String.valueOf(Constant.COMMENT_PAGE_SIZE));
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( new Observer<CommentResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCommentDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull CommentResponse commentResponse) {
                        getView().showComment(commentResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtils.e(e);
                        getView().showError(e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                } );
        
        
        /*
        new Observer<CommentResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCommentDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull CommentResponse commentResponse) {
                        getView().showComment(commentResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getView().showError(e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                }
         */

        addDisposable(mCommentDisposable);
    }
}
