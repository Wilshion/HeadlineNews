package com.wilshion.headlinenews.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.wilshion.headlinenews.R;

/**
 * Created by Wilshion on 2017/8/16 10:36.
 * [description : 自定义 基础控件，仿ios UIView的CALayer 层]
 * [version : 1.0]
 */
public class UIView extends View  {
    private Layer mLayer;
    private Paint mCornorPaint, mBorderPaint;


    public UIView(Context context) {
        this(context, null);
    }

    public UIView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UIView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        initLayer();
        initAttrs(context, attrs, defStyleAttr);
    }

    private void initLayer() {
        mLayer = new Layer();
    }


    private void initAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.UIView);
        /** Cornor 层*/
        mLayer.mCornor.mCornors = a.getDimensionPixelOffset(R.styleable.UIView_ws_cornors, 0);
        mLayer.mCornor.mTopLeftCornor = a.getDimensionPixelOffset(R.styleable.UIView_ws_cornor_top_left, 0);
        mLayer.mCornor.mTopRightCornor = a.getDimensionPixelOffset(R.styleable.UIView_ws_cornor_top_right, 0);
        mLayer.mCornor.mBottomRightCornor = a.getDimensionPixelOffset(R.styleable.UIView_ws_cornor_bottom_right, 0);
        mLayer.mCornor.mBottomLeftCornor = a.getDimensionPixelOffset(R.styleable.UIView_ws_cornor_bottom_left, 0);
        /** Border 层*/
        mLayer.mBorder.mBorderWidth = a.getDimensionPixelSize(R.styleable.UIView_ws_border_width, 0);
        mLayer.mBorder.mBorderColor = a.getColor(R.styleable.UIView_ws_border_color, Color.BLUE);
        a.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mLayer != null && mLayer.mBorder.mBorderWidth > 0) {
            int borderWidth = mLayer.mBorder.mBorderWidth;
            int width = getDefaultSize(getMinimumWidth() + 2 * borderWidth, widthMeasureSpec);
            int height = getDefaultSize(getMeasuredHeight() + 2 * borderWidth, heightMeasureSpec);
            setMeasuredDimension(width, height);
        } else
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (mLayer != null) {
            if (mLayer.mBorder.mBorderWidth > 0) {
                int sc = canvas.saveLayer(0, 0, getWidth(), getHeight(), null,
                        Canvas.MATRIX_SAVE_FLAG |
                                Canvas.CLIP_SAVE_FLAG |
                                Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
                                Canvas.FULL_COLOR_LAYER_SAVE_FLAG |
                                Canvas.CLIP_TO_LAYER_SAVE_FLAG);
                canvas.drawBitmap(drawOriginBmp(getWidth(), getHeight()), 0, 0, getCornorPaint());
                getCornorPaint().setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
                canvas.drawBitmap(drawContent(getWidth(), getHeight()), 0, 0, getCornorPaint());
                getCornorPaint().setXfermode(null);
                canvas.restoreToCount(sc);
            }
            if (mLayer.mCornor.hasCornors()) {

                int sc1 = canvas.saveLayer(0, 0, getWidth(), getHeight(), null,
                        Canvas.MATRIX_SAVE_FLAG |
                                Canvas.CLIP_SAVE_FLAG |
                                Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
                                Canvas.FULL_COLOR_LAYER_SAVE_FLAG |
                                Canvas.CLIP_TO_LAYER_SAVE_FLAG);

                canvas.drawBitmap(drawCornor(getWidth(), getHeight()), 0, 0, getCornorPaint());
                getCornorPaint().setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
                canvas.drawBitmap(drawCornorRect(getWidth(), getHeight()), 0, 0, getCornorPaint());
                getCornorPaint().setXfermode(null);
                canvas.restoreToCount(sc1);
            }
        }
    }

    private Bitmap drawOriginBmp(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(mLayer.mBorder.mBorderColor);

        canvas.drawRect(0, 0, width, height, p);
        return bitmap;
    }


    private Bitmap drawBorder(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(mLayer.mBorder.mBorderColor);
        p.setStyle(Paint.Style.FILL);

        int topLeft = mLayer.mCornor.mTopLeftCornor;
        int topRight = mLayer.mCornor.mTopRightCornor;
        int bottomLeft = mLayer.mCornor.mBottomLeftCornor;
        int bottomRight = mLayer.mCornor.mBottomRightCornor;
        Path path = new Path();
        path.addRoundRect(new RectF(0, 0, width, height),
                new float[]{topLeft, topLeft, topRight, topRight, bottomRight, bottomRight, bottomLeft, bottomLeft},
                Path.Direction.CW);
        canvas.drawPath(path, p);
        return bitmap;
    }

    private Bitmap drawContent(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        int borderWidth = mLayer.mBorder.mBorderWidth;
        int topLeft = mLayer.mCornor.mTopLeftCornor;
        int topRight = mLayer.mCornor.mTopRightCornor;
        int bottomLeft = mLayer.mCornor.mBottomLeftCornor;
        int bottomRight = mLayer.mCornor.mBottomRightCornor;
        Path path = new Path();
        path.addRoundRect(new RectF(borderWidth, borderWidth, width - borderWidth, height - borderWidth),
                new float[]{topLeft, topLeft, topRight, topRight, bottomRight, bottomRight, bottomLeft, bottomLeft},
                Path.Direction.CW);
        canvas.drawPath(path, p);
        return bitmap;
    }

    private Bitmap drawCornor(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(0xFFFFCC44);

        int topLeft = mLayer.mCornor.mTopLeftCornor;
        int topRight = mLayer.mCornor.mTopRightCornor;
        int bottomLeft = mLayer.mCornor.mBottomLeftCornor;
        int bottomRight = mLayer.mCornor.mBottomRightCornor;
        canvas.drawArc(new RectF(0, 0, topLeft * 2, topLeft * 2), 180, 90, true, p);
        canvas.drawArc(new RectF(width - 2 * topRight, 0, width, 2 * topRight), 270, 90, true, p);
        canvas.drawArc(new RectF(width - 2 * bottomRight, height - 2 * bottomRight, width, height), 0, 90, true, p);
        canvas.drawArc(new RectF(0, height - 2 * bottomLeft, 2 * bottomLeft, height), 90, 90, true, p);


        return bitmap;
    }

    private Bitmap drawCornorRect(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.WHITE);

        int topLeft = mLayer.mCornor.mTopLeftCornor;
        int topRight = mLayer.mCornor.mTopRightCornor;
        int bottomLeft = mLayer.mCornor.mBottomLeftCornor;
        int bottomRight = mLayer.mCornor.mBottomRightCornor;
        canvas.drawRect(new RectF(0, 0, topLeft, topLeft), p);
        canvas.drawRect(new RectF(getWidth() - topRight, 0, getWidth(), topRight), p);
        canvas.drawRect(new RectF(getWidth() - bottomRight, getHeight() - bottomRight, getWidth(), getHeight()), p);
        canvas.drawRect(new RectF(0, getHeight() - bottomLeft, bottomLeft, getHeight()), p);

        return bitmap;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return super.onTouchEvent(event);
    }

    public Layer getLayer() {
        return mLayer;
    }

    public Paint getBorderPaint() {
        if (mBorderPaint == null) {
            mBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mBorderPaint.setStrokeWidth(mLayer.mBorder.mBorderWidth);
            mBorderPaint.setColor(mLayer.mBorder.mBorderColor);
        }
        return mBorderPaint;
    }

    private Paint getCornorPaint() {
        if (mCornorPaint == null) {
            mCornorPaint = new Paint();
            mCornorPaint.setFilterBitmap(false);
            mCornorPaint.setStyle(Paint.Style.FILL);
        }
        return mCornorPaint;
    }
    

    /**
     * 圆角半径
     */
    public static final class Layer {

        private Cornor mCornor;
        private Border mBorder;

        public Layer() {
            mCornor = new Cornor();
            mBorder = new Border();
        }

        public void setCornor(Cornor cornor) {
            mCornor = cornor;
        }
    }

    public static final class Cornor {
        /**
         * 左上角半径
         */
        private int mTopLeftCornor;
        /**
         * 右上角半径
         */
        private int mTopRightCornor;
        /**
         * 左下角半径
         */
        private int mBottomLeftCornor;
        /**
         * 右下角半径
         */
        private int mBottomRightCornor;

        private int mCornors;

        public boolean hasCornors() {
            if (mCornors > 0)
                return true;
            if (mTopLeftCornor > 0
                    || mTopRightCornor > 0
                    || mBottomLeftCornor > 0
                    || mBottomRightCornor > 0)
                return true;
            return false;
        }
    }

    public static final class Border {
        private int mBorderWidth;
        private int mBorderColor;
    }

}
