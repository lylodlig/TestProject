package com.lzy.testproject.ui.behavior.behavoir;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lzy.testproject.R;
import com.lzy.testproject.test.MyService;

import java.util.ArrayList;
import java.util.List;

public class Behavoir1Activity extends AppCompatActivity {

    private View mSwipeLayout;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavoir1);
        initView1();
        initView2();
        startService(new Intent(this, MyService.class));
    }

    private void initView2() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        MyAdapter adapter = new MyAdapter();
        mRecyclerView.setAdapter(adapter);
        adapter.setData(mockData());
        adapter.notifyDataSetChanged();
    }

    private void initView1() {
        mSwipeLayout = findViewById(R.id.swipe_layout);
        SwipeDismissBehavior swipe = new SwipeDismissBehavior();

        /**
         * //设置滑动的方向，有3个值
         *
         * 1，SWIPE_DIRECTION_ANY 表示向左像右滑动都可以，
         * 2，SWIPE_DIRECTION_START_TO_END，只能从左向右滑
         * 3，SWIPE_DIRECTION_END_TO_START，只能从右向左滑
         */
        swipe.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_START_TO_END);

        swipe.setStartAlphaSwipeDistance(0f);

        swipe.setSensitivity(0.2f);

        swipe.setListener(new SwipeDismissBehavior.OnDismissListener() {
            @Override
            public void onDismiss(View view) {
                Log.e("lzy", "------>onDissmiss");
            }

            @Override
            public void onDragStateChanged(int state) {
                Log.e("lzy", "------>onDragStateChanged");
            }
        });

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) mSwipeLayout.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.setBehavior(swipe);
        }
    }


    private List<String> mockData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data.add("item:" + i);
        }
        return data;
    }


    public static class MyAdapter extends RecyclerView.Adapter {
        private List<String> mData;

        public void setData(List<String> data) {
            mData = data;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, null));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((MyViewHolder) holder).mTextView.setText(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData == null ? 0 : mData.size();
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.item_content_text);
        }
    }
}
