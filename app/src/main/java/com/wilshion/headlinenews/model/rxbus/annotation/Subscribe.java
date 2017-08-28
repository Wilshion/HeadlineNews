package com.wilshion.headlinenews.model.rxbus.annotation;

import android.support.annotation.NonNull;

import com.wilshion.headlinenews.model.rxbus.event.EventThread;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Wilshion on 2017/8/8 11:56.
 * [description : ]
 * [version : 1.0]
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Subscribe {
    @NonNull int tag();

    EventThread thread() default EventThread.MAIN_THREAD;
}
