package com.wilshion.headlinenews.ui;

import com.wilshion.headlinenews.R;
import com.wilshion.headlinenews.mvp.contract.MainContract;
import com.wilshion.headlinenews.mvp.presenter.MainPresenter;
import com.wilshion.headlinenews.ui.base.BaseFragment;
import com.wilshion.headlinenews.ui.base.BaseMvpActivity;
import com.wilshion.headlinenews.ui.fragment.HomeMainFragment;
import com.wilshion.headlinenews.ui.fragment.MicroMainFragment;
import com.wilshion.headlinenews.ui.fragment.MineMineFragment;
import com.wilshion.headlinenews.ui.fragment.VideoMainFragment;
import com.wilshion.headlinenews.view.tabbar.UIBottomBar;
import com.wilshion.headlinenews.view.tabbar.UIBottomBarItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseMvpActivity<MainContract.Presenter> implements MainContract.View,
        UIBottomBar.OnBottomBarItemClickListener {
    private UIBottomBar mUIBottomBar;
    private List<BaseFragment> mFragmentList;

    @Override
    protected MainContract.Presenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        initFragments();
        initBottomBar();
    }

    private void initFragments() {
        mFragmentList = new ArrayList<>();
        HomeMainFragment homeMainFragment = new HomeMainFragment();
        VideoMainFragment videoMainFragment = new VideoMainFragment();
        MicroMainFragment microMainFragment = new MicroMainFragment();
        MineMineFragment mineMineFragment = new MineMineFragment();
        mFragmentList.add(homeMainFragment);
        mFragmentList.add(videoMainFragment);
        mFragmentList.add(microMainFragment);
        mFragmentList.add(mineMineFragment);
        
        loadMultipleRootFragment(R.id.id_container_fl, 0
                , homeMainFragment,videoMainFragment, microMainFragment, mineMineFragment);
    }


    private void initBottomBar() {
        mUIBottomBar = (UIBottomBar) findViewById(R.id.id_bottom_bar);
        mUIBottomBar.setOnBottomBarItemClickListener(this);
    }

    @Override
    public void showFragment(int position) {
        showHideFragment(mFragmentList.get(position));
    }

    @Override
    public void onItemClick(UIBottomBar bottomBar, UIBottomBarItem bottomBarItem, int position) {
        showFragment(position);
    }


}
