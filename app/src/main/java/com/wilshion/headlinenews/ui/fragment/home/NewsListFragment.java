package com.wilshion.headlinenews.ui.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wilshion.headlinenews.R;
import com.wilshion.headlinenews.constant.Constant;
import com.wilshion.headlinenews.model.bean.Channel;
import com.wilshion.headlinenews.model.bean.News;
import com.wilshion.headlinenews.mvp.contract.NewsListContract;
import com.wilshion.headlinenews.mvp.presenter.NewsListPresenter;
import com.wilshion.headlinenews.ui.activity.BaseNewsDetailActivity;
import com.wilshion.headlinenews.ui.activity.NewsDetailActivity;
import com.wilshion.headlinenews.ui.activity.VideoDetailActivity;
import com.wilshion.headlinenews.ui.activity.WebViewActivity;
import com.wilshion.headlinenews.ui.adapter.NewsListAdapter;
import com.wilshion.headlinenews.ui.base.BaseMvpFragment;
import com.wilshion.headlinenews.view.UITipView;
import com.wilshion.utillib.util.EmptyUtils;
import com.wilshion.utillib.util.ResouceUtil;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCMediaManager;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerManager;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

import static fm.jiecao.jcvideoplayer_lib.JCVideoPlayer.CURRENT_STATE_PLAYING;

/**
 * Created by Wilshion on 2017/8/13 15:01.
 * [description : ]
 * [version : 1.0]
 */
public class NewsListFragment extends BaseMvpFragment<NewsListPresenter>
        implements NewsListContract.View, OnRefreshListener, OnLoadmoreListener,
        BaseQuickAdapter.OnItemClickListener {
    public static final String INTENT_PARAM_VIDEO = "is_video";
    private SmartRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private NewsListAdapter mAdapter;
    private UITipView mTipView;
    private String mChannelCode;
    private List<News> mData = new ArrayList<>();
    private int mPage = 1;
    private boolean mIsVideoList;

    public static NewsListFragment newInstance(Channel channel) {
        String[] channelCodes = ResouceUtil.getStringArray(R.array.channel_code);
        NewsListFragment newsFragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.CHANNEL_CODE, channel.channelCode);
        //是否是视频列表页面,根据判断频道号是否是视频
        bundle.putBoolean(NewsListFragment.INTENT_PARAM_VIDEO, channel.channelCode.equals(channelCodes[1]));
        newsFragment.setArguments(bundle);
        return newsFragment;
    }

    @Override
    public void showNewsList(List<News> newsList, boolean isPullToRefresh, String msg) {
        getStateView().showContent();
        if (mAdapter == null) {
            mAdapter = new NewsListAdapter(getActivity(), mChannelCode, newsList, mIsVideoList);
            mAdapter.setOnItemClickListener(this);
            mRecyclerView.setAdapter(mAdapter);
            mData = newsList;
        } else {
            if (isPullToRefresh) {
                mData = newsList;
            } else {
                mData.addAll(newsList);
                mAdapter.updateData(mData);
            }
            mAdapter.updateData(mData);
        }
        if (EmptyUtils.isEmpty(mData)) {
            getStateView().showRetry();
        }
        if (isPullToRefresh) {
            mRefreshLayout.finishRefresh();
            mTipView.show(msg);
        } else mRefreshLayout.finishLoadmore();
    }

    @Override
    protected NewsListPresenter createPresenter() {
        return new NewsListPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.base_recycler_view;
    }

    @Override
    protected void initViews() {
        super.initViews();
        getStateView().showLoading();
        getStateView().setOnRetryClickListener(() -> mRefreshLayout.autoRefresh());
        mTipView = (UITipView) findViewById(R.id.id_tip_view);
        mRefreshLayout = (SmartRefreshLayout) findViewById(R.id.id_refresh_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        mChannelCode = getArguments().getString(Constant.CHANNEL_CODE);
        mIsVideoList = getArguments().getBoolean(INTENT_PARAM_VIDEO);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadmoreListener(this);
        mRefreshLayout.autoRefresh();

       mRecyclerView.addOnScrollListener(new OnScrollListener() {
           @Override
           public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
               if (JCVideoPlayerManager.getCurrentJcvd()!=null){
                   JCVideoPlayerStandard videoPlayer = (JCVideoPlayerStandard) JCVideoPlayerManager.getCurrentJcvd();
                   if (videoPlayer.currentState == CURRENT_STATE_PLAYING) {
                       //如果正在播放
                       LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                       int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                       int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();

                       if (firstVisibleItemPosition > videoPlayer.getPosition() || lastVisibleItemPosition < videoPlayer.getPosition()) {
                           //如果第一个可见的条目位置大于当前播放videoPlayer的位置
                           //或最后一个可见的条目位置小于当前播放videoPlayer的位置，释放资源
                           JCVideoPlayer.releaseAllVideos();
                       }
                   }
               }
           }
       });
//        mRefreshLayout.setDisableContentWhenLoading(true); 
//        mRefreshLayout.setDisableContentWhenRefresh(true);
//        mRefreshLayout.setDragRate(0.6f);
//        mRefreshLayout.setEnableAutoLoadmore(true);
//        mRefreshLayout.setEnableLoadmore(true);
//        mRefreshLayout.setEnableLoadmoreWhenContentNotFull(true);
//        mRefreshLayout.setEnableNestedScroll(true);
//        mRefreshLayout.setEnableOverScrollBounce(true);
//        mRefreshLayout.setEnablePureScrollMode(true);
//        mRefreshLayout.setEnableScrollContentWhenLoaded(true);
//        mRefreshLayout.setEnableRefresh(true);
//        mRefreshLayout.setReboundDuration(2);
//        mRefreshLayout.setReboundInterpolator(null);


//       
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
//        new Handler().postDelayed(() -> getPresenter().requestNewsList(mChannelCode, mPage), 3000);
        getPresenter().requestNewsList(mChannelCode, mPage);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        mPage++;
        getPresenter().requestNewsList(mChannelCode, mPage);
    }

    @Override
    public void showError(String msg) {
        super.showError(msg);
        mRefreshLayout.finishRefresh();
        getStateView().showRetry();
        mTipView.show(msg);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        News news = mAdapter.getData().get(position);
        String itemId = news.item_id;
        StringBuffer urlSb = new StringBuffer("http://m.toutiao.com/i");
        urlSb.append(itemId).append("/info/");
        String url = urlSb.toString();//http://m.toutiao.com/i6412427713050575361/info/
        Intent intent;
        if (news.has_video) {
            //视频
            intent = new Intent(getActivity(), VideoDetailActivity.class);
            if (JCVideoPlayerManager.getCurrentJcvd() != null) {
                JCVideoPlayerStandard videoPlayer = (JCVideoPlayerStandard) JCVideoPlayerManager.getCurrentJcvd();
                //传递进度
                int progress = JCMediaManager.instance().mediaPlayer.getCurrentPosition();
                if (progress != 0) {
                    intent.putExtra(VideoDetailActivity.PROGRESS, progress);
                }
            }
        } else {
            //非视频新闻
            if (news.article_type == 1) {
                //如果article_type为1，则是使用WebViewActivity打开
                intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra(WebViewActivity.URL, news.article_url);
                intent.putExtra(WebViewActivity.USER,news.user_info);
                startActivity(intent);
                return;
            }
            //其他新闻
            intent = new Intent(getActivity(), NewsDetailActivity.class);
        }

        intent.putExtra(BaseNewsDetailActivity.CHANNEL_CODE, mChannelCode);
        intent.putExtra(BaseNewsDetailActivity.POSITION, position);

        intent.putExtra(BaseNewsDetailActivity.DETAIL_URL, url);
        intent.putExtra(BaseNewsDetailActivity.GROUP_ID, news.group_id);
        intent.putExtra(BaseNewsDetailActivity.ITEM_ID, itemId);

        startActivity(intent);
    }
}
