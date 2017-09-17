package com.wilshion.headlinenews.view.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

import com.wilshion.headlinenews.R;
import com.wilshion.utillib.util.ResouceUtil;
import com.wilshion.utillib.util.SizeUtils;

/**
 * Created by Wilshion on 2017/9/4 18:23.
 * [description : 支持 分割线、布局方式的 recyclerView ]
 * [version : 1.0]
 */
public class UIRecyclerView extends RecyclerView {


    /**
     * 列数
     */
    private int mNumColums ;

    private int mDividerWidth;

    private int mDividerColor;

    private int mDividerMarginLeft, mDividerMarginRight;

    private Drawable mDividerDrawable;

    private LayoutManager mLayoutManager;

    private int mOritation;

    /**
     * 是否是 瀑布流布局
     */
    private boolean isStaggerLayout;

    public UIRecyclerView(Context context) {
        this(context, null);
    }

    public UIRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UIRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.UIRecyclerView);
        mDividerColor = ta.getColor(R.styleable.UIRecyclerView_ws_divider_color, ResouceUtil.getColor(R.color.gray));
        mDividerDrawable = ta.getDrawable(R.styleable.UIRecyclerView_ws_divider_bg);
        mDividerWidth = ta.getDimensionPixelSize(R.styleable.UIRecyclerView_ws_divider_width, SizeUtils.dp2px(1));
        mDividerMarginLeft = ta.getDimensionPixelOffset(R.styleable.UIRecyclerView_ws_divider_margin_left, 0);
        mDividerMarginRight = ta.getDimensionPixelOffset(R.styleable.UIRecyclerView_ws_divider_margin_right, 0);
        mOritation = ta.getInt(R.styleable.UIRecyclerView_ws_oritation, OrientationHelper.VERTICAL);
        isStaggerLayout = ta.getBoolean(R.styleable.UIRecyclerView_ws_layout_stagger, false);
        mNumColums = ta.getInt(R.styleable.UIRecyclerView_ws_num_colums, 1);

        setupValues();
    }

    private void setupValues() {
        if (mDividerWidth > 0) {
            setupDivider();
        }

        setupLayoutManager();


    }

    private void setupLayoutManager() {
        if (isStaggerLayout) {
            mLayoutManager = new StaggeredGridLayoutManager(mNumColums, mOritation);
        } else {
            if (mOritation == LinearLayoutManager.HORIZONTAL) {
                mLayoutManager = new GridLayoutManager(getContext(), mNumColums, mOritation, false);
            } else {
                mLayoutManager = new GridLayoutManager(getContext(), mNumColums);
            }
        }
        setLayoutManager(mLayoutManager);
    }

    private void setupDivider() {
        UIDivider divider = new UIDivider(getContext(), mOritation, mDividerColor, mDividerDrawable,
                mDividerWidth, mDividerMarginLeft, mDividerMarginRight);
        addItemDecoration(divider);
    }
}
