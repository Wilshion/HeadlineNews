package com.wilshion.headlinenews.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.roundview.RoundTextView;
import com.sunfusheng.glideimageview.progress.GlideApp;
import com.wilshion.headlinenews.R;
import com.wilshion.headlinenews.model.bean.NewsDetail;
import com.wilshion.utillib.util.TimeUtils;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Wilshion on 2017/9/14 17:13.
 * [description : ]
 * [version : 1.0]
 */
public class NewsDetailHeader extends FrameLayout {
    private CircleImageView mAvatarIv;
    private RelativeLayout mUserRl;
    private TextView mTitleTv;
    private TextView mAutorTv;
    private TextView mTimeTv;
    private RoundTextView mFollowTv;
    private WebView mWebView;
    
    private int mHeaderHeight;

    public NewsDetailHeader(@NonNull Context context) {
        this(context, null);
    }

    public NewsDetailHeader(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NewsDetailHeader(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initViews();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeaderHeight = getMeasuredHeight();
    }

    private void initViews() {
        View.inflate(getContext(), R.layout.part_news_detail_header, this);
        mUserRl = (RelativeLayout) findViewById(R.id.id_user_rl);
        mAvatarIv = (CircleImageView) findViewById(R.id.id_avatar_iv);
        mTitleTv = (TextView) findViewById(R.id.id_title_tv);
        mAutorTv = (TextView) findViewById(R.id.id_author_tv);
        mTimeTv = (TextView) findViewById(R.id.id_time_tv);
        mFollowTv = (RoundTextView) findViewById(R.id.id_follow_tv);
        mWebView = (WebView) findViewById(R.id.id_webview);
    }

    public int getHeaderHeight() {
        return mHeaderHeight;
    }

    public void setNewsDetail(NewsDetail newsDetail) {
        mTitleTv.setText(newsDetail.title);
        if (newsDetail.media_user == null) {
            //如果没有用户信息
            mUserRl.setVisibility(GONE);
        } else {
            GlideApp.with(getContext()).load(newsDetail.media_user.avatar_url).into(mAvatarIv);
            mAutorTv.setText(newsDetail.media_user.screen_name);
            mTimeTv.setText(TimeUtils.getFriendlyTimeSpanByNow(newsDetail.publish_time * 1000L));
        }
        if (TextUtils.isEmpty(newsDetail.content))
            mWebView.setVisibility(GONE);
        String htmlPart1 = "<!DOCTYPE HTML html>\n" +
                "<head><meta charset=\"utf-8\"/>\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, user-scalable=no\"/>\n" +
                "</head>\n" +
                "<body>\n" +
                "<style> \n" +
                "img{max-width:100%!important;height:auto!important}\n" +
                " </style>";
        String htmlPart2 = "</body></html>";

        String html = htmlPart1 + newsDetail.content + htmlPart2;


        mWebView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
    }
}
