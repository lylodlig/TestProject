package com.lzy.testproject.ui.behavior.behavoir;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.lzy.testproject.MyApplication;
import com.lzy.testproject.R;


/**
 * Created by zhouwei on 16/12/16.
 */

public class FloatingHeaderTitleBehavior extends CoordinatorLayout.Behavior<View>{
    private ArgbEvaluator mArgbEvaluator;
    /**
     * Title 的折叠高度
     */
    private int mTitleCollapsedHeight;
    /**
     * titile 初始化Y轴的位置
     */
    private int mTitleInitY ;

    private int mMargin;

    public FloatingHeaderTitleBehavior(Context context, AttributeSet attributeSet){
        mArgbEvaluator = new ArgbEvaluator();
        mTitleCollapsedHeight = context.getResources().getDimensionPixelOffset(R.dimen.collapsedTitleHeight);
        mTitleInitY = context.getResources().getDimensionPixelOffset(R.dimen.title_init_y);
        mMargin = context.getResources().getDimensionPixelOffset(R.dimen.title_margin_left);
    }
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return isDependent(dependency);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {

        float progress = 1.0f - Math.abs(dependency.getTranslationY() / (dependency.getHeight() - getCollapsedHeight()));

        float translationY = (mTitleInitY - mTitleCollapsedHeight) * progress;

        child.setTranslationY(translationY);

        // background change
        int startColor = MyApplication.getApplication().getResources().getColor(R.color.init_bg_color);
        int endColor = MyApplication.getApplication().getResources().getColor(R.color.end_bg_color);
        child.setBackgroundColor((Integer) mArgbEvaluator.evaluate(progress,endColor,startColor));
        //set margin
        int margin = (int) (mMargin * progress);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        params.setMargins(margin,0,margin,0);
//        child.setLayoutParams(params);
        return true;
    }

    private boolean isDependent(View dependency){

        return dependency!=null && dependency.getId() == R.id.header_view;
    }

    private int getCollapsedHeight(){
        return MyApplication.getApplication().getResources().getDimensionPixelOffset(R.dimen.collapsedTitleHeight);
    }
}
