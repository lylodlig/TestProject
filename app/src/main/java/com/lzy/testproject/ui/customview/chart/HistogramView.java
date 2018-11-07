package com.lzy.testproject.ui.customview.chart;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.blankj.utilcode.util.ConvertUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by LiZhiyu on 2018/11/6.
 */
public class HistogramView extends View {
    private List<PieInfo> mData;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    private int mWidth;
    private int mHeight;
    private double mTotalValue;
    private ModeEnum mType = ModeEnum.Horizontal;
    private boolean isStartAnim = false;
    private long mAnimDuration = 2000;
    private int mTextSize = 30;
    private int mCorner = 10;
    private int mBackColor;
    private OnClickListener onClickListener;
    private ValueAnimator valueAnimator;


    public enum ModeEnum {
        Horizontal, Vertical, HorizontalFull
    }

    public HistogramView(Context context) {
        super(context);
        init();
    }

    public HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    private void init() {
        mBackColor = Color.parseColor("#F5F5F5");
    }

    public HistogramView setType(ModeEnum mType) {
        this.mType = mType;
        return this;
    }

    public HistogramView setStartAnim(boolean startAnim) {
        isStartAnim = startAnim;
        return this;
    }

    public HistogramView setmAnimDuration(long mAnimDuration) {
        this.mAnimDuration = mAnimDuration;
        return this;
    }

    public HistogramView setmTextSize(int mTextSize) {
        this.mTextSize = mTextSize;
        return this;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeight = getMeasuredHeight();
        mWidth = getMeasuredWidth();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mData == null || mData.size() == 0)
            return;
        RectF rectF;
        PieInfo pieInfo;
        float right;
        switch (mType) {
            case Vertical:
                int textHeight = ConvertUtils.dp2px(20);
                rectF = new RectF(0, textHeight, mWidth, mHeight);
                mPaint.setColor(mBackColor);
                canvas.drawRoundRect(rectF, 3, 3, mPaint);
                if (!isStartAnim) {
                    pieInfo = mData.get(0);
                    mPaint.setColor(pieInfo.color);
                    float height = (float) ((mHeight - textHeight) * pieInfo.value / mTotalValue);
                    canvas.drawRoundRect(0, mHeight - height, mWidth, mHeight, mCorner, mCorner, mPaint);


                    mPaint.setTextSize(mTextSize);
                    mPaint.setColor(pieInfo.color);
                    mPaint.setTextSize(mTextSize);
                    Rect textRect = new Rect();
                    BigDecimal b1 = new BigDecimal(100 * pieInfo.value);
                    BigDecimal b2 = new BigDecimal(mTotalValue);
                    String title = b1.divide(b2, 0, BigDecimal.ROUND_HALF_UP).intValue() + "%";
                    mPaint.getTextBounds(title, 0, title.length(), textRect);
                    canvas.drawText(title, (mWidth - textRect.width()) / 2, mHeight - height - textRect.height(), mPaint);
                }
                break;
            case Horizontal:
                rectF = new RectF(0, 0, mWidth, mHeight);
                mPaint.setColor(mBackColor);
                canvas.drawRoundRect(rectF, 3, 3, mPaint);

                pieInfo = mData.get(0);
                mPaint.setColor(pieInfo.color);
                right = (float) (mWidth * pieInfo.value / mTotalValue);
                canvas.drawRoundRect(0, 0, currentAnimValue, mHeight, mCorner, mCorner, mPaint);
                Log.i("lzy", "onDraw: " + currentAnimValue + "  " + right);
                if ((right - currentAnimValue) < 1) {
                    mPaint.setTextSize(mTextSize);
                    mPaint.setColor(Color.WHITE);
                    Rect textRect = new Rect();
                    pieInfo.title = String.valueOf((int) pieInfo.value);
                    mPaint.getTextBounds(pieInfo.title, 0, pieInfo.title.length(), textRect);
                    canvas.drawText(pieInfo.title, (right - textRect.width()) / 2, mHeight / 2 + textRect.height() / 2, mPaint);
                }

                break;
            case HorizontalFull:
                rectF = new RectF(0, 0, mWidth, mHeight);
                mPaint.setColor(mBackColor);
                canvas.drawRect(rectF, mPaint);
                float start = 0;
                for (int i = 0; i < mData.size(); i++) {
                    pieInfo = mData.get(i);
                    if (pieInfo.value == 0) {
                        continue;
                    }
                    mPaint.setColor(pieInfo.color);
                    right = (float) (mWidth * pieInfo.value / mTotalValue) + start;
                    RectF rectf = new RectF(start, 0, right, mHeight);
                    canvas.drawRect(rectf, mPaint);
                    pieInfo.rectF = rectf;


                    mPaint.setTextSize(mTextSize);
                    mPaint.setColor(Color.WHITE);
                    Rect textRect = new Rect();
                    BigDecimal b1 = new BigDecimal(100 * pieInfo.value);
                    BigDecimal b2 = new BigDecimal(mTotalValue);
                    String title = b1.divide(b2, 0, BigDecimal.ROUND_HALF_UP).intValue() + "%";
                    mPaint.getTextBounds(title, 0, title.length(), textRect);
                    canvas.drawText(title, (right - start - textRect.width()) / 2 + start, (mHeight + textRect.height()) / 2, mPaint);
                    start = right;
                }
                break;
        }
    }

    private float startX, startY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                return true;
            case MotionEvent.ACTION_UP:
                for (int i = 0; i < mData.size(); i++) {
                    PieInfo pieInfo = mData.get(i);
                    Log.i("lzy", "onTouchEvent: "+event.getRawX());
                    if (onClickListener != null && pieInfo.rectF != null && pieInfo.rectF.contains(startX, startY) && pieInfo.rectF.contains(event.getX(), event.getY())) {
                        onClickListener.onClick(pieInfo);
                    }
                }
                break;
        }
        return true;
    }

    public void setData(List<PieInfo> list, int totalValue) {
        mTotalValue = 0;
        this.mData = list;
        if (mType == ModeEnum.Horizontal || mType == ModeEnum.Vertical) {
            mTotalValue = totalValue;
        } else {
            for (int i = 0; i < list.size(); i++) {
                mTotalValue = (mTotalValue + list.get(i).value);
            }
        }
        post(new Runnable() {
            @Override
            public void run() {
                if (!isStartAnim) {
                    BigDecimal b1 = new BigDecimal(mWidth * mData.get(0).value);
                    BigDecimal b2 = new BigDecimal(mTotalValue);
                    BigDecimal divide = b1.divide(b2, 0, BigDecimal.ROUND_HALF_UP);
                    currentAnimValue = divide.floatValue();
                    invalidate();
                } else {
                    show();
                }
            }
        });


    }

    private float currentAnimValue;

    private void show() {
        if (mType == ModeEnum.Horizontal && mData.size() > 0) {
            BigDecimal b1 = new BigDecimal(mWidth * mData.get(0).value);
            BigDecimal b2 = new BigDecimal(mTotalValue);
            BigDecimal divide = b1.divide(b2, 0, BigDecimal.ROUND_HALF_UP);
            if (valueAnimator != null && valueAnimator.isRunning()) {
                valueAnimator.cancel();
            }
            valueAnimator = ValueAnimator.ofFloat(0f, divide.floatValue());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    currentAnimValue = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            valueAnimator.setDuration(mAnimDuration);
            valueAnimator.start();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (valueAnimator != null && valueAnimator.isRunning()) {
            valueAnimator.cancel();
        }
    }

    public interface OnClickListener {
        void onClick(PieInfo pieInfo);
    }

}
