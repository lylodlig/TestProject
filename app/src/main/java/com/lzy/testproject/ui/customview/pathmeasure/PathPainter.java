package com.lzy.testproject.ui.customview.pathmeasure;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by LiZhiyu on 2018/10/25.
 */
public class PathPainter extends View {
    private float[] tan;
    private float[] pos;
    private Path mPath;
    private Paint mPaint;
    private PathMeasure mPathMeasure;
    private float mAnimatorValue;
    private Path mDst;
    private float mLength;

    public PathPainter(Context context) {
        super(context);
    }

    public PathPainter(Context context, AttributeSet attrs) {
        super(context, attrs);
        pos = new float[2];
        tan = new float[2];
        mPathMeasure = new PathMeasure();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPath = new Path();
        mPath.addCircle(400, 400, 100, Path.Direction.CW);
        mPathMeasure.setPath(mPath, true);
        mLength = mPathMeasure.getLength();
        mDst = new Path();

        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mAnimatorValue = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();

    }

    public PathPainter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mDst.reset();
        // 硬件加速的BUG
        mDst.lineTo(0, 0);
        float stop = mLength * mAnimatorValue;
        float start = (float) (stop - ((0.5 - Math.abs(mAnimatorValue - 0.5)) * mLength));
        mPathMeasure.getSegment(start, stop, mDst, true);
//        canvas.drawPath(mDst, mPaint);

        mPathMeasure.getPosTan(stop, pos, tan);
        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI);
        canvas.drawPath(mPath, mPaint);
        canvas.drawCircle(pos[0], pos[1], 10, mPaint);
        canvas.save();
        canvas.translate(400, 400);
        canvas.rotate(degrees);
        canvas.drawLine(0, -100, 300, -100, mPaint);
        canvas.restore();
    }
}
