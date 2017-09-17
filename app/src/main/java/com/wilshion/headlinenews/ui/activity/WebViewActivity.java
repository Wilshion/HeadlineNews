package com.wilshion.headlinenews.ui.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.wilshion.headlinenews.R;
import com.wilshion.headlinenews.model.bean.UserEntity;
import com.wilshion.headlinenews.mvp.contract.BaseContract;
import com.wilshion.headlinenews.ui.base.BaseMvpActivity;
import com.wilshion.headlinenews.view.UIWebView;
import com.wilshion.utillib.util.ResouceUtil;
import com.wilshion.utillib.util.StatusBarUtil;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Wilshion on 2017/9/14 15:58.
 * [description : ]
 * [version : 1.0]
 */
public class WebViewActivity extends BaseMvpActivity implements View.OnClickListener {
    public static String URL = "url";
    public static String USER = "user";
    private TextView mAutorTv;
    private CircleImageView mAvatarIv;
    private TextView mFollowTv;
    private UIWebView mWebView;

    private UserEntity mUserEntity;

    @Override
    protected void initViews() {
        StatusBarUtil.setPaddingSmart(this, findViewById(R.id.id_statusbar));
        StatusBarUtil.immersive(this, ResouceUtil.getColor(R.color.gray));
        
        mAutorTv = (TextView) findViewById(R.id.id_author_tv);
        mAvatarIv = (CircleImageView) findViewById(R.id.id_avatar_iv);
        mFollowTv = (TextView) findViewById(R.id.id_follow_tv);
        mWebView = (UIWebView) findViewById(R.id.id_webview);

        mAvatarIv.setVisibility(View.GONE);
        mFollowTv.setVisibility(View.VISIBLE);

        findViewById(R.id.id_back_iv).setOnClickListener(this);
        mFollowTv.setOnClickListener(this);
        mWebView.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {  //表示按返回键
                    mWebView.goBack();
                    return true;
                }
            }
            return false;
        });
    }

    @Override
    protected void initData(Intent preIntent) {
        String url = preIntent.getStringExtra(URL);
        mUserEntity = preIntent.getParcelableExtra(USER);
        mWebView.loadUrl(url);

        if (mUserEntity != null) {
            mAutorTv.setText(mUserEntity.name);
        }
    }

    @Override
    protected void requestData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.test_webview_activity;
    }

    @Override
    protected BaseContract.Presenter createPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_back_iv:
                finish();
                break;
            case R.id.id_follow_tv:
                showToastShort("关注，敬请期待");
                break;
            case R.id.id_more_iv:
                showToastShort("分享，敬请期待");
                break;
        }
    }
}
