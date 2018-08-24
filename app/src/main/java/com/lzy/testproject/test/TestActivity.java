package com.lzy.testproject.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lzy.testproject.R;

import java.text.DecimalFormat;
import java.util.Observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class TestActivity extends AppCompatActivity {

    private static final String TAG = "lzy";
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);


    }

    public void bt(View view) {
        ARouter.getInstance().build("/lzy/constraint/TestActivity").navigation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}