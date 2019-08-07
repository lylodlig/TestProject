package com.lzy.testproject.framework.jetpack.viewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import androidx.lifecycle.ViewModelProviders
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
