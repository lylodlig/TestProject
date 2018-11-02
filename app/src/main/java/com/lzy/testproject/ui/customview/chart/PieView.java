package com.lzy.testproject.ui.customview.chart;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiZhiyu on 2018/11/2.
 */
public class PieView extends View {
    private List<PieInfo> mListData = new ArrayList<>();
    private int mPieWidth = 20;
    private Paint mPaint = new Paint();
    private Paint mTextPaint = new Paint();
    private int mWidth;
    private int mHeight;

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
        mTextPaint.setAntiAlias(true);

        mPaint.setStrokeWidth(mPieWidth);
        mPaint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int startDegree = 0;
        for (int i = 0; i < mListData.size(); i++) {
            PieInfo pieInfo = mListData.get(i);
            mPaint.setColor(pieInfo.color);
            canvas.drawArc(0, 0, mWidth, mHeight, startDegree, pieInfo.degree, true, mPaint);
            startDegree = pieInfo.degree;
        }
    }

    public void setBuilder(Builder builder) {
        mListData = builder.listData;
        mPieWidth = builder.pieWidth;
        invalidate();
    }

    private static final class Builder {
        private List<PieInfo> listData = new ArrayList<>();
        private int pieWidth;
        private double totalValue;

        public Builder() {
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
            for (int i = 0; i < listData.size(); i++) {
                PieInfo pieInfo = listData.get(i);
                pieInfo.degree = (int) (360 * pieInfo.value / totalValue);
            }
        }
    }

}
