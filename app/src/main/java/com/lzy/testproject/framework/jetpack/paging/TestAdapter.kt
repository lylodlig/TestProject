package com.lzy.testproject.framework.jetpack.paging

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.joe.jetpackdemo.common.BaseConstant
import com.joe.jetpackdemo.databinding.ShoeRecyclerItemBinding
import com.joe.jetpackdemo.db.data.Shoe
import com.joe.jetpackdemo.ui.activity.DetailActivity
import com.joe.jetpackdemo.ui.adapter.ShoeDiffCallback
import com.lzy.testproject.databinding.PagingItemTestBinding

/**
 * 鞋子的适配器 配合Data Binding使用
 */
class TestAdapter constructor(val context: Context) :
    PagedListAdapter<Info, TestAdapter.ViewHolder>(TestDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            PagingItemTestBinding.inflate(
                LayoutInflater.from(parent.context)
                , parent
                , false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shoe = getItem(position)
        holder.apply {
            bind(shoe!!)
            itemView.tag = shoe
        }
    }

    /**
     * Holder的点击事件
     */
    private fun onCreateListener(id: Long): View.OnClickListener {
        return View.OnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(BaseConstant.DETAIL_SHOE_ID, id)
            context.startActivity(intent)
        }
    }


    class ViewHolder(private val binding: PagingItemTestBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Info) {
            binding.apply {
                this.info = item
                executePendingBindings()
            }
        }
    }
}