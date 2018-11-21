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
    private boolean drawAsPercent;//是否按照百分比来画，不需要计算百分不


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
        float right = 0;
        switch (mType) {
            case Vertical:
                int textHeight = ConvertUtils.dp2px(20);
                rectF = new RectF(0, textHeight, mWidth, mHeight);
                mPaint.setColor(mBackColor);
                canvas.drawRoundRect(rectF, 3, 3, mPaint);
                if (!isStartAnim) {
                    pieInfo = mData.get(0);
                    mPaint.setColor(pieInfo.color);
                    float height;
                    String titleText;
                    if (drawAsPercent) {
                        height = (float) ((mHeight - textHeight) * pieInfo.percent / 100);
                        titleText = pieInfo.percent + "%";
                    } else {
                        height = (float) ((mHeight - textHeight) * pieInfo.value / mTotalValue);
                        BigDecimal b1 = new BigDecimal(100 * pieInfo.value);
                        BigDecimal b2 = new BigDecimal(mTotalValue);
                        titleText = b1.divide(b2, 0, BigDecimal.ROUND_HALF_UP).intValue() + "%";
                    }
                    RectF rectf = new RectF(0, mHeight - height, mWidth, mHeight);
                    canvas.drawRoundRect(rectf, mCorner, mCorner, mPaint);
                    pieInfo.rectF = rectf;
                    mPaint.setColor(pieInfo.color);
                    mPaint.setTextSize(mTextSize);
                    Rect textRect = new Rect();
                    mPaint.getTextBounds(titleText, 0, titleText.length(), textRect);
                    canvas.drawText(titleText, (mWidth - textRect.width()) / 2, mHeight - height - textRect.height(), mPaint);
                }
                break;
            case Horizontal:
                rectF = new RectF(0, 0, mWidth, mHeight);
                mPaint.setColor(mBackColor);
                canvas.drawRoundRect(rectF, 3, 3, mPaint);
                if (mData.size() == 0) {
                    return;
                }
                pieInfo = mData.get(0);
                mPaint.setColor(pieInfo.color);
                if (drawAsPercent) {
                    right = (float) (mWidth * pieInfo.percent / 100);
                } else {
                    if (mTotalValue > 0)
                        right = (float) (mWidth * pieInfo.value / mTotalValue);
                }
                canvas.drawRoundRect(0, 0, currentAnimValue, mHeight, mCorner, mCorner, mPaint);

                if (right > 0 && (right - currentAnimValue) < 1) {
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
                    if (drawAsPercent) {
                        right = (float) (mWidth * pieInfo.percent / 100) + start;
                    } else {
                        right = (float) (mWidth * pieInfo.value / mTotalValue) + start;
                    }

                    RectF rectf = new RectF(start, 0, right, mHeight);
                    canvas.drawRect(rectf, mPaint);
                    pieInfo.rectF = rectf;
                    start = pieInfo.rectF.right;

                }
                start = 0;
                for (int i = 0; i < mData.size(); i++) {
                    pieInfo = mData.get(i);
                    if (pieInfo.value == 0) {
                        continue;
                    }
                    mPaint.setTextSize(mTextSize);
                    mPaint.setColor(Color.WHITE);
                    Rect textRect = new Rect();
                    String title;
                    if (drawAsPercent) {
                        title = pieInfo.percent + "%";
                    } else {
                        BigDecimal b1 = new BigDecimal(100 * pieInfo.value);
                        BigDecimal b2 = new BigDecimal(mTotalValue);
                        title = b1.divide(b2, 0, BigDecimal.ROUND_HALF_UP).intValue() + "%";
                    }
                    mPaint.getTextBounds(title, 0, title.length(), textRect);
                    String[] split = title.split("%");
                    if (Double.valueOf(split[0]) < 10) {
                        canvas.drawText(title, start, (mHeight + textRect.height()) / 2, mPaint);
                    } else {
                        canvas.drawText(title, (pieInfo.rectF.right - start - textRect.width()) / 2 + start, (mHeight + textRect.height()) / 2, mPaint);
                    }


                    start = pieInfo.rectF.right;
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
                    if (onClickListener != null && pieInfo.rectF != null && pieInfo.rectF.contains(startX, startY) && pieInfo.rectF.contains(event.getX(), event.getY())) {
                        onClickListener.onClick(pieInfo, event.getRawX(), event.getRawY());
//                        show(getContext(), pieInfo.value + "", event.getRawX(), event.getRawY());
                    }
                }
                break;
        }
        return true;
    }

    public void setData(List<PieInfo> list, int totalValue) {
        mTotalValue = 0;
        currentAnimValue = 0;
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
                if (isStartAnim && mType == ModeEnum.Horizontal) {//已动画方式展示
                    show();
                } else {
                    if (mData.size() > 0) {
                        if (drawAsPercent) {
                            currentAnimValue = (float) (mData.get(0).percent * mWidth / 100);
                        } else {
                            if (mTotalValue == 0) {
                                currentAnimValue = 0;
                            } else {
                                BigDecimal b1 = new BigDecimal(mWidth * mData.get(0).value);
                                BigDecimal b2 = new BigDecimal(mTotalValue);
                                BigDecimal divide = b1.divide(b2, 0, BigDecimal.ROUND_HALF_UP);
                                currentAnimValue = divide.floatValue();
                            }
                        }
                    }
                    invalidate();
                }
            }
        });
    }

    //已经计算了百分比
    public void setData(List<PieInfo> list) {
        drawAsPercent = true;
        setData(list, 0);
    }

    private float currentAnimValue;

    private void show() {
        if (mType == ModeEnum.Horizontal && mData.size() > 0) {
            float value;
            if (drawAsPercent) {
                value = (float) (mData.get(0).percent * mWidth / 100);
            } else {
                BigDecimal b1 = new BigDecimal(mWidth * mData.get(0).value);
                BigDecimal b2 = new BigDecimal(mTotalValue);
                BigDecimal divide = b1.divide(b2, 0, BigDecimal.ROUND_HALF_UP);
                value = divide.floatValue();
            }

            if (valueAnimator != null && valueAnimator.isRunning()) {
                valueAnimator.cancel();
            }
            valueAnimator = ValueAnimator.ofFloat(0f, value);
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
        void onClick(PieInfo pieInfo, float clickX, float clickY);
    }

}
