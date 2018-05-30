package com.lzy.testproject.designpatterns.strategy;

/**
 * Created by LiZhiyu on 2018/5/30.
 */
public class MoveCalculate {

    private static PriceCalculate mStrategy;

    public void setmStrategy(PriceCalculate mStrategy) {
        this.mStrategy = mStrategy;
    }

    public int calculate() {
        return mStrategy.caculate();
    }
}
