package com.lzy.testproject.framework.jetpack.viewmodel

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.lzy.testproject.R
import kotlinx.android.synthetic.main.activity_view_model.*

class ViewModelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)

        val model = ViewModelProviders.of(this)[Model::class.java]
        Log.d("lzy", model.text)

        btn.setOnClickListener {
            model.text = "测试"
            Log.d("lzy", model.text)
        }
    }
}
