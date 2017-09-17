package com.wilshion.headlinenews.test;

import com.wilshion.headlinenews.R;
import com.wilshion.headlinenews.ui.base.BaseNavBarActivity;
import com.wilshion.headlinenews.view.UIWebView;

/**
 * Created by Wilshion on 2017/9/14 10:44.
 * [description : ]
 * [version : 1.0]
 */
public class TestWebViewActivity extends BaseNavBarActivity {
   UIWebView mWebView;

    @Override
    protected int getContentLayoutId() {
        return R.layout.test_webview_activity;
    }

    @Override
    protected void setupNavigationBar() {
        setNavBarTitle("测试webview");
    }


    @Override
    protected void initViews() {
        mWebView = (UIWebView) findViewById(R.id.id_webview);
        mWebView.loadUrl("http://jdcl-test.ztehealth.com/agedservice/modules/admin/jdclView/jdclContentView.html?artId=1378&tamptimes=1505296947326&sign=9d1009979ba20b1f98407064706dec70");
    }
}
