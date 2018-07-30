package com.lzy.testproject.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

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

        DecimalFormat myformat = new DecimalFormat();
        myformat.applyPattern("##,###.00");
        Log.e("lzy", "" + myformat.format(3424241234.12));
        Log.e("lzy", "" + myformat.format(1234));
        Log.e("lzy", "" + myformat.format(1234.121));
        Log.e("lzy", "" + myformat.format(0.12));
        Class<? extends DecimalFormat> aClass = myformat.getClass();
        io.reactivex.Observable.just(1, 2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}