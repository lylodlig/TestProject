package com.lzy.testproject.ui.calendar

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lzy.testproject.R
import me.drakeet.multitype.ItemViewBinder

/**
 * Created by LiZhiyu on 2018/12/24.
 */
class TitleViewBinder : ItemViewBinder<Title, TitleViewBinder.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, item: Title) {
        holder.tv?.text = item.title
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val view = inflater.inflate(R.layout.item_title, parent, false)
        return ViewHolder(view)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv: TextView? = null

        init {
            tv = itemView.findViewById<TextView>(R.id.tv)

        }
    }

}