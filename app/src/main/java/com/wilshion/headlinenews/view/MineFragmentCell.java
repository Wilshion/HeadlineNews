package com.wilshion.headlinenews.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.roundview.RoundTextView;
import com.wilshion.headlinenews.R;

/**
 * Created by Wilshion on 2017/9/11 19:53.
 * [description : ]
 * [version : 1.0]
 */
public class MineFragmentCell extends LinearLayout {

    private TextView mTitleTv, mSubTitleTv;
    private RoundTextView mNotificationTv;

    public MineFragmentCell(Context context) {
        this(context, null);
    }

    public MineFragmentCell(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MineFragmentCell(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initCtrls(context);
        initAttrs(context, attrs);
    }

    private void initCtrls(Context context) {
        View.inflate(context, R.layout.item_mine_fragment, this);
        mTitleTv = (TextView) findViewById(R.id.id_title_tv);
        mSubTitleTv = (TextView) findViewById(R.id.id_subtitle_tv);
        mNotificationTv = (RoundTextView) findViewById(R.id.id_notification_tv);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MineFragmentCell);
        String mTitle = ta.getString(R.styleable.MineFragmentCell_cell_title);
        String mSubTitle = ta.getString(R.styleable.MineFragmentCell_cell_subtitle);
        int mNotificationNum = ta.getInteger(R.styleable.MineFragmentCell_cell_notification_num, 0);
        ta.recycle();

        if (!TextUtils.isEmpty(mTitle)){
            mTitleTv.setVisibility(VISIBLE);
            mTitleTv.setText(mTitle);
        }
        if (!TextUtils.isEmpty(mSubTitle)){
            mSubTitleTv.setVisibility(VISIBLE);
            mSubTitleTv.setText(mSubTitle);
        }
//        if (mNotificationNum > 0) {
            mNotificationTv.setVisibility(VISIBLE);
            mNotificationTv.setText(mNotificationNum + "");
//        }

    }
}
