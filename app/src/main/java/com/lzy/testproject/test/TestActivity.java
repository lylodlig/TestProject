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

public class TestActivity extends AppCompatActivity {

    private static final String TAG = "lzy";
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);

        EditText editText= findViewById(R.id.editText);


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e(TAG, "onTextChanged: "+s.toString().length());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
//        try {
//            Thread.sleep(1245);
//            change();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
