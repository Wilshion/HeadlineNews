package com.wilshion.headlinenews.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wilshion.headlinenews.R;
import com.wilshion.headlinenews.model.bean.NewsDetail;
import com.wilshion.utillib.util.ResouceUtil;
import com.wilshion.utillib.util.StatusBarUtil;

/**
 * Created by Wilshion on 2017/9/14 15:31.
 * [description : ]
 * [version : 1.0]
 */
public class NewsDetailActivity extends BaseNewsDetailActivity  {
    private LinearLayoutManager mLayoutManager;
    @Override
    protected int getLayoutId() {
        return R.layout.news_detail_activity;
    }

    @Override
    protected void initViews() {
        super.initViews();
        if (mCommentRv == null){
            throw new NullPointerException("recyclerview 为空了，请检查");
        }
        StatusBarUtil.setPaddingSmart(this, findViewById(R.id.id_statusbar));
        StatusBarUtil.immersive(this, ResouceUtil.getColor(R.color.gray));

        findViewById(R.id.id_back_iv).setOnClickListener(this);
        findViewById(R.id.id_comment_count_iv).setOnClickListener(this);
        findViewById(R.id.id_collect_iv).setOnClickListener(this);
        findViewById(R.id.id_share_iv).setOnClickListener(this);

        mLayoutManager = (LinearLayoutManager) mCommentRv.getLayoutManager();
        mCommentRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int firstPos = mLayoutManager.findFirstVisibleItemPosition();
            }
        }); 
    }

    @Override
    public void showNewsDetail(NewsDetail newsDetail) {
        mNewsDetailHeader.setNewsDetail(newsDetail);
    }
}
