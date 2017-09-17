package com.wilshion.headlinenews.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * Created by Wilshion on 2017/9/14 10:47.
 * [description : ]
 * [version : 1.0]
 */
public class UIWebView extends WebView {
    private ProgressBar mProgressBar;

    public UIWebView(Context context) {
        this(context, null);
    }

    public UIWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UIWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initSettings();
    }

    private void initSettings() {
        mProgressBar = new ProgressBar(getContext(), null, android.R.attr.progressBarStyleHorizontal);
        mProgressBar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 5, 0, 0));
        addView(mProgressBar);
//        getSettings().setBuiltInZoomControls(true);// 会出现放大缩小的按钮
        getSettings().setSupportZoom(true);
        getSettings().setUseWideViewPort(true);
        getSettings().setLoadWithOverviewMode(true);
        getSettings().setDomStorageEnabled(true);
        getSettings().setAllowFileAccess(true);
        getSettings().setJavaScriptEnabled(true);


        setWebChromeClient(new WebChromeClient());
        setWebViewClient(new WebClient());
    }

    private class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                mProgressBar.setVisibility(GONE);
            } else {
                if (mProgressBar.getVisibility() == GONE)
                    mProgressBar.setVisibility(VISIBLE);
                mProgressBar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }

    private class WebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) mProgressBar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        mProgressBar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public void loadHtml(String html) {
        if (TextUtils.isEmpty(html))
            return;
        html = "<!DOCTYPE html><html><meta name='viewport' content='width=device-width, initial-scale=1' /><head><style>img{width:80%; height:auto;}</style></head><body>"
                + html + "</body></html>";
        loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
    }
}
