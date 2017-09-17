package com.wilshion.headlinenews.model.http.response;

/**
 * Created by Wilshion on 2017/9/4 09:41.
 * [description : ]
 * [version : 1.0]
 */
public class ResultResponse<T> {
    public String has_more;
    public String message;
    public String success;
    public T data;
    
    
}
