package com.wilshion.headlinenews.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wilshion.headlinenews.R;
import com.wilshion.utillib.util.ResouceUtil;
import com.wilshion.utillib.util.SizeUtils;

/**
 * Created by Wilshion on 2017/8/9 23:03.
 * [description : 角标  用于显示通知数量]
 * <p>
 * 你可以在xml中或者代码中创建它用来显示通知数量
 * 1-1 在 xml 中初始化此控件
 * 2-1 在 代码中初始化此控件
 * </p>
 *
 * @see com.wilshion.headlinenews.R.styleable#UIBadgeView_ws_backgroud_drawable        背景图资源id，不设置默认显示 背景颜色
 * @see com.wilshion.headlinenews.R.styleable#UIBadgeView_ws_background_color          背景色 id，默认显示 R.color.colorAccent
 * @see com.wilshion.headlinenews.R.styleable#UIBadgeView_ws_cornors                   圆角半径，默认控件高度一半（美观性较好，建议使用默认值）
 * @see com.wilshion.headlinenews.R.styleable#UIBadgeView_ws_padding_horizontal        水平方向的 padding，默认3dp
 * @see com.wilshion.headlinenews.R.styleable#UIBadgeView_ws_padding_vertical          垂直方向的 padding，默认1dp
 * @see com.wilshion.headlinenews.R.styleable#UIBadgeView_ws_text_color                字体颜色，默认白色
 * @see com.wilshion.headlinenews.R.styleable#UIBadgeView_ws_text_size                 字体大小，默认12sp
 * @see com.wilshion.headlinenews.R.styleable#UIBadgeView_ws_count                     当前显示数量
 * @see com.wilshion.headlinenews.R.styleable#UIBadgeView_ws_max_visible_count         最大支持显示数量，默认100（超过此值，将显示最大值+，后面省略）
 * [version : 1.0]
 */
public class UIBadgeView extends View {
    private final String TAG = getClass().getSimpleName();
    private final int DEFAULT_COLOR_BG = ResouceUtil.getColor(R.color.colorAccent);
    /**
     * 默认 文字颜色
     */
    private final int DEFAULT_COLOR_TEXT = ResouceUtil.getColor(R.color.white);
    /**
     * 默认 字体大小 单位 px
     */
    private final int DEFAULT_TEXT_SIZE = SizeUtils.sp2px(10);

    private final int DEFAULT_MAX_VISIBLE_COUNT = 99;
    private final int DEFAULT_PADDING_HORIZONTAL = SizeUtils.dp2px(3);
    private final int DEFAULT_PADDING_VERTICAL = SizeUtils.dp2px(1);


    private Paint mPaintBg;
    private Paint mPaintTv;

    /**
     * 字体大小，单位 px
     */
    private int mTextSize;

    /**
     * 背景资源，如果设置了这个值，将不再绘制默认背景
     */
    private int mBgDrawableRes = -1;

    /**
     * 背景颜色资源id，如果设置了  mBgDrawableRes ，则不起作用
     */
    private int mBgColorRes;
    /**
     * 默认显示 白色
     */
    private int mTextColor;
    /**
     * 水平方向上的内边距 只有水平和垂直方向的 内间距是因为，我觉得这个角标为了美观，还是对称好看点吧。。。
     */
    private int mPaddingH;
    /**
     * 垂直方向上的内边距
     */
    private int mPaddingV;
    /**
     * 圆角半径 单位 dp(若不指定，则取高度的一半作为半径,建议采用默认值，因为我觉得美观一点)
     */
    private int mCornors;
    /**
     * 当前显示数量，默认最大显示99，超过99以 99+ 显示
     */
    private int mCount;
    /**
     * 支持最大显示 数字
     * 例如，设置为99，则超过99的 数量最终会以 99+ 显示
     */
    private int mMaxVisibleCount;

    private String mText;

    public UIBadgeView(Context context) {
        this(context, null);
    }

    public UIBadgeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UIBadgeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        initPaints();
    }

    private void initAttrs(AttributeSet attributeSet) {
        TypedArray a = getContext().obtainStyledAttributes(attributeSet, R.styleable.UIBadgeView);
        mBgDrawableRes = a.getResourceId(R.styleable.UIBadgeView_ws_backgroud_drawable, -1);
        mBgColorRes = a.getColor(R.styleable.UIBadgeView_ws_background_color, DEFAULT_COLOR_BG);
        mTextColor = a.getColor(R.styleable.UIBadgeView_ws_text_color, DEFAULT_COLOR_TEXT);
        mTextSize = a.getDimensionPixelSize(R.styleable.UIBadgeView_ws_text_size, DEFAULT_TEXT_SIZE);
        mMaxVisibleCount = a.getInteger(R.styleable.UIBadgeView_ws_max_visible_count, DEFAULT_MAX_VISIBLE_COUNT);
        mPaddingH = a.getDimensionPixelSize(R.styleable.UIBadgeView_ws_padding_horizontal, DEFAULT_PADDING_HORIZONTAL);
        mPaddingV = a.getDimensionPixelSize(R.styleable.UIBadgeView_ws_padding_vertical, DEFAULT_PADDING_VERTICAL);
//        mCornors = a.getDimensionPixelSize(R.styleable.UIBadgeView_cornors, 0);
        mCount = a.getInteger(R.styleable.UIBadgeView_ws_count, 0);

        setCount(mCount);
        a.recycle();
    }

    private void initPaints() {
        if (mBgDrawableRes == -1) {
            mPaintBg = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaintBg.setColor(mBgColorRes);
        }

        mPaintTv = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintTv.setColor(mTextColor);
        mPaintTv.setTextSize(mTextSize);
        mPaintTv.setTextAlign(Paint.Align.CENTER);
    }

    /**
     * 设置文字大小
     *
     * @param textSize 大小，单位sp，比如 设置 大小为 20sp，则 setTextSize(20) 即可
     */
    public void setTextSize(int textSize) {
        textSize = SizeUtils.sp2px(textSize);
        mTextSize = textSize;
        mPaintTv.setTextSize(mTextSize);
    }

    /**
     * 设置 圆角半径
     *
     * @param cornors 半径，单位 dp
     */
    public void setCornors(int cornors) {
        cornors = SizeUtils.dp2px(cornors);
        mCornors = cornors;
    }

    public void setPaddingH(int paddingH) {
        mPaddingH = SizeUtils.dp2px(paddingH);

    }

    public void setPaddingV(int paddingV) {
        mPaddingV = SizeUtils.dp2px(paddingV);
    }

    public void setBgColorRes(int bgColorRes) {
        mBgColorRes = bgColorRes;
    }

    public void setTextColor(int textColor) {
        mTextColor = textColor;
    }

    public void setMaxVisibleCount(int maxVisibleCount) {
        mMaxVisibleCount = maxVisibleCount;
        /** 调用 setCount 防止先调用了setMaxVisibleCount 再调用 setCount 不起作用的问题 */
        setCount(mCount);
    }

    public void setCount(int count) {
        mCount = count;
        mText = String.valueOf(mCount);
        if (count > mMaxVisibleCount) {
            /** 当前数量大于可见最大数量的时候，取最大可见数再拼接个 加号就好*/
            mText = mMaxVisibleCount + "+";
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getMeasureSize(widthMeasureSpec, mPaddingH, true);
        int height = getMeasureSize(heightMeasureSpec, mPaddingV, false);

        setMeasuredDimension(width, height);
    }

    private int getMeasureSize(int measureSpec, int padding, boolean isWidth) {
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        int result = size;
        switch (mode) {
            case MeasureSpec.EXACTLY:
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                if (isWidth)
                    result = calTextWidth(mText) + 2 * padding;
                else
                    result = calTextHeight(mPaintTv) + 2 * padding;
                break;
        }
        return result;
    }

    /**
     * 计算文本 宽度
     *
     * @param text
     * @return 文本宽度 ，单位 px
     */
    private int calTextWidth(String text) {
        float textWidth2 = mPaintTv.measureText(text);
        return (int) textWidth2;
    }

    /**
     * 计算文本 高度
     *
     * @param paint 绘制文本的画笔
     * @return
     */
    private int calTextHeight(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        int textHeight = (int) (fontMetrics.descent - fontMetrics.ascent);
        return textHeight;
    }


    @Override
    protected void onDraw(Canvas canvas) {

        /** 1、绘制背景 */
        RectF rectF = new RectF(0, 0, getWidth(), getHeight());
        if (mBgDrawableRes == -1) {
            /** 当用户没有设置背景资源的时候，我们需要给用户绘制一个默认的背景*/
            Path path = new Path();
            if (mCornors == 0)
            /** 用户指定了 cornor 属性，则取用户设置的值，否则取高度一半作为半径*/
                mCornors = getHeight() / 2;
            /** CW-顺时针，CCW-逆时针*/
            path.addRoundRect(rectF, mCornors, mCornors, Path.Direction.CW);
            canvas.drawPath(path, mPaintBg);
        } else {
            /** 当用户指定了背景资源，则我们直接绘制背景资源即可 */
            Bitmap rawBp = BitmapFactory.decodeResource(getResources(), mBgDrawableRes);
            Bitmap scaledBp = Bitmap.createScaledBitmap(rawBp, getWidth(), getHeight(), true);
            canvas.drawBitmap(scaledBp, 0, 0, mPaintTv);
        }


        /** 2、计算 文字的宽度、起点X、起点Y*/
        Paint.FontMetrics fontMetrics = mPaintTv.getFontMetrics();
        /** 2.1 由于设置了 画笔的文字对齐方式为 居中，所以textX 的坐标就是中心点的坐标*/
        float textX = rectF.centerX();
        /** 2.2 由于文本的绘制方式是沿着baseLine 去绘制的，所以，为了使文本垂直居中，要计算出，baseline 到 centerY 的垂直距离，然后偏移这个量即可*/
        float textY = getMeasuredHeight() / 2 - (fontMetrics.descent + fontMetrics.ascent) / 2;
        /** 2.3 绘制文字 */
        canvas.drawText(mText, textX, textY, mPaintTv);

        /** 绘制线，检测文字居中*/
//        canvas.drawLine(0, fontMetrics.ascent, 400, fontMetrics.ascent, mPaintTv);
//        canvas.drawLine(0, fontMetrics.descent, 400, fontMetrics.descent, mPaintTv);
//        canvas.drawLine(0, getMeasuredHeight() / 2, 400, getMeasuredHeight() / 2, mPaintTv);
//        canvas.drawLine(getMeasuredWidth() / 2, 0, getMeasuredWidth() / 2, 300, mPaintTv);
    }
}
