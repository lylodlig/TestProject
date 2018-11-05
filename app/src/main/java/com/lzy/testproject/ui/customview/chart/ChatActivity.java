package com.lzy.testproject.ui.customview.chart;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lzy.testproject.R;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        PieView pieView = findViewById(R.id.pieView);

        PieInfo pieInfo1 = new PieInfo();
        pieInfo1.value = 20;
        pieInfo1.describe = "退款笔数：30";
        pieInfo1.describe2 = "金额总计：23432";
        pieInfo1.title = "业务变更";
        pieInfo1.color = Color.RED;
        PieInfo pieInfo2 = new PieInfo();
        pieInfo2.value = 30;
        pieInfo2.describe = "退款笔数：30";
        pieInfo2.describe2 = "金额总计：23432";
        pieInfo2.title = "客户原因";
        pieInfo2.color = Color.BLACK;
        PieInfo pieInfo3 = new PieInfo();
        pieInfo3.value = 10;
        pieInfo3.describe = "退款笔数：30";
        pieInfo3.title = "其他原因";
        pieInfo3.color = Color.GREEN;
        PieInfo pieInfo4 = new PieInfo();
        pieInfo4.value = 10;
        pieInfo4.describe = "退款笔数：30";
        pieInfo4.title = "其他原因";
        pieInfo4.color = Color.YELLOW;

        PieView.Builder builder = new PieView.Builder()
                .setWidth(250)
                .setCircleWidth(300)
                .setMax(25)
                .setAlpha(50)
                .setMode(PieView.ModeEnum.PieFull)
                .addData(pieInfo1)
                .addData(pieInfo2)
                .addData(pieInfo3)
                .addData(pieInfo4);
        pieView.setBuilder(builder);
    }
}
