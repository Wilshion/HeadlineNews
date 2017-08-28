package com.wilshion.headlinenews.ui.fragment;

import com.wilshion.headlinenews.R;
import com.wilshion.headlinenews.ui.base.BaseFragment;
import com.wilshion.headlinenews.view.UILoadingView;


/**
 * Created by Wilshion on 2017/8/12 21:31.
 * [description : 我的]
 * [version : 1.0]
 */
public class MineMineFragment extends BaseFragment {
    UILoadingView mUILoadingView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initViews() {
        findViewById(R.id.id_hide).setOnClickListener((view) -> mUILoadingView.hide());
        mUILoadingView = (UILoadingView) findViewById(R.id.id_loading_view);
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();

        mUILoadingView.hide();
    }
}
