package com.wilshion.headlinenews.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.wilshion.headlinenews.R;
import com.wilshion.utillib.util.ResouceUtil;
import com.wilshion.utillib.util.SizeUtils;

/**
 * Created by Wilshion on 2017/9/6 17:01.
 * [description : ]
 * [version : 1.0]
 */
public class UISearchView extends AppCompatEditText implements ValueAnimator.AnimatorUpdateListener, View.OnFocusChangeListener, TextWatcher {
    private Drawable mClearDrawable;
    private boolean hasFoucs;

    public UISearchView(Context context) {
        this(context, null);
    }

    public UISearchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UISearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mClearDrawable = getCompoundDrawables()[2];
        if (mClearDrawable == null) {
            mClearDrawable = ResouceUtil.getDrawable(R.drawable.delete_40);
        }
        mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
        // 默认设置隐藏图标
        setClearIconVisible(false);
        // 设置焦点改变的监听
        setOnFocusChangeListener(this);
        // 设置输入框里面内容发生改变的监听
        addTextChangedListener(this);
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAnimation();
    }


    private void startAnimation() {
        int endPaddingLeft = getPaddingLeft();
        int startPaddingLeft = endPaddingLeft + SizeUtils.dp2px(20);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(startPaddingLeft, endPaddingLeft)
                .setDuration(500);
        valueAnimator.addUpdateListener(this);
        valueAnimator.start();
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        int paddingLeft = (int) animation.getAnimatedValue();
        setPadding(paddingLeft, getPaddingTop(), getPaddingRight(), getPaddingBottom());
    }

    /**
     * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件 当我们按下的位置 在 EditText的宽度 -
     * 图标到控件右边的间距 - 图标的宽度 和 EditText的宽度 - 图标到控件右边的间距之间我们就算点击了图标，竖直方向就没有考虑
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {
                boolean touchable = event.getX() > 
                        (getWidth() - getTotalPaddingRight())&& 
                        (event.getX() < ((getWidth() - getPaddingRight())));
                if (touchable) {
                    this.setText("");
                }
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * 当ClearEditText焦点发生变化的时候，判断里面字符串长度设置清除图标的显示与隐藏
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFoucs = hasFocus;
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }
    }

    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     *
     * @param visible
     */
    protected void setClearIconVisible(boolean visible) {
        Drawable right = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0],getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (hasFoucs) {
            setClearIconVisible(s.length() > 0);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
