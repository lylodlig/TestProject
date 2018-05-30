package com.lzy.testproject.designpatterns.strategy;

/**
 * Created by LiZhiyu on 2018/5/30.
 */
public class NormalStrategy implements PriceCalculate {
    @Override
    public int caculate() {
        return 50;
    }
}
