package com.wilshion.headlinenews.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wilshion.headlinenews.R;
import com.wilshion.headlinenews.model.bean.News;

import java.util.List;

/**
 * Created by Wilshion on 2017/8/13 15:10.
 * [description : ]
 * [version : 1.0]
 */
public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {
    /**
     * 纯文本
     */
    private final int ITEM_TYPE_TEXT = 1;
    /**
     * 左文字、右小图(视频)
     */
    private final int ITEM_TYPE_TEXT_SMALL_IMG = 2;
    /**
     * 上面文字，下面三张小图
     */
    private final int ITEM_TYPE_SMALL_IMG = 3;
    /**
     * 上面文字，下面一张大图、或者大视频
     */
    private final int ITEM_TYPE_BIG_IMG = 4;


    private Context mContext;
    private List<News> mNewsList;

    public NewsListAdapter(Context context, String channelCode, List<News> newsList,boolean isVideo) {
        mContext = context;
        mNewsList = newsList;
    }

    @Override
    public int getItemViewType(int position) {
        News news = mNewsList.get(position);
        
        return super.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.item_news_list, null);
        return new ViewHolder(contentView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        News newsBean = mNewsList.get(position);
        holder.mTitleTv.setText(newsBean.title);
    }

    @Override
    public int getItemCount() {
        return mNewsList == null ? 0 : mNewsList.size();
    }

    public void updateData(List<News> data) {
        mNewsList = data;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitleTv;

        public ViewHolder(View itemView) {
            super(itemView);

            mTitleTv = (TextView) itemView.findViewById(R.id.id_title_tv);
        }
    }
}
