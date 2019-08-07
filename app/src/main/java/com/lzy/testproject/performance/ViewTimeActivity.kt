package com.lzy.testproject.performance

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.LayoutInflaterCompat
import com.lzy.testproject.R

class ViewTimeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        LayoutInflaterCompat.setFactory2(layoutInflater, object : LayoutInflater.Factory2 {
            override fun onCreateView(p0: View?, p1: String?, p2: Context, p3: AttributeSet): View {
                val start = System.currentTimeMillis()
                val view = delegate.createView(p0, p1, p2, p3)
                val end = System.currentTimeMillis()
                Log.i("meihuan", "加载耗时${end - start}")
                return view
            }

            override fun onCreateView(p0: String?, p1: Context?, p2: AttributeSet?): View? {
                return null
            }

        })
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_time)
    }
}
