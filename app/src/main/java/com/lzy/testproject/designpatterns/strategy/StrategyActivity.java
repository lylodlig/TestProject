package com.lzy.testproject.designpatterns.strategy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lzy.testproject.R;

public class StrategyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strategy);

        MoveCalculate moveCalculate = new MoveCalculate();
        moveCalculate.setmStrategy(new VipStrategy());
//        moveCalculate.setmStrategy(new NormalStrategy());
        int price = moveCalculate.calculate();
        Log.i("lzy", "vipPrice: " + price);
    }
}
