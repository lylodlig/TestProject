package com.lzy.testproject.ui.animation.value;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lzy.testproject.R;

public class ValueActivity extends AppCompatActivity {
    private ImageView mImageView;
    private int width;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value);
        mImageView = (ImageView) findViewById(R.id.iv);
        findViewById(R.id.bt1).setOnClickListener(onClickListener);
        findViewById(R.id.bt2).setOnClickListener(onClickListener);
        width = getWindowManager().getDefaultDisplay().getWidth();

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt1:
                    marginValueAnimator();
                    break;
                case R.id.bt2:
                    scaleValueAnimator();
                    break;
            }
        }
    };

    private void marginValueAnimator() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, width - mImageView.getWidth());
        //监听变化过程
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //获取当前值
                int animatedValueMargin = (int) animation.getAnimatedValue();
                ViewGroup.MarginLayoutParams
                        layoutParams = (ViewGroup.MarginLayoutParams) mImageView.getLayoutParams();
                layoutParams.leftMargin = animatedValueMargin;
                mImageView.setLayoutParams(layoutParams);

            }
        });
        valueAnimator.setDuration(1000);
        valueAnimator.setRepeatCount(3);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);

        valueAnimator.setTarget(mImageView);
        valueAnimator.start();
    }


    public void scaleValueAnimator() {
        //1.设置目标属性名及属性变化的初始值和结束值
        PropertyValuesHolder mPropertyValuesHolderScaleX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.0f);
        PropertyValuesHolder mPropertyValuesHolderScaleY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.0f);
        ValueAnimator mAnimator = ValueAnimator.ofPropertyValuesHolder(mPropertyValuesHolderScaleX, mPropertyValuesHolderScaleY);
        //2.为目标对象的属性变化设置监听器
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 3.根据属性名获取属性变化的值分别为ImageView目标对象设置X和Y轴的缩放值
                float animatorValueScaleX = (float) animation.getAnimatedValue("scaleX");
                float animatorValueScaleY = (float) animation.getAnimatedValue("scaleY");
                mImageView.setScaleX(animatorValueScaleX);
                mImageView.setScaleY(animatorValueScaleY);

            }
        });
        //4.为ValueAnimator设置自定义的Interpolator
//        mAnimator.setInterpolator(new CustomInterpolator());
        //5.设置动画的持续时间、是否重复及重复次数等属性
        mAnimator.setDuration(2000);
        mAnimator.setRepeatCount(3);
        mAnimator.setRepeatMode(ValueAnimator.REVERSE);
        //6.为ValueAnimator设置目标对象并开始执行动画
        mAnimator.setTarget(mImageView);
        mAnimator.start();
    }

}
