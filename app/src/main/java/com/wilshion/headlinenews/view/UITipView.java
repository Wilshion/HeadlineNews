package com.wilshion.headlinenews.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.wilshion.utillib.util.EmptyUtils;

/**
 * Created by Wilshion on 2017/8/13 19:47.
 * [description : 头条 刷新成功、失败 通知]
 * [version : 1.0]
 */
public class UITipView extends AppCompatTextView {
    private static final long STAY_TIME = 1000;

    public UITipView(Context context) {
        this(context, null);
    }

    public UITipView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UITipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setGravity(Gravity.CENTER);
        setVisibility(GONE);
    }

    public void show(String msg) {
        if (EmptyUtils.isEmpty(msg))
            msg = "";
        setText(msg);
        show();
    }

    private void show() {
        if (getVisibility() == VISIBLE)
            return;
        setVisibility(VISIBLE);
        startShowAnimation();
    }

    private void startShowAnimation() {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(this, "scaleX", 0, 1.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(this, "scaleY", 0, 1.0f);
        animatorSet.setDuration(500);
        animatorSet.play(scaleX).with(scaleY);
        animatorSet.start();
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mHandler.postDelayed(() -> dismiss(), STAY_TIME);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private Handler mHandler = new Handler();


    private void dismiss() {
        Animation translateY = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF
                , 0.0f, Animation.RELATIVE_TO_SELF, -1.0f);
        translateY.setDuration(500);
        translateY.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                setVisibility(GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        startAnimation(translateY);
    }

   
}
