package com.lzy.testproject.ui.behavior.behavoir;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;

import com.lzy.testproject.MyApplication;
import com.lzy.testproject.R;

/**
 * 自定义Behavior ：实现RecyclerView(或者其他可滑动View，如：NestedScrollView) 滑动覆盖header 的效果
 * Created by zhouwei on 16/12/19.
 */

public class CoverHeaderScrollBehavior extends CoordinatorLayout.Behavior<View> {
    public static final String TAG = "CoverHeaderScroll";

    public CoverHeaderScrollBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        Log.i(TAG, "onLayoutChild.....");
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        if (params != null && params.height == CoordinatorLayout.LayoutParams.MATCH_PARENT) {
            child.layout(0, 0, parent.getWidth(), parent.getHeight());
            child.setTranslationY(getHeaderHeight());
            return true;
        }

        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        // 在这个方法里面只处理向上滑动
//        if (dy < 0) {
//            return;
//        }
//        float transY = child.getTranslationY() - dy;
//        Log.i(TAG, "transY:" + transY + "++++child.getTranslationY():" + child.getTranslationY() + "---->dy:" + dy);
//        if (transY >= 0 && transY <= getHeaderHeight()) {
//            child.setTranslationY(transY);
//            consumed[1] = dy;
//        }
    }

    @Override
    public boolean onNestedFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, float velocityX, float velocityY, boolean consumed) {
        Log.i("lzy", "onNestedFling: " + velocityY + "   " + child.getTranslationY());
        if (velocityY > 1000) {
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(child.getY(), child.getY() - getHeaderHeight());
            valueAnimator.setDuration(1000);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    child.setTranslationY((Float) animation.getAnimatedValue());
                }
            });
            valueAnimator.start();
        } else if (velocityY < -1000) {
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(child.getY(), child.getY() + getHeaderHeight());
            valueAnimator.setDuration(1000);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    child.setTranslationY((Float) animation.getAnimatedValue());
                }
            });
            valueAnimator.start();
        }
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        float transY = child.getTranslationY() - dyUnconsumed;
//        Log.i(TAG, "transY:" + transY + "++++child.getTranslationY():" + child.getTranslationY() + "---->dy:" + dy);
        if (transY >= 0 && transY <= getHeaderHeight()) {
            child.setTranslationY(transY);

//            consumed[1] = dy;
        }
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
//        // 在这个方法里只处理向下滑动
//        if (dyUnconsumed > 0) {
//            return;
//        }
//        float transY = child.getTranslationY() - dyUnconsumed;
//        Log.i(TAG, "------>transY:" + transY + "****** child.getTranslationY():" + child.getTranslationY() + "--->dyUnconsumed" + dxUnconsumed);
//        if (transY > 0 && transY < getHeaderHeight()) {
//            child.setTranslationY(transY);
//        }
    }

    /**
     * 获取Header 高度
     *
     * @return
     */
    public int getHeaderHeight() {
        return MyApplication.getApplication().getResources().getDimensionPixelOffset(R.dimen.header_height);
    }

}
