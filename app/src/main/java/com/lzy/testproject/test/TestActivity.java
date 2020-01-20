package com.lzy.testproject.test;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.EnvironmentCompat;

import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lzy.testproject.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
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
        new Thread() {
            @Override
            public void run() {
                super.run();
                String s=getJson("langues.json",TestActivity.this);
                makeAll(Environment.getExternalStorageDirectory().getAbsolutePath(),s);
            }
        }.start();

    }
    public static void makeAll(String buildDir,String t) {
        try {
            File file = new File(buildDir + File.separator + "tt");
            if (!file.exists()) {
                file.mkdirs();
            }

            FileOutputStream fos = new FileOutputStream(file.getAbsolutePath() + "/dimens.xml");
            fos.write(t.getBytes());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    }


    public static String getJson(String fileName, Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream is = context.getAssets().open(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
}