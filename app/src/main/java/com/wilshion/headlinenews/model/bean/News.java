package com.wilshion.headlinenews.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Wilshion on 2017/8/13 16:35.
 * [description : ]
 * [version : 1.0]
 */
public class News {

    public int read_count;
    public String media_name;
    public int ban_comment;
    @SerializedName("abstract")
    public String abstractX;
    public int article_type;
    public String tag;
    public int has_m3u8_video;
    public String keywords;
    public String rid;
    public boolean show_portrait_article;
    public int user_verified;
    public int aggr_type;
    public int cell_type;
    public int article_sub_type;
    public int bury_count;
    public String title;
    public int ignore_web_transform;
    public int source_icon_style;
    public int tip;
    public int hot;
    public String share_url;
    public int has_mp4_video;
    public String source;
    public int comment_count;
    public String article_url;
    public int share_count;
    public int publish_time;
    public int gallary_image_count;
    public int cell_layout_style;
    public long tag_id;
    public int video_style;
    public String verified_content;
    public String display_url;
    public String item_id;
    public boolean is_subject;
    public boolean show_portrait;
    public int repin_count;
    public int cell_flag;
    public UserEntity user_info;
    public String source_open_url;
    public int level;
    public int like_count;
    public int digg_count;
    public long behot_time;
    public long cursor;
    public String url;
    public int preload_web;
    public int user_repin;
    public boolean has_image;
    public int item_version;
    public boolean has_video;
    public int video_duration;
    public VideoEntity video_detail_info;
    public String group_id;
    public ImageEntity middle_image;
    public List<ImageEntity> image_list;
    public List<ImageEntity> large_image_list;

}
