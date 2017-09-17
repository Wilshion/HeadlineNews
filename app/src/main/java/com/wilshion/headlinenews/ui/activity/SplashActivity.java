package com.wilshion.headlinenews.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.wilshion.headlinenews.R;
import com.wilshion.headlinenews.mvp.contract.SplashContract;
import com.wilshion.headlinenews.mvp.presenter.SplashPresenter;
import com.wilshion.headlinenews.ui.MainActivity;
import com.wilshion.headlinenews.ui.base.BaseMvpActivity;

/**
 * Created by Wilshion on 2017/8/8 16:18.
 * [description : ]
 * [version : 1.0]
 */
public class SplashActivity extends BaseMvpActivity<SplashPresenter> implements SplashContract.View, View.OnClickListener {
    private TextView mSkipTv;

    @Override
    protected SplashPresenter createPresenter() {
        return new SplashPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initViews() {
        mSkipTv = (TextView) findViewById(R.id.id_skip_tv);
        mSkipTv.setOnClickListener(this);
    }

    @Override
    public void requestData() {
        super.requestData();
        getPresenter().getAdData();
    }

    @Override
    public void goToMainActivity() {
        finish();   
        startActivity(MainActivity.class);
    }

    @Override
    public void goToAdDetailActivity() {
        finish();
    }

    @Override
    public void showAdContent() {
        
    }

    @Override
    public void countDown(int senconds) {
        mSkipTv.setText(String.format("(%d秒)跳过广告", senconds));
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.id_skip_tv:
                goToMainActivity();
                break;

            case R.id.id_ad_iv:
                goToAdDetailActivity();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logE("onDestroy");
    }
}
