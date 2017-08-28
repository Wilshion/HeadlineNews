package com.wilshion.headlinenews.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wilshion.headlinenews.R;

/**
 * Created by Wilshion on 2017/8/8 14:43.
 * [description : ]
 * [version : 1.0]
 */
public class UINavigationBar extends Toolbar implements View.OnClickListener {
    private RelativeLayout mLeftLayout;
    private ImageView mLeftIv;
    private TextView mTitleTv;
    private LinearLayout mRightLayout;
    private ImageView mRightIv;
    private TextView mRightTv;

    private OnNavigationBarClickListener mOnNavigationBarClickListener;

    public UINavigationBar(Context context) {
        this(context, null);
    }

    public UINavigationBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UINavigationBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.view_navigation_bar, this);
        mLeftLayout = (RelativeLayout) findViewById(R.id.id_left_rl);
        mLeftIv = (ImageView) findViewById(R.id.id_left_iv);
        mTitleTv = (TextView) findViewById(R.id.id_title_tv);
        mRightLayout = (LinearLayout) findViewById(R.id.id_right_rl);
        mRightIv = (ImageView) findViewById(R.id.id_right_iv);
        mRightTv = (TextView) findViewById(R.id.id_right_tv);
    }

    public void setNavBarLeftImg(int resIcon) {
        mLeftIv.setImageResource(resIcon);
        mLeftLayout.setOnClickListener(this);
    }

    public void setNavBarLeftImg(String imgUrl) {
        mLeftLayout.setOnClickListener(this);
    }

    public void setNavBarTitle(String title) {
        mTitleTv.setText(title);
        mTitleTv.setOnClickListener(this);
    }

    public void setNavBarRightText(String text) {
        if (mRightLayout.getVisibility() == GONE)
            mRightLayout.setVisibility(VISIBLE);
        mRightTv.setText(text);
        mRightLayout.setOnClickListener(this);
    }

    public void setNavBarRightImg(int resIcon) {
        if (mRightLayout.getVisibility() == GONE)
            mRightLayout.setVisibility(VISIBLE);
        mRightIv.setImageResource(resIcon);

        mRightLayout.setOnClickListener(this);
    }

    public void setNavBarRightImg(String imgUrl) {
        mRightLayout.setOnClickListener(this);
    }

    public void setOnNavigationBarClickListener(OnNavigationBarClickListener onNavigationBarClickListener) {
        mOnNavigationBarClickListener = onNavigationBarClickListener;
    }

    public interface OnNavigationBarClickListener {
        void onNavigationBarLeftClicked();

        void onNavigationBarTitleClicked();

        void onNavigationBarRightClicked();
    }

    @Override
    public void onClick(View v) {
        if (mOnNavigationBarClickListener == null)
            return;
        int id = v.getId();
        switch (id) {
            case R.id.id_left_rl:
                mOnNavigationBarClickListener.onNavigationBarLeftClicked();
                break;
            case R.id.id_title_tv:
                mOnNavigationBarClickListener.onNavigationBarTitleClicked();
                break;
            case R.id.id_right_rl:
                mOnNavigationBarClickListener.onNavigationBarRightClicked();
                break;
        }
    }
}
