package com.lzy.testproject.ui.customview.chart;


import androidx.annotation.ColorInt;

/**
 * Created by LiZhiyu on 2018/11/6.
 */
public class TextInfo {
    public String text;
    @ColorInt
    public int color;
    public int size;

    public boolean isBold;

    public TextInfo() {
    }

    public TextInfo(String text, int color, int size, boolean isBold) {
        this.text = text;
        this.color = color;
        this.size = size;
        this.isBold = isBold;
    }
}
