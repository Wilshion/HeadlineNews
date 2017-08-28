package com.wilshion.headlinenews.model.rxbus.pojo;

/**
 * Created by Wilshion on 2017/8/8 11:53.
 * [description : ]
 * [version : 1.0]
 */
public class Msg {
    public int code;
    public Object object;

    public Msg(int code, Object object){
        this.code = code;
        this.object = object;
    }
}
