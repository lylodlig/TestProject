package com.lzy.testproject.ui.recyclerview.diffcallback

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lzy.testproject.R
import com.lzy.testproject.ui.paging.Student
import com.lzy.testproject.ui.recyclerview.ViewHolder
import kotlinx.android.synthetic.main.activity_diff.*

//RecyclerView局部刷新
class DiffActivity : AppCompatActivity() {

    var list = mutableListOf<Student>()
    var newList = mutableListOf<Student>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diff)
        btn.setOnClickListener {
            newList.clear()
            newList.addAll(list)
            newList.add(3, Student(428574))

            var calculateDiff = DiffUtil.calculateDiff(DiffCallback(list, newList), true)
            calculateDiff.dispatchUpdatesTo(reyclerView.adapter!!)
            list.clear()
            list.addAll(newList)
        }

        for (item in 0 until 10) {
            list.add(Student(item))
        }

        var calculateDiff = DiffUtil.calculateDiff(DiffCallback(list, newList), true)
        reyclerView.layoutManager = LinearLayoutManager(this)
        reyclerView.adapter = MyAdapter()
        calculateDiff?.dispatchUpdatesTo(reyclerView.adapter!!)
    }

    inner class MyAdapter : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(this@DiffActivity).inflate(R.layout.item_text, parent, false))
        }

        override fun getItemCount(): Int {
            return this@DiffActivity.list.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val textView = holder.itemView.findViewById<TextView>(R.id.tv)
            textView.text = ("测试   ${this@DiffActivity.list[position].id}")
        }

    }

}
