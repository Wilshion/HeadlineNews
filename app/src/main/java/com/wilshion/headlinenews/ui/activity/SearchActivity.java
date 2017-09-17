package com.wilshion.headlinenews.ui.activity;

import com.wilshion.headlinenews.R;
import com.wilshion.headlinenews.mvp.presenter.SearchPresenter;
import com.wilshion.headlinenews.ui.base.BaseMvpActivity;

/**
 * Created by Wilshion on 2017/9/6 16:36.
 * [description : 搜索页面]
 * [version : 1.0]
 */
public class SearchActivity extends BaseMvpActivity<SearchPresenter> {

    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initViews() {

    }
}
