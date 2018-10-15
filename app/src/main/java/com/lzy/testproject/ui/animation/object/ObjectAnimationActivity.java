package com.lzy.testproject.ui.animation.object;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.lzy.testproject.R;

public class ObjectAnimationActivity extends AppCompatActivity {

    private int screenWidth;
    private int screenHeight;
    private ImageView mImageView;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animation);
        screenWidth = getWindowManager().getDefaultDisplay().getWidth();
        screenHeight = getWindowManager().getDefaultDisplay().getHeight();
        mButton = (Button) findViewById(R.id.bt);

        mImageView = (ImageView) findViewById(R.id.iv);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewWrapper wrapper = new ViewWrapper(mButton);
                ObjectAnimator.ofInt(wrapper, "width", 1000).setDuration(1000).start();
            }
        });
    }


    //透明度变化动画
    public void alpha(View view) {
//        1、通过调用ofFloat()方法创建ObjectAnimator对象，并设置目标对象、需要改变的目标属性名、初始值和结束值；
        ObjectAnimator mAnimatorAlpha = ObjectAnimator.ofFloat(mImageView, "alpha", 1.0f, 0.0f);
        //2、设置动画的持续时间、是否重复及重复次数属性；
        mAnimatorAlpha.setRepeatMode(ValueAnimator.REVERSE);
        mAnimatorAlpha.setRepeatCount(1);
        mAnimatorAlpha.setDuration(1000);
        //3、启动动画
        mAnimatorAlpha.start();
    }

    //翻转动画，翻转360度
    public void flip(View view) {
        ObjectAnimator visibleToInVisable = ObjectAnimator.ofFloat(mImageView, "rotationX", 0.0f, 360.0f);
        //设置插值器
        visibleToInVisable.setInterpolator(new LinearInterpolator());
        visibleToInVisable.setDuration(1000);
        visibleToInVisable.start();
    }

    //缩放动画
    public void scale(View view) {
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.scale_anim);
        animator.setTarget(mImageView);
        animator.start();
    }

    //平移动画
    public void translate(View view) {
        ObjectAnimator mAnimatorTranslateX = ObjectAnimator.ofFloat(mImageView, "translationX", 0.0f, screenWidth / 2);
        mAnimatorTranslateX.setRepeatMode(ValueAnimator.REVERSE);
        mAnimatorTranslateX.setRepeatCount(1);
        mAnimatorTranslateX.setDuration(1000);

        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(mImageView, "translationY", 0.0f, screenHeight / 2);
        mAnimatorTranslateY.setRepeatMode(ValueAnimator.REVERSE);
        mAnimatorTranslateY.setRepeatCount(1);
        mAnimatorTranslateY.setDuration(1000);

        mAnimatorTranslateX.start();
        mAnimatorTranslateY.start();
    }

    //旋转动画
    public void rotate(View view) {
        ObjectAnimator mAnimatorRotate = ObjectAnimator.ofFloat(mImageView, "rotation", 0.0f, 360.0f);
        mAnimatorRotate.setRepeatMode(ValueAnimator.REVERSE);
        mAnimatorRotate.setRepeatCount(1);
        mAnimatorRotate.setDuration(2000);

        mAnimatorRotate.start();
    }

    public void set(View v) {
        codeAnimatorSet(mImageView);
    }

    public void set2(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mImageView, "alpha", 0.0f, 1.0f);
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mImageView.setAlpha(value);
                mImageView.setScaleX(1 - value * 0.5f);
            }
        });
        animator.start();

        //采用下面代码实现效果一样

//        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0.0f, 1.0f);
//        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.0f);
//
//        ObjectAnimator animator1 = ObjectAnimator.ofPropertyValuesHolder(mImageView, alpha, scaleX);
//        animator1.setDuration(1000);
//        animator1.start();

    }

    public void codeAnimatorSet(ImageView mImageView) {
        AnimatorSet mAnimatorSet = new AnimatorSet();

        ObjectAnimator mAnimatorSetRotateX = ObjectAnimator.ofFloat(mImageView, "rotationX", 0.0f, 360.0f);
        mAnimatorSetRotateX.setDuration(3000);

        ObjectAnimator mAnimatorSetRotateY = ObjectAnimator.ofFloat(mImageView, "rotationY", 0.0f, 360.0f);
        mAnimatorSetRotateY.setDuration(3000);

        ObjectAnimator mAnimatorScaleX = ObjectAnimator.ofFloat(mImageView, "scaleX", 1.0f, 0.2f);
        mAnimatorScaleX.setRepeatCount(1);
        mAnimatorScaleX.setRepeatMode(ValueAnimator.REVERSE);
        mAnimatorScaleX.setDuration(1500);

        ObjectAnimator mAnimatorScaleY = ObjectAnimator.ofFloat(mImageView, "scaleY", 1.0f, 0.2f);
        mAnimatorScaleY.setRepeatCount(1);
        mAnimatorScaleY.setRepeatMode(ValueAnimator.REVERSE);
        mAnimatorScaleY.setDuration(1500);

        ObjectAnimator mAnimatorScaleY2 = ObjectAnimator.ofFloat(mImageView, "scaleY", 1.0f, 0.2f);
        mAnimatorScaleY2.setRepeatCount(1);
        mAnimatorScaleY2.setRepeatMode(ValueAnimator.REVERSE);
        mAnimatorScaleY2.setDuration(1500);

        mAnimatorSet.play(mAnimatorSetRotateY)
                .with(mAnimatorScaleX)
                .with(mAnimatorScaleY)
                .before(mAnimatorSetRotateX).before(mAnimatorScaleY2);
//        mAnimatorSet.playTogether();
        mAnimatorSet.start();
    }

    //用此类来封装View，间接提供get、set方法
    private static class ViewWrapper {
        private View mView;

        public ViewWrapper(View view) {
            mView = view;
        }

        public void setWidth(int width) {
            mView.getLayoutParams().width = width;
            mView.requestLayout();
        }

        public int getWidth() {
            return mView.getLayoutParams().width;
        }
    }
}
