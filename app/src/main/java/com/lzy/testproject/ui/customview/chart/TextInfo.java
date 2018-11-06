package com.lzy.testproject.ui.customview.chart;

import android.support.annotation.ColorInt;

/**
 * Created by LiZhiyu on 2018/11/6.
 */
public class TextInfo {
    public String text;
    @ColorInt
    public int color;
    public int size;

    public TextInfo() {
    }

    public TextInfo(String text, int color, int size) {
        this.text = text;
        this.color = color;
        this.size = size;
    }
}
