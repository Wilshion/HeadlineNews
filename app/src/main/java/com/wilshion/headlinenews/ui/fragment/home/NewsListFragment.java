package com.wilshion.headlinenews.ui.fragment.home;

import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wilshion.headlinenews.R;
import com.wilshion.headlinenews.constant.Constant;
import com.wilshion.headlinenews.model.bean.News;
import com.wilshion.headlinenews.mvp.contract.NewsListContract;
import com.wilshion.headlinenews.mvp.presenter.NewsListPresenter;
import com.wilshion.headlinenews.ui.adapter.NewsListAdapter2;
import com.wilshion.headlinenews.ui.base.BaseMvpFragment;
import com.wilshion.headlinenews.view.UITipView;
import com.wilshion.utillib.util.EmptyUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wilshion on 2017/8/13 15:01.
 * [description : ]
 * [version : 1.0]
 */
public class NewsListFragment extends BaseMvpFragment<NewsListPresenter> implements NewsListContract.View, OnRefreshListener, OnLoadmoreListener {
    private SmartRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private String mChannelCode;
    private List<News> mData = new ArrayList<>();
    private NewsListAdapter2 mAdapter;
    private int mPage = 1;
    private UITipView mTipView;

    @Override
    public void showNewsList(List<News> newsList, boolean isPullToRefresh, String msg) {
        getStateView().showContent();
        if (mAdapter == null) {
            mAdapter = new NewsListAdapter2(getActivity(), mChannelCode, newsList, false);
            mRecyclerView.setAdapter(mAdapter);
            mData = newsList;
        } else {
            if (isPullToRefresh) {
                mData = newsList;
            } else {
                mData.addAll(newsList);
                mAdapter.updateData(mData);
            }
            mAdapter.updateData(mData);
        }
        if (EmptyUtils.isEmpty(mData)) {
            getStateView().showEmpty();
        }
        if (isPullToRefresh) {
            mRefreshLayout.finishRefresh();
            mTipView.show(msg);
        } else mRefreshLayout.finishLoadmore();
    }

    @Override
    protected NewsListPresenter createPresenter() {
        return new NewsListPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.base_recycler_view;
    }

    @Override
    protected void initViews() {
        super.initViews();
        getStateView().showLoading();
        mTipView = (UITipView) findViewById(R.id.id_tip_view);
        mRefreshLayout = (SmartRefreshLayout) findViewById(R.id.id_refresh_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        mChannelCode = getArguments().getString(Constant.CHANNEL_CODE);

        ClassicsHeader classicsHeader = (ClassicsHeader) mRefreshLayout.getRefreshHeader();
        classicsHeader.setSpinnerStyle(SpinnerStyle.Translate);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadmoreListener(this);
        mRefreshLayout.autoRefresh(0);
//        mRefreshLayout.setDisableContentWhenLoading(true);
//        mRefreshLayout.setDisableContentWhenRefresh(true);
//        mRefreshLayout.setDragRate(0.6f);
//        mRefreshLayout.setEnableAutoLoadmore(true);
//        mRefreshLayout.setEnableLoadmore(true);
//        mRefreshLayout.setEnableLoadmoreWhenContentNotFull(true);
//        mRefreshLayout.setEnableNestedScroll(true);
//        mRefreshLayout.setEnableOverScrollBounce(true);
//        mRefreshLayout.setEnablePureScrollMode(true);
//        mRefreshLayout.setEnableScrollContentWhenLoaded(true);
//        mRefreshLayout.setEnableRefresh(true);
//        mRefreshLayout.setReboundDuration(2);
//        mRefreshLayout.setReboundInterpolator(null);


//       
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        new Handler().postDelayed(() -> getPresenter().requestNewsList(mChannelCode, mPage), 3000);
//        getPresenter().requestNewsList(mChannelCode, mPage);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        mPage++;
        getPresenter().requestNewsList(mChannelCode, mPage);
    }

    @Override
    public void showError(String msg) {
        super.showError(msg);
        mRefreshLayout.finishRefresh();
        mTipView.show(msg);
    }
}
