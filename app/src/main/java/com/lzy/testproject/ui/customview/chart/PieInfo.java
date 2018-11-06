package com.lzy.testproject.ui.customview.chart;

import android.graphics.RectF;
import android.support.annotation.ColorInt;

/**
 * Created by LiZhiyu on 2018/11/2.
 */
public class PieInfo {
    @ColorInt
    public int color;

    public String describe="";
    public String describe2="";
    public String title;
    //存储区域
    public RectF rectF;


    public double value;

    public int degree;

    public PieInfo() {
    }

    public PieInfo(int color, double value) {
        this.color = color;
        this.value = value;
    }
}
