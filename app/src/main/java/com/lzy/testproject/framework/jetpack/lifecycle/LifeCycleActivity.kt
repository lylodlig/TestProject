package com.lzy.testproject.framework.jetpack.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lzy.testproject.R

class LifeCycleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle)

        lifecycle.addObserver(MyLifeCycleObserver())
    }
}
