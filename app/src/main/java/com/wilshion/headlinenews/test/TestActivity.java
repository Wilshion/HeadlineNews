package com.wilshion.headlinenews.test;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wilshion.headlinenews.R;
import com.wilshion.headlinenews.ui.base.BaseNavBarActivity;

/**
 * Created by Wilshion on 2017/8/29 21:54.
 * [description : ]
 * [version : 1.0]
 */
public class TestActivity extends BaseNavBarActivity implements OnRefreshListener, OnLoadmoreListener {
    SmartRefreshLayout mRefreshLayout;
    RecyclerView mRecyclerView;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }


    @Override
    protected void initViews() {
        mRefreshLayout = (SmartRefreshLayout) findViewById(R.id.id_refresh_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadmoreListener(this);
        mRefreshLayout.autoRefresh(0);
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.base_recycler_view;
    }

    @Override
    protected void setupNavigationBar() {
        setNavBarTitle("测试的");
    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        
    }
}
