package com.lzy.testproject.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lzy.testproject.R;

public class Test2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("lzy", "onCreate: Test2");
        setContentView(R.layout.activity_test4);
    }

    @Override
    protected void onStart() {
        Log.i("lzy", "onStart: Test2");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i("lzy", "onResume: Test2");
        super.onResume();
    }
}
