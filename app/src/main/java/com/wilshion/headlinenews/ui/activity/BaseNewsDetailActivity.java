package com.wilshion.headlinenews.ui.activity;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.nukc.stateview.StateView;
import com.wilshion.headlinenews.R;
import com.wilshion.headlinenews.constant.Constant;
import com.wilshion.headlinenews.model.http.response.CommentResponse;
import com.wilshion.headlinenews.mvp.contract.NewsDetailConstract;
import com.wilshion.headlinenews.mvp.presenter.NewsDetailPresenter;
import com.wilshion.headlinenews.ui.adapter.CommentAdapter;
import com.wilshion.headlinenews.ui.base.BaseMvpActivity;
import com.wilshion.headlinenews.view.NewsDetailHeader;
import com.wilshion.headlinenews.view.UIBadgeView;
import com.wilshion.utillib.util.EmptyUtils;
import com.wilshion.utillib.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wilshion on 2017/9/14 16:29.
 * [description : ]
 * [version : 1.0]
 */
public abstract class BaseNewsDetailActivity extends BaseMvpActivity<NewsDetailPresenter>
        implements NewsDetailConstract.View, BaseQuickAdapter.RequestLoadMoreListener, View.OnClickListener {
    public static final String CHANNEL_CODE = "channelCode";
    public static final String PROGRESS = "progress";
    public static final String POSITION = "position";
    public static final String DETAIL_URL = "detailUrl";
    public static final String GROUP_ID = "groupId";
    public static final String ITEM_ID = "itemId";

    private String mDetalUrl;
    private String mGroupId;
    private String mItemId;
    protected String mChannelCode;
    protected int mPosition;

    private ScrollView mScrollView;

    private RelativeLayout mContentFl;
    private StateView mStateView;
    protected NewsDetailHeader mNewsDetailHeader;
    protected RecyclerView mCommentRv;
    protected CommentAdapter mCommentAdapter;
    private List<CommentResponse.CommentData> mCommentList;
    private CommentResponse mCommentResponse;

    private UIBadgeView mCommentCountTv;
    private boolean mShowComment;

    @Override
    protected NewsDetailPresenter createPresenter() {
        return new NewsDetailPresenter();
    }

    @Override
    protected void initViews() {
        /** findViewById */
        mScrollView = (ScrollView) findViewById(R.id.id_content_sv);
        mContentFl = (RelativeLayout) findViewById(R.id.id_content_fl);
        mCommentRv = (RecyclerView) findViewById(R.id.id_recycler_view);
        mCommentCountTv = (UIBadgeView) findViewById(R.id.id_comment_count_tv);

        /** RecyclerView */
        mCommentRv.setLayoutManager(new LinearLayoutManager(this));
        mCommentRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        /** StateView */
        mStateView = StateView.inject(mContentFl);
        mStateView.setLoadingResource(R.layout.page_loading);
        mStateView.setEmptyResource(R.layout.page_no_comment);
        mStateView.setRetryResource(R.layout.page_net_error);

        /** NewsDetailHeader */
        mNewsDetailHeader = (NewsDetailHeader) findViewById(R.id.id_news_detail_header);
    }

    @Override
    protected void initData(Intent intent) {
        mCommentList = new ArrayList<>();

        mChannelCode = intent.getStringExtra(CHANNEL_CODE);
        mPosition = intent.getIntExtra(POSITION, 0);
        mDetalUrl = intent.getStringExtra(DETAIL_URL);
        mGroupId = intent.getStringExtra(GROUP_ID);
        mItemId = intent.getStringExtra(ITEM_ID);
        mItemId = mItemId.replace("i", "");
    }

    @Override
    public void requestData() {
        getPresenter().getNewsDetail(mDetalUrl);

        mStateView.showLoading();
        getPresenter().getComment(mGroupId, mItemId, 1);
    }


    @Override
    public void showComment(CommentResponse response) {
        mCommentResponse = response;

        if (EmptyUtils.isEmpty(mCommentList)) {
            //第一次访问
            if (EmptyUtils.isEmpty(response.data)) {
                //没有评论，展示空布局
                mStateView.showEmpty();
                return;
            }
            //展示内容布局
            mStateView.showContent();
        }

        if (EmptyUtils.isEmpty(response.data)) {
            //没有评论了
            mCommentAdapter.loadMoreEnd();
            return;
        }

        if (response.total_number > 0) {
            //如果评论数大于0，显示红点
            mCommentCountTv.setVisibility(View.VISIBLE);
            mCommentCountTv.setCount(response.total_number);
        }
        mCommentList.addAll(response.data);
        showComment();
        if (!response.has_more) {
            mCommentAdapter.loadMoreEnd();
        } else {
            mCommentAdapter.loadMoreComplete();
        }
    }

    private void showComment() {
        if (mCommentAdapter == null) {
            mCommentAdapter = new CommentAdapter(R.layout.item_comment, mCommentList);
            mCommentAdapter.setEnableLoadMore(true);
            mCommentAdapter.setOnLoadMoreListener(this, mCommentRv);
            mCommentRv.setAdapter(mCommentAdapter);
        } else
            mCommentAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String msg) {
        super.showError(msg);
        mStateView.showRetry();
    }

    @Override
    public void onLoadMoreRequested() {
        getPresenter().getComment(mGroupId, mItemId, mCommentList.size() / Constant.COMMENT_PAGE_SIZE + 1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_back_iv:
                finish();
                break;
            case R.id.id_comment_count_iv:
                toggleWithenContentAndComment();
                break;
            case R.id.id_collect_iv:
                showToastShort("收藏即将上线");
                break;
            case R.id.id_share_iv:
                showToastShort("分享");
                break;
        }
    }

    /**
     * 切换展示 内容和评论区域
     */
    private void toggleWithenContentAndComment() {
        int contentSize = mNewsDetailHeader.getHeaderHeight() + mCommentRv.getHeight();
        int curScrollY = mScrollView.getScrollY();
        LogUtils.e("contentSize = " + contentSize
                + "scrollViewHeight = " + mScrollView.getHeight()
                + "Headerheight = " + mNewsDetailHeader.getHeaderHeight()
                + " curScrollY =" + curScrollY
        );
        if (curScrollY < mNewsDetailHeader.getHeaderHeight() && !mShowComment) {
            mScrollView.smoothScrollTo(0, mNewsDetailHeader.getHeaderHeight());
            mShowComment = true;
        } else {
            mScrollView.smoothScrollTo(0, 0);
            mShowComment = false;
        }
    }
}
