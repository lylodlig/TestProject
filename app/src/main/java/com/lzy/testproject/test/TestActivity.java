package com.lzy.testproject.test;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.DeviceUtils;
import com.lzy.testproject.R;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);
        Log.i("lzy", "onCreate: " + DeviceUtils.getManufacturer());
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("lzy", "onStart: ");
    }

    public void bt(View view) {
        getApplicationContext().startActivity(new Intent());
//        ARouter.getInstance().build("/lzy/constraint/TestActivity").navigation();
//        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.notify);
//        mediaPlayer.start();
        float c = 121485;
        float val = c / 10000;

        String format = new DecimalFormat("#.0").format(val);
        BigDecimal bigDecimal = new BigDecimal(val);
        bigDecimal.setScale(1, BigDecimal.ROUND_HALF_UP);
        Log.i("lzy", "bt: " + format);
        Log.i("lzy", "bt: master");
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