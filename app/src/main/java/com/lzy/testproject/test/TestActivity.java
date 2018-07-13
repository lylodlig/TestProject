package com.lzy.testproject.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lzy.testproject.R;

public class TestActivity extends AppCompatActivity {

    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);

        test();

        try {
            Thread.sleep(1245);
            change();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void change() {
        Log.e("lzy", "--" + String.valueOf(count));
        count = 2;
    }

    private synchronized void test() {
        Task1 task1 = new Task1();
        task1.start();
    }

    class Task1 extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(1000);
                Log.e("lzy", String.valueOf(count));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            test();
        }
    }


}
