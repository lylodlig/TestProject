package com.lzy.testproject.other.calendar;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;

import com.leeiidesu.permission.PermissionHelper;
import com.leeiidesu.permission.callback.OnPermissionResultListener;
import com.lzy.testproject.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        DatePicker datePicker = new DatePicker(this);
        PermissionHelper.with(this)
                .permissions(Manifest.permission.WRITE_CALENDAR, Manifest.permission.READ_CALENDAR)
                .listener(new OnPermissionResultListener() {
                    @Override
                    public void onGranted() {
                        new MyThread().start();
                    }

                    @Override
                    public void onFailed(ArrayList<String> deniedPermissions) {

                    }
                }).request();

    }

    private class MyThread extends Thread {
        @Override
        public void run() {
            long startMillis = 0;
            long endMillis = 0;
            Calendar beginTime = Calendar.getInstance();
            beginTime.set(2018, 8, 4, 10, 50);  //注意，月份的下标是从0开始的
            startMillis = beginTime.getTimeInMillis();  //插入日历时要取毫秒计时
            Calendar endTime = Calendar.getInstance();
            endTime.set(2018, 8, 4, 9, 52);
            endMillis = endTime.getTimeInMillis();
            CalendarReminderUtils.addCalendarEvent(CalendarActivity.this, "测试Title222", "description", startMillis, endMillis, 1);

//            CalendarReminderUtils.deleteCalendarEvent(CalendarActivity.this, "测试Title222");
        }
    }
}
