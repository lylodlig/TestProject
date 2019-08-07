package com.lzy.testproject.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.lzy.testproject.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val name = if (savedInstanceState != null) "sd" else "Kotlin"
        Log.d("lzy","Hello $name")
    }
}
