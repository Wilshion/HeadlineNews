package com.wilshion.headlinenews.model.http.response;

import com.wilshion.headlinenews.model.bean.Comment;

import java.util.List;

/**
 * Created by Wilshion on 2017/9/14 17:09.
 * [description : ]
 * [version : 1.0]
 */
public class CommentResponse {
    public int total_number;
    public boolean has_more;
    public List<CommentData> data;
    
    public static final class CommentData{
        public Comment comment;
    }
}
