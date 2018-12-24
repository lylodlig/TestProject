package com.lzy.testproject.ui.calendar

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup
import android.util.Log
import com.lzy.testproject.R
import kotlinx.android.synthetic.main.activity_calendar1.*
import me.drakeet.multitype.MultiTypeAdapter
import java.util.*

class CalendarActivity : AppCompatActivity() {
    var dataList = mutableListOf<Any>()
    var selectStartTime = "1970-01-01"
    var selectEndTime = "2099-12-12"
    var calendar: Calendar = Calendar.getInstance(Locale.CHINA)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar1)

        recyclerView.layoutManager = GridLayoutManager(this, 7)
        (recyclerView.layoutManager as GridLayoutManager).spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (dataList[position] is Title) {
                    7
                } else 1
            }
        }
        recyclerView.adapter = MultiTypeAdapter(dataList)

        (recyclerView.adapter as MultiTypeAdapter).register(DateInfo::class.java, DateViewBinder())
        (recyclerView.adapter as MultiTypeAdapter).register(Title::class.java, TitleViewBinder())

        init()
    }

    fun init() {
        dataList.addAll(getMonthData(calendar))
        for (index in 1..3) {
            calendar.add(Calendar.MONTH, 1)
            Log.d("lzy","===${calendar.get(Calendar.MONTH)+1}")
            dataList.addAll(getMonthData(calendar))
        }
        recyclerView.adapter.notifyDataSetChanged()
    }

    fun getMonthData(calendar: Calendar): MutableList<Any> {
        var dataList = mutableListOf<Any>()
        dataList.add(Title("${calendar.get(Calendar.YEAR)}年${calendar.get(Calendar.MONTH) + 1}月"))
//        val calendar = Calendar.getInstance(Locale.CHINA)      //获取China区Calendar实例，实际是GregorianCalendar的一个实例
//        calendar.time = date//初始化日期
        val maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)  //获得当前日期所在月份有多少天（或者说day的最大值)，用于后面的计算

        val calendarClone: Calendar = calendar.clone() as Calendar//克隆一个Calendar再进行操作，避免造成混乱
        calendarClone.set(Calendar.DAY_OF_MONTH, 1)  //将日期调到当前月份的第一天
        val startDayOfWeek = calendarClone.get(Calendar.DAY_OF_WEEK) //获得当前日期所在月份的第一天是星期几
        calendarClone.set(Calendar.DAY_OF_MONTH, maxDay) //将日期调到当前月份的最后一天
        val endDayOfWeek = calendarClone.get(Calendar.DAY_OF_WEEK) //获得当前日期所在月份的最后一天是星期几

        /**
         * 计算上一个月在本月日历页出现的那几天.
         * 比如，startDayOfWeek = 3，表示当月第一天是星期二，所以日历向前会空出2天的位置，那么让上月的最后两天显示在星期日和星期一的位置上.
         */
        val startEmptyCount = startDayOfWeek - 1 //上月在本月日历页因该出现的天数。
        val preCalendar = calendar.clone() as Calendar  //克隆一份再操作
        preCalendar.set(Calendar.DAY_OF_MONTH, 1) //将日期调到当月第一天
        preCalendar.add(Calendar.DAY_OF_MONTH, -startEmptyCount) //向前推移startEmptyCount天
        for (index in 1..startEmptyCount) {
            val dateInfo = DateInfo() //使用DateInfo来储存所需的相关信息
            dateInfo.date = preCalendar.time
            dateInfo.type = DateInfo.PRE_MONTH //标记日期信息的类型为上个月
            dataList.add(dateInfo) //将日期添加到数组中
            preCalendar.add(Calendar.DAY_OF_MONTH, 1) //向后推移一天
        }


        /**
         * 计算当月的每一天日期
         */
        val calendarCur = calendar.clone() as Calendar  //克隆一份再操作
        calendarCur.set(Calendar.DAY_OF_MONTH, 1) //由于是获取当月日期信息，所以直接操作当月Calendar即可。将日期调为当月第一天
        for (index in 1..maxDay) {
            val dateInfo = DateInfo() //使用DateInfo来储存所需的相关信息
            dateInfo.date = calendarCur.time
            dateInfo.type = DateInfo.CURRENT_MONTH //标记日期信息的类型为当月
            dataList.add(dateInfo) //将日期添加到数组中
            calendarCur.add(Calendar.DAY_OF_MONTH, 1) //向后推移一天
        }

        /**
         * 计算下月在本月日历页出现的那几天。
         * 比如，endDayOfWeek = 6，表示当月第二天是星期五，所以日历向后会空出1天的位置，那么让下月的第一天显示在星期六的位置上。
         */
        val endEmptyCount = 7 - endDayOfWeek; //下月在本月日历页上因该出现的天数
        val afterCalendar = calendar.clone() as Calendar //同样，克隆一份在操作
        for (index in 1..endEmptyCount) {
            val dateInfo = DateInfo() //使用DateInfo来储存所需的相关信息
            dateInfo.date = afterCalendar.time
            dateInfo.type = DateInfo.AFTER_MONTH //将DateInfo类型标记为下个月
            dataList.add(dateInfo) //将日期添加到数组中
            afterCalendar.add(Calendar.DAY_OF_MONTH, 1) //向后推移一天
        }


        return dataList
    }
}
