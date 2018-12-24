package com.lzy.testproject.ui.calendar

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lzy.testproject.R
import me.drakeet.multitype.ItemViewBinder
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by LiZhiyu on 2018/12/24.
 */
class DateViewBinder : ItemViewBinder<DateInfo, DateViewBinder.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, item: DateInfo) {
        when {
            item.type == DateInfo.PRE_MONTH || item.type == DateInfo.AFTER_MONTH
            -> holder.tv?.text = ""
            item.date != null -> {
                val format = SimpleDateFormat("yyyy-MM-dd").format(item.date)
                holder.tv?.text = format.split("-")[2]
            }
        }
        holder.tv?.setTextColor(Color.WHITE)
        when (item.status) {
            DateInfo.STATUS_NORMAL -> {
                holder.tv?.setTextColor(Color.BLACK)
                holder.itemView?.setBackgroundColor(Color.WHITE)
            }
            DateInfo.STATUS_STRAT -> holder.itemView?.setBackgroundResource(R.drawable.shape_calendar_left)
            DateInfo.STATUS_END -> holder.itemView?.setBackgroundResource(R.drawable.shape_calendar_right)
            DateInfo.STATUS_ING ->
                when {
                    isFirstDayOfMonth(item.date!!) -> holder.itemView?.setBackgroundResource(R.drawable.shape_calendar_left)
                    isLastDayOfMonth(item.date!!) -> holder.itemView?.setBackgroundResource(R.drawable.shape_calendar_right)
                    else -> holder.itemView?.setBackgroundColor(Color.parseColor("#DDAA75"))
                }
        }
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val view = inflater.inflate(R.layout.item_calendar, parent, false)
        return ViewHolder(view)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv: TextView? = null

        init {
            tv = itemView.findViewById<TextView>(R.id.tv)

        }
    }

    fun isFirstDayOfMonth(date: Date): Boolean {
        if (date == null) {
            return false
        }
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar.get(Calendar.DAY_OF_MONTH) == 1
    }

    fun isLastDayOfMonth(date: Date): Boolean {
        if (date == null) {
            return false
        }
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar.get(Calendar.DAY_OF_MONTH) == calendar
                .getActualMaximum(Calendar.DAY_OF_MONTH)
    }
}