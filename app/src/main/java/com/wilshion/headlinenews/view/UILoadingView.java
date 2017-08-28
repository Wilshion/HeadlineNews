package com.wilshion.headlinenews.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.wilshion.headlinenews.R;
import com.wilshion.utillib.util.LogUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wilshion on 2017/8/27 17:09.
 * [description : ]
 * [version : 1.0]
 */
public class UILoadingView extends FrameLayout {
    private ImageView mLoadIv1;
    private ImageView mLoadIv2;
    private ImageView mLoadIv3;
    private ImageView mLoadIv4;
    private AnimatorSet mAnimatorSet;

    public UILoadingView(Context context) {
        this(context, null);
    }

    public UILoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UILoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
    }

    private void initView() {
        View.inflate(getContext(), R.layout.loading_flash_view, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LogUtils.e("onFinishInflate");
        mLoadIv1 = (ImageView) findViewById(R.id.id_load1_iv);
        mLoadIv2 = (ImageView) findViewById(R.id.id_load2_iv);
        mLoadIv3 = (ImageView) findViewById(R.id.id_load3_iv);
        mLoadIv4 = (ImageView) findViewById(R.id.id_load4_iv);
        
        show();
    }

    public void show() {
      
        if (getVisibility() != VISIBLE)
            return;
        if (mAnimatorSet == null)
            initAnimatorSet();
        if (mAnimatorSet.isRunning() || mAnimatorSet.isStarted())
            return;
        mAnimatorSet.start();
    }


    public void hide() {
        LogUtils.e("hide");
        if (mAnimatorSet == null)
            return;
        if ((!mAnimatorSet.isRunning()) && (!mAnimatorSet.isStarted()))
            return;
        mAnimatorSet.removeAllListeners();
        mAnimatorSet.cancel();
        mAnimatorSet.end();
    }

    private void initAnimatorSet() {
        mAnimatorSet = new AnimatorSet();

        List<ImageView> imageViewList = Arrays.asList(mLoadIv1, mLoadIv2, mLoadIv3, mLoadIv4);
        List<Animator> animatorList = new ArrayList<>();

        for (int i = 0; i < imageViewList.size(); i++) {
            ImageView imgView = imageViewList.get(i);
            ObjectAnimator animator = ObjectAnimator.ofFloat(imgView, "alpha", new float[]{1.0F, 0.5F});
            animator.setDuration(500);
            animator.setStartDelay(100 * i);
            animator.setRepeatCount(-1);
            animator.setRepeatMode(ValueAnimator.REVERSE);
            animatorList.add(animator);


//            ObjectAnimator loadAnimator = ObjectAnimator.ofFloat(imageViewList.get(i),
//                    "alpha", new float[]{1.0F, 0.5F}).setDuration(500L);
//            loadAnimator.setStartDelay(100 * i);
//            loadAnimator.setRepeatMode(ObjectAnimator.REVERSE);
//            loadAnimator.setRepeatCount(-1);
//            animatorList.add(loadAnimator);
        }

        mAnimatorSet.playTogether(animatorList);
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (visibility == GONE)
            hide();
        else show();
    }
}
