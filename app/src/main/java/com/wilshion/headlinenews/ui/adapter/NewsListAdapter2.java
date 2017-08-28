package com.wilshion.headlinenews.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.wilshion.headlinenews.R;
import com.wilshion.headlinenews.model.bean.News;
import com.wilshion.utillib.util.EmptyUtils;

import java.util.List;

/**
 * Created by Wilshion on 2017/8/14 16:07.
 * [description : ]
 * [version : 1.0]
 */
public class NewsListAdapter2 extends BaseQuickAdapter<News, BaseViewHolder> {
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
    
    private String mChannelCode;

    public NewsListAdapter2(Context context, String channelCode,@Nullable List<News> data, boolean isVideoList) {
        super(data);
        mContext = context;
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
                    if (!news.has_image){
                        return ITEM_TYPE_TEXT;
                    }else {
                        if (EmptyUtils.isEmpty(news.image_list)){
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
        int itemViewType = helper.getItemViewType();
//        switch (itemViewType) {
//            case ITEM_TYPE_TEXT:
                showPureText(helper, item);
//                break;
//            case ITEM_TYPE_LEFT_TEXT_RIGHT_IMG:
//                showTextWithRightImg(helper, item);
//                break;
//            case ITEM_TYPE_THREE_SMALL_IMG:
//                showThreeSmallImg(helper, item);
//                break;
//            case ITEM_TYPE_BIG_IMG:
//                showBigImg(helper, item);
//                break;
//        }
    }
    
    public void updateData(List<News> data){
        this.mData = data;
        notifyDataSetChanged();
    }

  

    private void showPureText(BaseViewHolder helper, News item) {
        helper.setText(R.id.id_title_tv,item.title);
    }

   
    private void showThreeSmallImg(BaseViewHolder helper, News item) {
        helper.setText(R.id.id_title_tv,item.title);
    }

    private void showTextWithRightImg(BaseViewHolder helper, News item) {
        helper.setText(R.id.id_title_tv,item.title);
    }

    private void showBigImg(BaseViewHolder helper, News item) {
        helper.setText(R.id.id_title_tv,item.title);
    }

}
