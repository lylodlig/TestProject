//package com.lzy.testproject.ui.paging;
//
//import android.arch.paging.PagedListAdapter;
//import androidx.appcompat.util.DiffUtil
//import androidx.recyclerview.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.lzy.testproject.R
//import kotlinx.android.synthetic.main.activity_test2.*
//
///**
// * Created by LiZhiyu on 2018/7/9.
// */
//class MyAdaper : PagedListAdapter<Student, MyAdaper.ViewHolder>(diffCallback) {
//
//
//    companion object {
//        private val diffCallback = object : DiffUtil.ItemCallback<Student>() {
//            override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean =
//                    oldItem.id == newItem.id
//
//            override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean =
//                    oldItem == newItem
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_drag, parent, false))
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//    }
//
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//    }
//}