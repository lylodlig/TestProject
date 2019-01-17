package com.lzy.testproject.ui.recyclerview.fastsmooth

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lzy.testproject.R
import kotlinx.android.synthetic.main.activity_fast_scroll.*

class FastScrollActivity : AppCompatActivity() {
    var list = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fast_scroll)

        for (index in 0..50) {
            list.add("还是开放的狂欢节   $index")
        }
        recyclerview.layoutManager = FastScrollLinearLayoutManager(this)
        recyclerview.adapter=MyAdapter()

        btfast.setOnClickListener {
            recyclerview.smoothScrollToPosition(40)
        }
    }


    internal inner class MyAdapter : RecyclerView.Adapter<MyAdapter.ViewHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(this@FastScrollActivity).inflate(R.layout.item_text, parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val textView = holder.itemView.findViewById<TextView>(R.id.tv)
            textView.text = list.get(position)

        }

        override fun getItemCount(): Int {
            return list.size
        }

        internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    }
}
