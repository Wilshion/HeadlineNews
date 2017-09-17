package com.wilshion.headlinenews.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wilshion.headlinenews.R;
import com.wilshion.utillib.util.LogUtils;
import com.wilshion.utillib.util.ResouceUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Wilshion on 2017/8/13 12:47.
 * [description : 支持虚线的线条]
 * <p>
 * 1、默认是水平方向
 * 2、默认高度1dp
 * 3、默认颜色 灰色
 * </p>
 * <p>
 * 1、若设置了 android:background 属性，则不支持绘制 虚线
 * 2、若想绘制粗虚线，请设置 mThickness 属性，并利用 line_color 属性去设置线条颜色若你想修改线条颜色的时候
 * </p>
 * [version : 1.0]
 */
public class UILine extends View {
    private static final int ORIENTATION_HORIZONTAL = 0;
    private static final int ORIENTATION_VERTICAL = 1;

    @IntDef({ORIENTATION_HORIZONTAL, ORIENTATION_VERTICAL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface OrientationMode {
    }

    /**
     * 实线
     */
    private static final int LINE_MODE_REAL = 0;
    /**
     * 虚线
     */
    private static final int LINE_MODE_DOTTED = 1;

    @IntDef({LINE_MODE_REAL, LINE_MODE_DOTTED})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LineMode {

    }

    /**
     * 线条模式（实线、虚线）
     */
    private int mLineMode;
    /**
     * 方向
     */
    private int mOrientation;

    /**
     * 线条厚度（垂直方向就是宽度、水平方向就是高度）,只有当线条方向的反方向值（未指定，才有用，否则按照指定高度绘制线条
     * 例如，线条是水平方向的，此时指定了  android:layout_height="10dp" 则，此属性失效，线条的高度按照10dp 展示
     */
    private int mThickness;

    private int mColor;
    /**
     * 单个虚线的大小
     */
    private float mDottedSize = 20f;
    /**
     * 虚线之前的 间隔大小
     */
    private float mDottedSpacingSize = 10f;
    private Paint mPaint;

    private Path mPath;

    private String mTag = getClass().getSimpleName();

    public void setOrientation(@OrientationMode int orientation) {
        mOrientation = orientation;
    }

    public void setLineMode(@LineMode int lineMode) {
        mLineMode = lineMode;
    }

    public void setThickness(int thickness) {
        mThickness = thickness;
    }

    public UILine(Context context) {
        this(context, null);
    }

    public UILine(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UILine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
        initPaint();
    }


    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.UILine);
        mOrientation = a.getInt(R.styleable.UILine_ws_line_orientation, ORIENTATION_HORIZONTAL);
        mThickness = a.getDimensionPixelSize(R.styleable.UILine_ws_line_thickness, 1);
        mColor = a.getColor(R.styleable.UILine_ws_line_color, ResouceUtil.getColor(R.color.line_stoke));
        mLineMode = a.getInt(R.styleable.UILine_ws_line_mode, LINE_MODE_REAL);
        mDottedSize = a.getDimensionPixelOffset(R.styleable.UILine_ws_line_dotted_size, 20);
        mDottedSpacingSize = a.getDimensionPixelOffset(R.styleable.UILine_ws_line_dotted_spacing, 10);
        mTag = a.getString(R.styleable.UILine_ws_line_tag);
        a.recycle();
    }

    private void initPaint() {
        mPaint = new Paint();

        mPaint.setColor(mColor);
        mPaint.setStyle(Paint.Style.STROKE);
        /** 边界实际的位置是在边框的中间，所以都要减去边框宽度的一半，所以需要乘以2，才能得到想要的尺寸*/
        mPaint.setStrokeWidth(mThickness * 2);
        if (mLineMode == LINE_MODE_DOTTED) {
            /** 定义 虚线的一些尺寸属性 从0开始，偶数项代表 单个虚线的长度，第二个代表虚线间隔的大小，后面会依次循环*/
            float[] intervals = new float[]{mDottedSize, mDottedSpacingSize};
            /** 第二个参数是 偏移量,填0就够用了*/
            DashPathEffect dashPathEffect = new DashPathEffect(intervals, 0);
            mPaint.setPathEffect(dashPathEffect);
        }
        mPath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureSize(widthMeasureSpec, true);
        int height = measureSize(heightMeasureSpec, false);
        /** 设置线条的宽度 */
        mPaint.setStrokeWidth(mOrientation == ORIENTATION_HORIZONTAL ? height : width);
        if (mTag != null && mTag.contains("测试"))
            LogUtils.e("height = " + height);
        setMeasuredDimension(width, height);
    }

    private int measureSize(int spec, boolean isWidth) {
        int mode = MeasureSpec.getMode(spec);
        int size = MeasureSpec.getSize(spec);

        int result = size;
        switch (mode) {
            case MeasureSpec.EXACTLY:
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                if (isWidth) {
                    /** 计算宽度 */
                    if (mOrientation == ORIENTATION_HORIZONTAL) {

                    } else {
                        result = mThickness;
                    }
                } else {
                    /** 计算高度 */
                    if (mOrientation == ORIENTATION_HORIZONTAL) {
                        result = mThickness;
                    } else {

                    }
                }
                break;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Drawable background = getBackground();
        if (background == null) {

            mPath.reset();
            mPath.moveTo(0, 0);
            if (mOrientation == ORIENTATION_HORIZONTAL)
                mPath.lineTo(getMeasuredWidth(), 0);
            else mPath.lineTo(0, getMeasuredHeight());
            canvas.drawPath(mPath, mPaint);
        } else {
            background.draw(canvas);
        }
    }
}
