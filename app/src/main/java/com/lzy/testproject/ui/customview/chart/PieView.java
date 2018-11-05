package com.lzy.testproject.ui.customview.chart;

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
    private Paint mOuterTextPaint = new Paint();
    private Paint mOuterTextPaintGray = new Paint();
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

        mOuterTextPaint.setAntiAlias(true);
        mOuterTextPaint.setColor(mOuterTextColor);
        mOuterTextPaint.setTextSize(mOuterTextSize);

        mOuterTextPaintGray.setAntiAlias(true);
        mOuterTextPaintGray.setColor(mOuterTextColorGray);
        mOuterTextPaintGray.setTextSize(mOuterGrayTextSize);


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
                for (int i = 0; i < mListData.size(); i++) {
                    mPaint.setStrokeWidth(mPieWidth);
                    mPaint.setStyle(Paint.Style.STROKE);
                    PieInfo pieInfo = mListData.get(i);
                    mPaint.setColor(pieInfo.color);
                    canvas.drawArc(rectF, startDegree, pieInfo.degree + 2, false, mPaint);


                    //画文字
                    int centerDegree = startDegree + pieInfo.degree / 2;
                    BigDecimal bigDecimal1 = new BigDecimal(100 * pieInfo.degree);
                    BigDecimal bigDecimal2 = new BigDecimal(360);
                    bigDecimal1.setScale(1, BigDecimal.ROUND_HALF_UP);
                    BigDecimal divide = bigDecimal1.divide(bigDecimal2, 0, BigDecimal.ROUND_HALF_UP);
                    int raduis = mCircleWidth - mPieWidth / 2;
                    int innerTextX = (int) (Math.cos(2 * Math.PI / 360 * centerDegree) * raduis) + mWidth / 2;
                    int innerTextY = (int) (Math.sin(2 * Math.PI / 360 * centerDegree) * raduis) + mHeight / 2;
                    canvas.drawText(divide.intValue() + "%", innerTextX, innerTextY, mInnerTextPaint);

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
                    path.lineTo((float) (outterTextX + 80 * x), (float) (outterTextY + 80 * y));
                    path.lineTo((float) (outterTextX + 400 * x), (float) (outterTextY + 80 * y));
                    canvas.drawPath(path, mPaint);

                    Rect rect = new Rect();
                    Rect rectGray = new Rect();
                    mOuterTextPaint.getTextBounds(pieInfo.title, 0, pieInfo.title.length(), rect);
                    mOuterTextPaintGray.getTextBounds(pieInfo.describe, 0, pieInfo.describe.length(), rectGray);

                    if (y > 0) {
                        if (x > 0) {
                            canvas.drawText(pieInfo.title, (float) (outterTextX + 80 * x), (float) (outterTextY + 80 * y - 10), mOuterTextPaint);
                            canvas.drawText(pieInfo.describe, (float) (outterTextX + 80 * x), (float) (outterTextY + 80 * y + 10 + rectGray.height()), mOuterTextPaintGray);
                        } else {
                            canvas.drawText(pieInfo.title, (float) (outterTextX + 80 * x) - rect.width() - 10, (float) (outterTextY + 80 * y - 10), mOuterTextPaint);
                            canvas.drawText(pieInfo.describe, (float) (outterTextX + 80 * x) - rectGray.width() - 10, (float) (outterTextY + 80 * y + 10 + rectGray.height()), mOuterTextPaintGray);
                        }
                    } else {
                        if (x > 0) {
                            canvas.drawText(pieInfo.title, (float) (outterTextX + 80 * x), (float) (outterTextY + 80 * y + rect.height() + 10), mOuterTextPaint);
                            canvas.drawText(pieInfo.describe, (float) (outterTextX + 80 * x), (float) (outterTextY + 80 * y - 10- rectGray.height()), mOuterTextPaintGray);
                        } else {
                            canvas.drawText(pieInfo.title, (float) (outterTextX + 80 * x) - rect.width() - 10, (float) (outterTextY + 80 * y + rect.height() + 10), mOuterTextPaint);
                            canvas.drawText(pieInfo.describe, (float) (outterTextX + 80 * x) - rectGray.width() - 10, (float) (outterTextY + 80 * y - 10 - rectGray.height()), mOuterTextPaintGray);
                        }
                    }

                    startDegree = startDegree + pieInfo.degree;
                }
                break;
            case Pie:

                if (mListData.size() > 0) {

                    PieInfo pieInfo = mListData.get(0);
                    mPaint.setColor(pieInfo.color);
                    mPaint.setAlpha(mAlpha);
                    canvas.drawArc(rectF, 0, 360, false, mPaint);
                    mPaint.setColor(pieInfo.color);
                    canvas.drawArc(rectF, -90, (float) (360 * pieInfo.value / mMaxValue), false, mPaint);

                    Path path = new Path();
                    path.lineTo(0, 0);
                    path.lineTo(100, 10);
                    canvas.drawText("sfs", 0, 0, mPaint);
//                    canvas.drawPath(path,mPaint);
                }
            case ColumnHorizontal:
                break;
            case ColumnHorizontalFull:
                break;
            case ColumnVertical:
                break;

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

        private ModeEnum mode = ModeEnum.Pie;

        public Builder() {
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
            int dx = 0;
//            if (listData.size() > 0)
//                dx = (listData.size() - 1) * 1;
            for (int i = 0; i < listData.size(); i++) {
                PieInfo pieInfo = listData.get(i);
                BigDecimal bigDecimal = new BigDecimal(((360 - dx) * pieInfo.value / totalValue));
                bigDecimal.setScale(0, BigDecimal.ROUND_HALF_UP);
//                pieInfo.degree = (int) ((360 - dx) * pieInfo.value / totalValue);
                pieInfo.degree = bigDecimal.intValue();
            }
        }
    }

}
