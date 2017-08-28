package com.wilshion.headlinenews.ui.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Wilshion on 2017/8/14 15:36.
 * [description : ]
 * [version : 1.0]
 */
public class CommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    
    private Context mContext;
    private List<T> mData;
    private int mItemId;

    public CommonAdapter(Context context, List<T> data) {
        mContext = context;
        mData = data;
    }

    public CommonAdapter(Context context, List<T> data, int itemId) {
        mContext = context;
        mData = data;
        mItemId = itemId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (mItemId == 0){
            
        }
        return super.getItemViewType(position);
    }
}
