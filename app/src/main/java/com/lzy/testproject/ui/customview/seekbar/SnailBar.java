package com.lzy.testproject.ui.customview.seekbar;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Looper;
import androidx.appcompat.widget.AppCompatSeekBar;
import android.util.AttributeSet;

import com.lzy.testproject.R;

public class SnailBar extends AppCompatSeekBar {

    public SnailBar(Context context) {
        super(context);
        init();
    }


    public SnailBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SnailBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Looper.prepare();
        this.setMax(100);
        this.setThumbOffset(dip2px(getContext(), 20));
        this.setBackgroundResource(R.mipmap.sbg);
        int padding = dip2px(getContext(), (float) 20);
        this.setPadding(padding, padding, padding, padding);
        this.setProgressDrawable(getResources().getDrawable(R.drawable.snailbar_define_style));
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        AnimationDrawable drawable = (AnimationDrawable) this.getThumb();
        drawable.start();
    }

    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
