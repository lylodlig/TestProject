package com.lzy.testproject.ui.recyclerview.snaphelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;

import com.lzy.testproject.R;

/**
 * 用于控制RecyclerView滑动停止后Item的对齐方式。
 * 默认提供了两种对齐方式PagerSnapHelper 与 LinearSnapHelper。PagerSnapHelper 和ViewPage效果一样，一次滑动一页。
 * 也可以自定义对齐方式
 */
public class SnapHelperActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snap_helper);
        RecyclerView mRecyclerView1 = findViewById(R.id.rv1);
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        SnapHelperAdapter mSnapHelperAdapter1 = new SnapHelperAdapter(this);
        PagerSnapHelper mPagerSnapHelper = new PagerSnapHelper();
        mPagerSnapHelper.attachToRecyclerView(mRecyclerView1);
        mRecyclerView1.setAdapter(mSnapHelperAdapter1);

        RecyclerView mRecyclerView2 = findViewById(R.id.rv2);
        mRecyclerView2.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        SnapHelperAdapter mSnapHelperAdapter2 = new SnapHelperAdapter(this);
        LinearSnapHelper mLinearSnapHelper = new LinearSnapHelper();
//        MySnapHelper mLinearSnapHelper = new MySnapHelper();
        mLinearSnapHelper.attachToRecyclerView(mRecyclerView2);
        mRecyclerView2.setAdapter(mSnapHelperAdapter2);
    }
}
