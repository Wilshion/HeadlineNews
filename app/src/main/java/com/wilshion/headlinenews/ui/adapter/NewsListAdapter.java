package com.wilshion.headlinenews.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.sunfusheng.glideimageview.progress.GlideApp;
import com.wilshion.headlinenews.R;
import com.wilshion.headlinenews.model.bean.News;
import com.wilshion.headlinenews.util.VideoPathDecoder;
import com.wilshion.utillib.util.EmptyUtils;
import com.wilshion.utillib.util.LogUtils;
import com.wilshion.utillib.util.ResouceUtil;
import com.wilshion.utillib.util.TimeUtils;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by Wilshion on 2017/8/14 16:07.
 * [description : ]
 * [version : 1.0]
 */
public class NewsListAdapter extends BaseQuickAdapter<News, BaseViewHolder> {
    /**
     * 纯文本
     */
    private final int ITEM_TYPE_TEXT = 1;
    /**
     * 左文字、右小图(视频)
     */
    private final int ITEM_TYPE_LEFT_TEXT_RIGHT_IMG = 2;
    /**
     * 上面文字，下面三张小图
     */
    private final int ITEM_TYPE_THREE_SMALL_IMG = 3;
    /**
     * 上面文字，下面一张大图、或者大视频
     */
    private final int ITEM_TYPE_BIG_IMG = 4;

    private final int ITEM_TYPE_VIDEO = 5;

    private Context mContext;

    private boolean mIsVideo;

    private String mChannelCode;

    public NewsListAdapter(Context context, String channelCode, @Nullable List<News> data, boolean isVideoList) {
        super(data);
        mContext = context;
        mIsVideo = isVideoList;
        mChannelCode = channelCode;
        setMultiTypeDelegate(new MultiTypeDelegate<News>() {
            @Override
            protected int getItemType(News news) {
                if (isVideoList)
                    return ITEM_TYPE_VIDEO;
                if (news.has_video) {
                    if (news.video_style == 0) {
                        //右侧视频
                        String midImgUrl = news.middle_image.url;
                        if (EmptyUtils.isEmpty(midImgUrl)) {
                            return ITEM_TYPE_TEXT;
                        }
                        return ITEM_TYPE_LEFT_TEXT_RIGHT_IMG;
                    } else if (news.video_style == 2) {
                        return ITEM_TYPE_BIG_IMG;
                    }
                } else {
                    if (!news.has_image) {
                        return ITEM_TYPE_TEXT;
                    } else {
                        if (EmptyUtils.isEmpty(news.image_list)) {
                            return ITEM_TYPE_LEFT_TEXT_RIGHT_IMG;
                        }
                        if (news.gallary_image_count == 3) {
                            //图片数为3，则为三图
                            return ITEM_TYPE_THREE_SMALL_IMG;
                        }
                        //中间大图，右下角显示图数
                        return ITEM_TYPE_BIG_IMG;
                    }
                }
                return ITEM_TYPE_TEXT;
            }
        });
        getMultiTypeDelegate()
                .registerItemType(ITEM_TYPE_TEXT, R.layout.item_news_text)
                .registerItemType(ITEM_TYPE_LEFT_TEXT_RIGHT_IMG, R.layout.item_news_text_small_img)
                .registerItemType(ITEM_TYPE_THREE_SMALL_IMG, R.layout.item_news_small_img)
                .registerItemType(ITEM_TYPE_BIG_IMG, R.layout.item_news_big_img)
                .registerItemType(ITEM_TYPE_VIDEO, R.layout.item_news_video);
    }

    @Override
    protected void convert(BaseViewHolder helper, News item) {
        if (!mIsVideo) {
            //如果不是视频列表

            helper.setText(R.id.id_title_tv, item.title)
                    .setText(R.id.id_author_tv, item.source)
                    .setText(R.id.id_comment_count_tv, item.comment_count + "评论")
                    .setText(R.id.id_time_tv, TimeUtils.getFriendlyTimeSpanByNow(item.behot_time * 1000));
//                    .setText(R.id.id_time_tv, TimeUtils.getFriendlyTimeSpanByNow(System.currentTimeMillis() - 1000 * 60 * 5));
        }
        int itemViewType = helper.getItemViewType();
        switch (itemViewType) {
            case ITEM_TYPE_TEXT:
                showPureText(helper, item);
                break;
            case ITEM_TYPE_LEFT_TEXT_RIGHT_IMG:
                showTextWithRightImg(helper, item);
                break;
            case ITEM_TYPE_THREE_SMALL_IMG:
                showThreeSmallImg(helper, item);
                break;
            case ITEM_TYPE_BIG_IMG:
                showBigImg(helper, item);
                break;
            case ITEM_TYPE_VIDEO:
                showVideo(helper, item);
                return;//视频列表布局没有下面的设置标签的操作，直接return
        }

        int position = helper.getAdapterPosition();
        String[] channelCodes = ResouceUtil.getStringArray(R.array.channel_code);
        boolean isTop = position == 0 && mChannelCode.equals(channelCodes[0]); //属于置顶
        boolean isHot = item.hot == 1;//属于热点新闻
        boolean isAD = !TextUtils.isEmpty(item.tag) ? item.tag.equals("ad") : false;//属于广告新闻
        boolean isMovie = !TextUtils.isEmpty(item.tag) ? item.tag.equals("video_movie") : false;//如果是影视
        helper.setVisible(R.id.id_type_tv, isTop || isHot || isAD);//如果是上面任意一个，显示标签
        helper.setVisible(R.id.id_comment_count_tv, !isAD);//如果是广告，则隐藏评论数
        String tag = "";
        if (isTop) {
            tag = "置顶";
            helper.setTextColor(R.id.id_type_tv, Color.parseColor("#F96B6B"));
        } else if (isHot) {
            tag = "热";
            helper.setTextColor(R.id.id_type_tv, Color.parseColor("#F96B6B"));
        } else if (isAD) {
            tag = "广告";
            helper.setTextColor(R.id.id_type_tv, Color.parseColor("#3091D8"));
        } else if (isMovie) {
            //如果是影视
            tag = "影视";
            helper.setTextColor(R.id.id_type_tv, Color.parseColor("#F96B6B"));
        }
        helper.setText(R.id.id_type_tv, tag);
    }


    public void updateData(List<News> data) {
        this.mData = data;
        notifyDataSetChanged();
    }


    private void showPureText(BaseViewHolder helper, News item) {
    }


    private void showThreeSmallImg(BaseViewHolder helper, News item) {
        GlideApp.with(mContext).load(item.image_list.get(0).url).into((ImageView) helper.getView(R.id.id_iv1));//右
        GlideApp.with(mContext).load(item.image_list.get(1).url).into((ImageView) helper.getView(R.id.id_iv2));//右
        GlideApp.with(mContext).load(item.image_list.get(2).url).into((ImageView) helper.getView(R.id.id_iv3));//右
    }

    private void showTextWithRightImg(BaseViewHolder helper, News item) {
        if (item.has_video) {
            helper.setVisible(R.id.id_bottom_right_fl, true);//显示时长
            helper.setText(R.id.id_bottom_right_tv, TimeUtils.secToTime(item.video_duration));//设置时长
        } else {
            if (item.has_image) {
                helper.setVisible(R.id.id_bottom_right_fl, true);//显示时长
                helper.setText(R.id.id_bottom_right_tv, item.image_list.size() + " 图");
            } else {
                helper.setVisible(R.id.id_bottom_right_fl, false);//隐藏时长
            }
        }
        GlideApp.with(mContext)
                .load(item.middle_image.url).into((ImageView) helper.getView(R.id.id_iv));//右
    }

    private void showBigImg(BaseViewHolder helper, News item) {
        //中间大图布局，判断是否有视频
        TextView tvBottomRight = helper.getView(R.id.id_info_tv);
        if (item.has_video) {
            helper.setVisible(R.id.id_play_iv, true);//显示播放按钮
            tvBottomRight.setCompoundDrawables(null, null, null, null);//去除TextView左侧图标
            helper.setText(R.id.id_info_tv, item.video_duration + "");//设置时长
            GlideApp.with(mContext)
                    .load(item.video_detail_info.detail_video_large_image.url)
                    .into((ImageView) helper.getView(R.id.id_iv));
        } else {
            helper.setVisible(R.id.id_play_iv, false);//隐藏播放按钮
            tvBottomRight.setCompoundDrawables(ResouceUtil.getDrawable(R.drawable.icon_picture_group), null, null, null);//TextView增加左侧图标
            helper.setText(R.id.id_info_tv, item.gallary_image_count + " 图");//设置时长
            GlideApp.with(mContext).load(item.image_list.get(0).url.replace("list/300x196", "large")).centerCrop()
                    .into((ImageView) helper.getView(R.id.id_iv));//中间图片使用image_list第一张
        }
    }

    private void showVideo(BaseViewHolder helper, News item) {
        int watchCount = item.video_detail_info.video_watch_count;
        String countUnit = "";
        if (watchCount > 10000) {
            watchCount = watchCount / 10000;
            countUnit = "万";
        }

        GlideApp.with(mContext).load(item.user_info.avatar_url).into((ImageView) helper.getView(R.id.id_avatar_iv));
        JCVideoPlayerStandard videoPlayer = helper.getView(R.id.id_video_view);
        GlideApp.with(mContext)
                .load(item.video_detail_info.detail_video_large_image.url)
                .fitCenter()
                .into(videoPlayer.thumbImageView);
        helper.setText(R.id.id_title_tv, item.title)
                .setText(R.id.id_play_count_tv, String.format("%s次播放", watchCount, countUnit))
                .setText(R.id.id_name_tv, item.user_info.name)
                .setText(R.id.id_duration_tv, TimeUtils.secToTime(item.video_duration));

        videoPlayer.setAllControlsVisible(GONE, GONE, VISIBLE, GONE, VISIBLE, VISIBLE, GONE);
        videoPlayer.tinyBackImageView.setVisibility(GONE);
        videoPlayer.titleTextView.setText("");
        videoPlayer.setOnVideoClickListener(() -> {
            LogUtils.e(" videoPlayer setOnVideoClickListener ");
            //点击播放
            helper.setVisible(R.id.id_duration_tv, false);//隐藏时长
            helper.setVisible(R.id.id_title_tv, false);//隐藏标题栏
            VideoPathDecoder decoder = new VideoPathDecoder() {
                @Override
                public void onSuccess(String url) {
                    LogUtils.i("Video url:" + url);
                    videoPlayer.setUp(url, JCVideoPlayer.SCREEN_LAYOUT_LIST, item.title);
                    videoPlayer.seekToInAdvance = item.video_detail_info.progress;
                    videoPlayer.startVideo();
                }

                @Override
                public void onDecodeError() {
                }
            };
            decoder.decodePath(item.url);
        });
    }
}
