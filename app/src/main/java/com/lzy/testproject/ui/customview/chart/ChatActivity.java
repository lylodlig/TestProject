package com.lzy.testproject.ui.customview.chart;

import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.lzy.testproject.R;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

//        PieView pieView = findViewById(R.id.pieView);
//        HistogramView histogramView = findViewById(R.id.histogramView);
//        PieInfo info = new PieInfo();
//        info.value = 60;
//        info.color = Color.RED;
//        info.title = "60";
//        PieInfo info2 = new PieInfo();
//        info2.value = 23;
//        info2.color = Color.BLUE;
//        PieInfo info3 = new PieInfo();
//        info3.value = 10;
//        info3.color = Color.CYAN;
//        List<PieInfo> list = new ArrayList<>();
//        list.add(info);
//        list.add(info2);
//        list.add(info3);
//        histogramView.setType(HistogramView.ModeEnum.Vertical).setData(list, 80);
//        histogramView.setOnClickListener(new HistogramView.OnClickListener() {
//            @Override
//            public void onClick(PieInfo pieInfo) {
//                Toast.makeText(ChatActivity.this, "pieInfo.value:" + pieInfo.value, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        PieInfo pieInfo1 = new PieInfo();
//        pieInfo1.value = 20;
//        pieInfo1.describe = "退款笔数：30";
//        pieInfo1.describe2 = "金额总计：23432";
//        pieInfo1.title = "业务变更";
//        pieInfo1.color = Color.RED;
//        PieInfo pieInfo2 = new PieInfo();
//        pieInfo2.value = 30;
//        pieInfo2.describe = "退款笔数：30";
//        pieInfo2.describe2 = "金额总计：23432";
//        pieInfo2.title = "客户原因";
//        pieInfo2.color = Color.BLACK;
//        PieInfo pieInfo3 = new PieInfo();
//        pieInfo3.value = 10;
//        pieInfo3.describe = "退款笔数：30";
//        pieInfo3.title = "其他原因";
//        pieInfo3.color = Color.GREEN;
//        PieInfo pieInfo4 = new PieInfo();
//        pieInfo4.value = 10;
//        pieInfo4.describe = "退款笔数：30";
//        pieInfo4.title = "其他原因";
//        pieInfo4.color = Color.YELLOW;
//
//        PieView.Builder builder = new PieView.Builder()
//                .setCenterText1(new TextInfo("案件数量总是", Color.GRAY, 30))
//                .setCenterText2(new TextInfo("1245", Color.BLACK, 30))
//                .setCenterTextMargin(2)
//                .setPieWidth(150)
//                .setCircleRadius(300)
//                .setMax(25)
//                .setAlpha(50)
//                .setMode(PieView.ModeEnum.Pie)
//                .addData(pieInfo1)
//                .addData(pieInfo2)
//                .addData(pieInfo3)
//                .addData(pieInfo4);
//        pieView.setBuilder(builder);
//        pieView.show();
    }
}
