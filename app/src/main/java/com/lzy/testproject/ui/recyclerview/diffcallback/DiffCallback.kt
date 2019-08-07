package com.lzy.testproject.ui.recyclerview.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.lzy.testproject.ui.paging.Student

/**
 * Created by LiZhiyu on 2018/7/9.
 */
class DiffCallback(list: MutableList<Student>, newList: MutableList<Student>) : DiffUtil.Callback() {

    var oldList: List<Student>? = null
    var newList: List<Student>? = null


    init {
        this.oldList = list
        this.newList = newList
    }


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList!![oldItemPosition].id == newList!![newItemPosition].id
    }

    override fun getOldListSize(): Int {
        return oldList!!.size
    }

    override fun getNewListSize(): Int {
        return newList!!.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList!![oldItemPosition].id == newList!![newItemPosition].id
    }
}