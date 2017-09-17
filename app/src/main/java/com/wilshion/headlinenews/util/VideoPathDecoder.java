package com.wilshion.headlinenews.util;

import android.support.annotation.NonNull;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wilshion.headlinenews.model.http.HNHttpHelper;
import com.wilshion.headlinenews.model.http.response.VideoPathResponse;
import com.wilshion.utillib.util.EmptyUtils;
import com.wilshion.utillib.util.LogUtils;
import com.wilshion.utillib.util.Utils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Wilshion on 2017/9/4 09:36.
 * [description : ]
 * [version : 1.0]
 */
public abstract class VideoPathDecoder {
    private static final String NICK = "chaychan";

    public void decodePath(String srcUrl) {
        WebView webView = new WebView(Utils.getContext());

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);//设置JS可用
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        ParseRelation relation = new ParseRelation(new IGetParamsListener() {
            @Override
            public void onGetParams(String r, String s) {
                sendRequest(srcUrl, r, s);
            }
        });

        webView.addJavascriptInterface(relation, NICK);//绑定JS和Java的联系类，以及使用到的昵称

        webView.loadUrl("file:///android_asset/parse.html");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                webView.loadUrl("javascript:getParseParam('" + srcUrl + "')");
            }
        });
    }

    private void sendRequest(String srcUrl, String r, String s) {
        HNHttpHelper.getInstance().getHomeApi().getVideoPath(srcUrl, r, s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VideoPathResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull VideoPathResponse videoPathResponse) {
                        LogUtils.e("parseVideoResponse: " + videoPathResponse);
                        String url = "";
                        switch (videoPathResponse.retCode) {
                            case 200:
                                //请求成功
                                List<VideoPathResponse.DataBean.VideoBean.DownloadBean> downloadList = videoPathResponse.data.video.download;
                                if (!EmptyUtils.isEmpty(downloadList)) {
                                    url = downloadList.get(downloadList.size() - 1).url;//获取下载地址中最后一个地址，即超清
                                }
                                onSuccess(url);
                                break;
                            case 400:
                                decodePath(srcUrl);//如果请求失败，可能是生成的r s请求参数不正确，重新再调用
                                break;
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtils.e(e.getLocalizedMessage());
                        onDecodeError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
//       HNHttpHelper.getInstance().getHomeApi().getVideoPath(srcUrl, r, s).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<VideoPathResponse>() {
//
//                    @Override
//                    public void onError(Throwable e) {
//                        LogUtils.e(e.getLocalizedMessage());
//                        onDecodeError();
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//
//                    @Override
//                    public void onNext(VideoPathResponse response) {
//                        LogUtils.e("parseVideoResponse: " + response);
//                        String url = "";
//                        switch (response.retCode) {
//                            case 200:
//                                //请求成功
//                                List<VideoPathResponse.DataBean.VideoBean.DownloadBean> downloadList = response.data.video.download;
//                                if (!EmptyUtils.isEmpty(downloadList)) {
//                                    url = downloadList.get(downloadList.size() - 1).url;//获取下载地址中最后一个地址，即超清
//                                }
//                                onSuccess(url);
//                                break;
//                            case 400:
//                                decodePath(srcUrl);//如果请求失败，可能是生成的r s请求参数不正确，重新再调用
//                                break;
//                        }
//                    }
//                });
    }


    public abstract void onSuccess(String url);

    public abstract void onDecodeError();


    private class ParseRelation {

        IGetParamsListener mListener;

        public ParseRelation(IGetParamsListener listener) {
            mListener = listener;
        }

        @JavascriptInterface
        public void onReceiveParams(String r, String s) {
            mListener.onGetParams(r, s);
        }
    }

    public interface IGetParamsListener {
        void onGetParams(String r, String s);
    }

}
