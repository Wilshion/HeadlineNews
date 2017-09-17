package com.wilshion.headlinenews.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.wilshion.headlinenews.R;
import com.wilshion.headlinenews.mvp.view.NavigationBarView;
import com.wilshion.headlinenews.view.UINavigationBar;

/**
 * Created by Wilshion on 2017/8/8 14:33.
 * [description : ]
 * [version : 1.0]
 */
public abstract class BaseNavBarActivity extends BaseActivity 
        implements NavigationBarView {
    private UINavigationBar mUINavigationBar;
    private FrameLayout mContentViewFl;
    private View mContentView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base_nav_bar);
        initNavigationBar();
        initContentLayoutView();
        initViews();
    }
    
    

    @Override
    protected void initData(Intent preIntent) {
        
    }

    @Override
    protected void requestData() {
        
    }

    private void initNavigationBar() {
        mUINavigationBar = (UINavigationBar) findViewById(R.id.id_nav_bar);
        mUINavigationBar.setOnNavigationBarClickListener(new UINavigationBar.OnNavigationBarClickListener(){
            @Override
            public void onNavigationBarLeftClicked() {
                logD("onNavigationBarLeftClicked");
                finish();
            }

            @Override
            public void onNavigationBarTitleClicked() {
                logD("onNavigationBarTitleClicked");
            }

            @Override
            public void onNavigationBarRightClicked() {
                logD("onNavigationBarRightClicked");
            }
        });

//        setSupportActionBar(mUINavigationBar);
        setupNavigationBar();
    }

    private void initContentLayoutView() {
        mContentViewFl = (FrameLayout) findViewById(R.id.id_content_fl);
        mContentView = LayoutInflater.from(this).inflate(getContentLayoutId(), null);
        mContentViewFl.addView(mContentView);
    }

    /**
     * 获得 内容视图的id
     *
     * @return
     */
    protected abstract int getContentLayoutId();

    /**
     * 设置 导航栏相关的属性
     */
    protected abstract void setupNavigationBar();

   

    protected final UINavigationBar getNavigationBar() {
        return mUINavigationBar;
    }

    protected final View getContentView() {
        return mContentView;
    }

    @Override
    public void onNavigationBarLeftClicked() {
        finish();
    }

    @Override
    public void onNavigationBarTitleClicked() {

    }

    @Override
    public void onNavigationBarRightClicked() {

    }


    @Override
    public void showError(String msg) {
        showToastShort(msg);
    }

    @Override
    public void setNavBarLeftImg(int resIcon) {
        mUINavigationBar.setNavBarLeftImg(resIcon);
    }

    @Override
    public void setNavBarLeftImg(String imgUrl) {
        mUINavigationBar.setNavBarLeftImg(imgUrl);
    }

    @Override
    public void setNavBarTitle(String title) {
        mUINavigationBar.setNavBarTitle(title);
    }

    @Override
    public void setNavBarRightText(String text) {
        mUINavigationBar.setNavBarRightText(text);
    }

    @Override
    public void setNavBarRightImg(int resIcon) {
        mUINavigationBar.setNavBarRightImg(resIcon);
    }

    @Override
    public void setNavBarRightImg(String imgUrl) {
        mUINavigationBar.setNavBarRightImg(imgUrl);
    }

   
}
