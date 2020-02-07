package com.lzy.testproject.framework.jetpack.paging

import androidx.recyclerview.widget.DiffUtil
import com.joe.jetpackdemo.db.data.Shoe

class TestDiffCallback: DiffUtil.ItemCallback<Info>() {
    override fun areItemsTheSame(oldItem: Info, newItem: Info): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Info, newItem: Info): Boolean {
        return oldItem == newItem
    }
}