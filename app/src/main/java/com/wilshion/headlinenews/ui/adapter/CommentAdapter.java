package com.wilshion.headlinenews.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunfusheng.glideimageview.progress.GlideApp;
import com.wilshion.headlinenews.R;
import com.wilshion.headlinenews.model.bean.Comment;
import com.wilshion.headlinenews.model.http.response.CommentResponse;
import com.wilshion.utillib.util.TimeUtils;

import java.util.List;

/**
 * Created by Wilshion on 2017/9/14 17:04.
 * [description : ]
 * [version : 1.0]
 */
public class CommentAdapter extends BaseQuickAdapter<CommentResponse.CommentData, BaseViewHolder> {
    public CommentAdapter(@LayoutRes int layoutResId, @Nullable List<CommentResponse.CommentData> data) {
        super(layoutResId, data);

    }


    @Override
    protected void convert(BaseViewHolder helper, CommentResponse.CommentData item) {
        Comment comment = item.comment;
        GlideApp.with(helper.itemView.getContext()).load(comment.user_profile_image_url).into((ImageView) helper.getView(R.id.id_avatar_iv));
        helper.setText(R.id.id_name_tv, comment.user_name)
                .setText(R.id.id_like_count_tv, String.valueOf(comment.digg_count))
                .setText(R.id.id_content_tv, comment.text)
                .setText(R.id.id_time_tv, TimeUtils.getFriendlyTimeSpanByNow(comment.create_time * 1000));
    }
}
