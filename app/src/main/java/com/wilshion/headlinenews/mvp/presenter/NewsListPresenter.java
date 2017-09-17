package com.wilshion.headlinenews.mvp.presenter;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.wilshion.headlinenews.model.bean.News;
import com.wilshion.headlinenews.model.bean.NewsData;
import com.wilshion.headlinenews.model.bean.TipBean;
import com.wilshion.headlinenews.model.http.HNHttpHelper;
import com.wilshion.headlinenews.model.http.response.NewsResponse;
import com.wilshion.headlinenews.model.sp.ChannelHelper;
import com.wilshion.headlinenews.mvp.RxPresenter;
import com.wilshion.headlinenews.mvp.contract.NewsListContract;
import com.wilshion.utillib.util.EmptyUtils;
import com.wilshion.utillib.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Wilshion on 2017/8/13 15:05.
 * [description : ]
 * [version : 1.0]
 */
public class NewsListPresenter extends RxPresenter<NewsListContract.View>
        implements NewsListContract.Presenter {
    private long lastTime;
    private Disposable mDisposable;

    @Override
    public void requestNewsList(String channelCode, int page) {
        lastTime = ChannelHelper.getLastUpdateTime(channelCode);
        //读取对应频道下最后一次刷新的时间戳
        if (lastTime == 0) {
            //如果为空，则是从来没有刷新过，使用当前时间戳
            lastTime = System.currentTimeMillis() / 1000;
        }
        long curTiem = System.currentTimeMillis();
        Observable observable = HNHttpHelper.getInstance().getHomeApi().getNewsList(channelCode, lastTime, curTiem);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull NewsResponse newsResponse) {
                        lastTime = System.currentTimeMillis() / 1000;
                        ChannelHelper.setLastUpdateTime(channelCode, lastTime);
                        List<NewsData> data = newsResponse.getData();
                        List<News> newsList = new ArrayList<>();
                        if (!EmptyUtils.isEmpty(data)) {
                            for (NewsData newsData : data) {
                                News news = new Gson().fromJson(newsData.getContent(), News.class);
                                newsList.add(news);
                            }
                        }
                        TipBean tipBean = newsResponse.getTips();
                        NewsListContract.View view = getView();
                        if (view == null)
                            LogUtils.e("无法获取到 NewsListContract.View");
                        if (newsList == null)
                            LogUtils.e("newsList == null");
                        if (tipBean == null)
                            LogUtils.e("tipBean == null");
                        String tipInfo = "刷新成功";
                        if (tipBean!=null)
                            tipInfo = tipBean.getDisplay_info();
                        getView().showNewsList(newsList, page == 1, tipInfo);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtils.e(e);
                        getView().showError(e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d("onComplete");
                    }
                });
        addDisposable(mDisposable);
    }
}
