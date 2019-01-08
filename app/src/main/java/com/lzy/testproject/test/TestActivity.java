package com.lzy.testproject.test;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.DeviceUtils;
import com.lzy.testproject.R;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);
        Log.i("lzy", "onCreate: " + DeviceUtils.getManufacturer());
    }


    public void bt(View view) {
        Intent intent = new Intent(this, Test2Activity.class);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("lzy", "onStop: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("lzy", "onStart: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("lzy", "onDestroy: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("lzy", "onPause: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("lzy", "onRestart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("lzy", "onResume: ");
    }
}