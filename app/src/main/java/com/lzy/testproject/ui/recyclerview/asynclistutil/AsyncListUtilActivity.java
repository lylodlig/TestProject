package com.lzy.testproject.ui.recyclerview.asynclistutil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lzy.testproject.R;
//AsyncListUtil 在 support-v7:23就存在了。它是异步加载数据的工具，它一般用于加载数据库数据
public class AsyncListUtilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_list);
        RecyclerView mRecyclerView = findViewById(R.id.rv);
        MyAsyncListUtil myAsyncListUtil = new MyAsyncListUtil(mRecyclerView);
        AsyncListUtilAdapter mAdapter = new AsyncListUtilAdapter(this, myAsyncListUtil);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }
}
