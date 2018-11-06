package com.lzy.testproject.ui.customview.chart;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiZhiyu on 2018/11/2.
 */
public class PieView extends View {
    private List<PieInfo> mListData = new ArrayList<>();
    private int mPieWidth = 20;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    private Paint mInnerTextPaint = new Paint();
    private Paint mTextPaint = new Paint();
    private Paint mOuterTextPaint = new Paint();
    private Paint mOuterTextPaintGray = new Paint();
    private Paint mOuterTextPaintGray2 = new Paint();
    private int mWidth;
    private int mHeight;
    private int mRadius;
    private int mOuterTextColor = Color.BLACK;
    private int mOuterTextColorGray = Color.GRAY;
    @ColorInt
    private int mBackgroundColor = Color.TRANSPARENT;
    private ModeEnum mMode;
    private int mMaxValue;
    private int mAlpha;
    private int startDegree = 180;
    private int mCircleWidth = 200;
    private int mInnerTextSize = 40;
    private int mOuterTextSize = 40;
    private int mOuterGrayTextSize = 30;
    private TextInfo mCenterText1;
    private TextInfo mCenterText2;

    private int mOuterExtendWidth = 50;
    private int mOuterExtendLineWidth = 200;
    private boolean isAnimEnable = true;
    private long mAnimDuration = 2000;
    private boolean isShowOuterText = false;
    private int mDx;//分割的白色间距
    private int mCenterTextMargin;
    private double mTotalValue;

    public enum ModeEnum {
        PieFull, Pie, ColumnHorizontal, ColumnHorizontalFull, ColumnVertical
    }

    public PieView(Context context) {
        super(context);
        init();
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint.setAntiAlias(true);
        mInnerTextPaint.setAntiAlias(true);
        mInnerTextPaint.setColor(Color.WHITE);
        mInnerTextPaint.setTextSize(mInnerTextSize);

        mTextPaint.setAntiAlias(true);

        if (isShowOuterText) {
            mOuterTextPaint.setAntiAlias(true);
            mOuterTextPaint.setColor(mOuterTextColor);
            mOuterTextPaint.setTextSize(mOuterTextSize);

            mOuterTextPaintGray.setAntiAlias(true);
            mOuterTextPaintGray.setColor(mOuterTextColorGray);
            mOuterTextPaintGray.setTextSize(mOuterGrayTextSize);

            mOuterTextPaintGray2.setAntiAlias(true);
            mOuterTextPaintGray2.setColor(mOuterTextColorGray);
            mOuterTextPaintGray2.setTextSize(mOuterGrayTextSize);
        }


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
//        mCircleWidth = Math.min(mWidth, mHeight);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(mBackgroundColor);
        RectF rectF = new RectF(mWidth / 2 - mCircleWidth + mPieWidth / 2, mHeight / 2 - mCircleWidth + mPieWidth / 2, mWidth / 2 + mCircleWidth - mPieWidth / 2, mHeight / 2 + mCircleWidth - mPieWidth / 2);
        startDegree = -180;
        switch (mMode) {
            case PieFull:
                //画中间文字
                Rect centerText1 = new Rect();
                Rect centerText2 = new Rect();
                mTextPaint.setTextSize(mCenterText1.size);
                mTextPaint.setColor(mCenterText1.color);
                mTextPaint.getTextBounds(mCenterText1.text, 0, mCenterText1.text.length(), centerText1);
                canvas.drawText(mCenterText1.text, (mWidth - centerText1.width()) / 2, (mHeight / 2 - centerText1.height() - mCenterTextMargin / 2), mTextPaint);


                mTextPaint.setTextSize(mCenterText2.size);
                mTextPaint.setColor(mCenterText2.color);
                mTextPaint.getTextBounds(mCenterText2.text, 0, mCenterText2.text.length(), centerText2);
                canvas.drawText(mCenterText2.text, (mWidth - centerText2.width()) / 2, (mHeight / 2 + centerText2.height() + mCenterTextMargin / 2), mTextPaint);
                for (int i = 0; i < mListData.size(); i++) {
                    mPaint.setStrokeWidth(mPieWidth);
                    mPaint.setStyle(Paint.Style.STROKE);
                    PieInfo pieInfo = mListData.get(i);
                    mPaint.setColor(pieInfo.color);
                    canvas.drawArc(rectF, startDegree, pieInfo.degree + mDx, false, mPaint);
                    mPaint.setColor(Color.WHITE);
                    canvas.drawArc(rectF, startDegree + pieInfo.degree, mDx, false, mPaint);


                    //画文字
                    int centerDegree = startDegree + pieInfo.degree / 2;
                    BigDecimal bigDecimal1 = new BigDecimal(100 * pieInfo.value);
                    BigDecimal bigDecimal2 = new BigDecimal(mTotalValue);
//                    bigDecimal1.setScale(1, BigDecimal.ROUND_HALF_UP);
                    BigDecimal divide = bigDecimal1.divide(bigDecimal2, 0, BigDecimal.ROUND_HALF_UP);
                    int raduis = mCircleWidth - mPieWidth / 2;
                    int innerTextX = (int) (Math.cos(2 * Math.PI / 360 * centerDegree) * raduis) + mWidth / 2;
                    int innerTextY = (int) (Math.sin(2 * Math.PI / 360 * centerDegree) * raduis) + mHeight / 2;
                    Rect rectInnerText = new Rect();
                    mInnerTextPaint.getTextBounds(divide.intValue() + "%", 0, (divide.intValue() + "%").length(), rectInnerText);

                    canvas.drawText(divide.intValue() + "%", innerTextX - rectInnerText.width() / 2, innerTextY, mInnerTextPaint);


                    if (isShowOuterText) {
                        //画外面文字的线条
                        mPaint.setStrokeWidth(4);
                        mPaint.setStyle(Paint.Style.FILL);
                        int outterTextX = (int) (Math.cos(2 * Math.PI / 360 * centerDegree) * (mCircleWidth + 20)) + mWidth / 2;
                        int outterTextY = (int) (Math.sin(2 * Math.PI / 360 * centerDegree) * (mCircleWidth + 20)) + mHeight / 2;
                        canvas.drawCircle(outterTextX, outterTextY, 10, mPaint);
                        mPaint.setStyle(Paint.Style.STROKE);
                        Path path = new Path();
                        path.moveTo(outterTextX, outterTextY);
                        double x = Math.cos(2 * Math.PI / 360 * centerDegree);
                        double y = Math.sin(2 * Math.PI / 360 * centerDegree);
                        path.lineTo((float) (outterTextX + mOuterExtendWidth * x), (float) (outterTextY + mOuterExtendWidth * y));
                        if (x > 0)
                            path.lineTo((float) (outterTextX + mOuterExtendLineWidth), (float) (outterTextY + mOuterExtendWidth * y));
                        else
                            path.lineTo((float) (outterTextX - mOuterExtendLineWidth), (float) (outterTextY + mOuterExtendWidth * y));

                        canvas.drawPath(path, mPaint);


                        Rect rect = new Rect();
                        Rect rectGrayDes = new Rect();
                        Rect rectGrayDes2 = new Rect();
                        mOuterTextPaint.getTextBounds(pieInfo.title, 0, pieInfo.title.length(), rect);
                        mOuterTextPaintGray.getTextBounds(pieInfo.describe, 0, pieInfo.describe.length(), rectGrayDes);
                        mOuterTextPaintGray2.getTextBounds(pieInfo.describe2, 0, pieInfo.describe2.length(), rectGrayDes2);

                        if (y > 0) {
                            if (x > 0) {
                                canvas.drawText(pieInfo.title, (float) (outterTextX + x * mOuterExtendWidth), (float) (outterTextY + mOuterExtendWidth * y - 10), mOuterTextPaint);
                                canvas.drawText(pieInfo.describe, outterTextX, (float) (outterTextY + mOuterExtendWidth * y + 10 + rectGrayDes.height()), mOuterTextPaintGray);
                                canvas.drawText(pieInfo.describe2, outterTextX, (float) (outterTextY + mOuterExtendWidth * y + 20 + 2 * rectGrayDes2.height()), mOuterTextPaintGray);
                            } else {
                                canvas.drawText(pieInfo.title, (float) (outterTextX + x * mOuterExtendWidth) - rect.width() - 10, (float) (outterTextY + mOuterExtendWidth * y - 10), mOuterTextPaint);
                                canvas.drawText(pieInfo.describe, outterTextX - rectGrayDes.width() - 10, (float) (outterTextY + mOuterExtendWidth * y + 10 + rectGrayDes.height()), mOuterTextPaintGray);
                                canvas.drawText(pieInfo.describe2, (float) (outterTextX) - rectGrayDes2.width() - 10, (float) (outterTextY + mOuterExtendWidth * y + 20 + 2 * rectGrayDes2.height()), mOuterTextPaintGray);
                            }
                        } else {
                            if (x > 0) {
                                canvas.drawText(pieInfo.title, (float) (outterTextX + x * mOuterExtendWidth), (float) (outterTextY + mOuterExtendWidth * y + rect.height() + 10), mOuterTextPaint);
                                canvas.drawText(pieInfo.describe, outterTextX, (float) (outterTextY + mOuterExtendWidth * y - 20 - 2 * rectGrayDes.height()), mOuterTextPaintGray);
                                canvas.drawText(pieInfo.describe2, outterTextX, (float) (outterTextY + mOuterExtendWidth * y - 10 - rectGrayDes2.height()), mOuterTextPaintGray);
                            } else {
                                canvas.drawText(pieInfo.title, (float) (outterTextX + x * mOuterExtendWidth) - rect.width() - 10, (float) (outterTextY + mOuterExtendWidth * y + rect.height() + 10), mOuterTextPaint);
                                canvas.drawText(pieInfo.describe, outterTextX - rectGrayDes.width() - 10, (float) (outterTextY + mOuterExtendWidth * y - 20 - 2 * rectGrayDes.height()), mOuterTextPaintGray);
                                canvas.drawText(pieInfo.describe2, outterTextX - rectGrayDes2.width() - 10, (float) (outterTextY + mOuterExtendWidth * y - rectGrayDes2.height()), mOuterTextPaintGray);
                            }
                        }
                    }
                    startDegree = startDegree + pieInfo.degree + mDx;
                }
                break;
            case Pie:
                mPaint.setStrokeWidth(mPieWidth);
                mPaint.setStyle(Paint.Style.STROKE);
                if (mListData.size() > 0) {
                    PieInfo pieInfo = mListData.get(0);
                    mPaint.setColor(pieInfo.color);
                    mPaint.setAlpha(mAlpha);
                    canvas.drawArc(rectF, 0, 360, false, mPaint);
                    mPaint.setColor(pieInfo.color);
                    canvas.drawArc(rectF, -90, sweepDegree, false, mPaint);
                }
            case ColumnHorizontal:
                break;
            case ColumnHorizontalFull:
                break;
            case ColumnVertical:
                break;

        }
    }

    private float sweepDegree = 0;

    public void show() {
        if (mMode == ModeEnum.Pie && mListData.size() > 0) {
            BigDecimal b1 = new BigDecimal(360 * mListData.get(0).value);
            BigDecimal b2 = new BigDecimal(mMaxValue);
            BigDecimal divide = b1.divide(b2, 0, BigDecimal.ROUND_HALF_UP);
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, divide.floatValue());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    sweepDegree = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            valueAnimator.setDuration(mAnimDuration);
            valueAnimator.start();
        }
    }

    public void setBuilder(Builder builder) {
        mListData = builder.listData;
        mPieWidth = builder.pieWidth;
        mBackgroundColor = builder.backColor;
        mMaxValue = builder.max;
        mMode = builder.mode;
        mAlpha = builder.alpha;
        mPaint.setStrokeWidth(mPieWidth);
        mRadius = (mWidth - mPieWidth) / 2;
        mCircleWidth = builder.circleWidth;
        startDegree = builder.startDegree;
        mInnerTextSize = builder.mInnerTextSize;
        mOuterTextSize = builder.mOuterTextSize;
        mOuterGrayTextSize = builder.mOuterGrayTextSize;
        mOuterExtendWidth = builder.mOuterExtendWidth;
        mOuterExtendLineWidth = builder.mOuterExtendLineWidth;
        mDx = builder.dx;
        mCenterText1 = builder.centerText1;
        mCenterText2 = builder.centerText2;
        mCenterTextMargin = builder.centerTextMargin;
        mTotalValue = builder.totalValue;
        invalidate();
    }

    public static final class Builder {
        private List<PieInfo> listData = new ArrayList<>();
        private int pieWidth;
        private double totalValue;
        @ColorInt
        private int backColor;
        private int max;
        private int alpha;
        private int circleWidth;
        private int startDegree;
        private int mInnerTextSize = 40;
        private int mOuterTextSize = 40;
        private int mOuterGrayTextSize = 30;
        private int mOuterExtendWidth = 50;
        private int mOuterExtendLineWidth = 200;
        private int dx = 3;
        private TextInfo centerText1;
        private TextInfo centerText2;
        private int centerTextMargin;

        private ModeEnum mode = ModeEnum.Pie;

        public Builder() {
        }

        public Builder setCenterTextMargin(int centerTextMargin) {
            this.centerTextMargin = centerTextMargin;
            return this;
        }

        public Builder setDx(int dx) {
            this.dx = dx;
            return this;
        }

        public Builder setCenterText1(TextInfo centerText1) {
            this.centerText1 = centerText1;
            return this;
        }

        public Builder setCenterText2(TextInfo centerText2) {
            this.centerText2 = centerText2;
            return this;
        }

        public Builder setOuterExtendLineWidth(int mOuterExtendLineWidth) {
            this.mOuterExtendLineWidth = mOuterExtendLineWidth;
            return this;
        }

        public Builder setInnerTextSize(int mInnerTextSize) {
            this.mInnerTextSize = mInnerTextSize;
            return this;
        }

        public Builder setOuterTextSize(int mOuterTextSize) {
            this.mOuterTextSize = mOuterTextSize;
            return this;
        }

        public Builder setOuterGrayTextSize(int mOuterGrayTextSize) {
            this.mOuterGrayTextSize = mOuterGrayTextSize;
            return this;
        }

        public Builder setOuterExtendWidth(int mOuterExtendWidth) {
            this.mOuterExtendWidth = mOuterExtendWidth;
            return this;
        }

        public Builder setStartDegree(int startDegree) {
            this.startDegree = startDegree;
            return this;
        }

        public Builder setAlpha(int alpha) {
            this.alpha = alpha;
            return this;
        }

        public Builder setCircleWidth(int width) {
            this.circleWidth = width;
            return this;
        }

        public Builder setMode(ModeEnum mode) {
            this.mode = mode;
            return this;
        }

        public Builder setMax(int max) {
            this.max = max;
            return this;
        }

        public Builder setBackgroundColor(@ColorInt int color) {
            this.backColor = color;
            return this;
        }

        public Builder addData(PieInfo pieInfo) {
            totalValue = totalValue + pieInfo.value;
            listData.add(pieInfo);
            calculate();
            return this;
        }

        public Builder addAllData(List<PieInfo> list) {
            listData.addAll(list);
            for (int i = 0; i < list.size(); i++) {
                totalValue = totalValue + listData.get(i).value;
            }
            calculate();
            return this;
        }

        public Builder setWidth(int width) {
            pieWidth = width;
            return this;
        }

        private void calculate() {
            int tatalDx;
            if (listData.size() > 1) {
                tatalDx = listData.size() * dx;
            } else {
                tatalDx = 0;
            }
            for (int i = 0; i < listData.size(); i++) {
                PieInfo pieInfo = listData.get(i);
                BigDecimal bigDecimal1 = new BigDecimal((360 - tatalDx) * pieInfo.value);
                BigDecimal bigDecimal2 = new BigDecimal(totalValue);
                BigDecimal divide = bigDecimal1.divide(bigDecimal2, 0, BigDecimal.ROUND_HALF_UP);
                pieInfo.degree = divide.intValue();
            }
        }
    }

}
