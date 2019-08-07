package com.lzy.testproject.ui.customview.calendar;

import android.Manifest;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;
import com.leeiidesu.permission.PermissionHelper;
import com.leeiidesu.permission.callback.OnPermissionResultListener;
import com.lzy.testproject.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    private CalendarView mCalendarView;
    private CalendarLayout mCalendarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar2);
        PermissionHelper.with(this)
                .permissions(Manifest.permission.WRITE_CALENDAR, Manifest.permission.READ_CALENDAR)
                .listener(new OnPermissionResultListener() {
                    @Override
                    public void onGranted() {
//                        new MyThread().start();
                    }

                    @Override
                    public void onFailed(ArrayList<String> deniedPermissions) {

                    }
                }).request();

        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mCalendarLayout = (CalendarLayout) findViewById(R.id.calendarLayout);

        List<com.haibin.calendarview.Calendar> schemes = new ArrayList<>();
        int year = mCalendarView.getCurYear();
        int month = mCalendarView.getCurMonth();
//        schemes.add(getSchemeCalendar(year, month, 3, R.color.pd_red, "假"));
        schemes.add(getSchemeCalendar(year, month, 6, 0xFFe69138, "事"));
        schemes.add(getSchemeCalendar(year, month, 10, 0xFFdf1356, "议"));
        schemes.add(getSchemeCalendar(year, month, 26, 0xFFdf1356, "议"));
        mCalendarView.setSchemeDate(schemes);
    }

    private com.haibin.calendarview.Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        com.haibin.calendarview.Calendar calendar = new com.haibin.calendarview.Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        calendar.addScheme(new com.haibin.calendarview.Calendar.Scheme());
        calendar.addScheme(0xFF008800, "假");
        calendar.addScheme(0xFF008800, "节");
        return calendar;
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
