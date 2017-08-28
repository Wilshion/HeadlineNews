package com.wilshion.headlinenews.ui.fragment;

import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wilshion.headlinenews.R;
import com.wilshion.headlinenews.ui.base.BaseFragment;
import com.wilshion.headlinenews.view.UINavigationBar;

/**
 * Created by Wilshion on 2017/8/12 21:30.
 * [description : 微头条]
 * [version : 1.0]
 */
public class MicroMainFragment extends BaseFragment {
    private UINavigationBar mUINavigationBar;
    private SmartRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_micro_main;
    }

    @Override
    protected void initViews() {
        initCtrls();
    }

    private void initCtrls() {
        mUINavigationBar = (UINavigationBar) findViewById(R.id.id_nav_bar);
        mRefreshLayout = (SmartRefreshLayout) findViewById(R.id.id_refresh_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recycler_view);

        mUINavigationBar.setNavBarTitle("微头条");
        mUINavigationBar.setNavBarRightImg(R.drawable.micro_add_friend);
        mUINavigationBar.setNavBarLeftImg(-1);
        mUINavigationBar.setOnNavigationBarClickListener(new UINavigationBar.OnNavigationBarClickListener() {
            @Override
            public void onNavigationBarRightClicked() {
                logD("onNavigationBarRightClicked");
            }
        });
    }
}
