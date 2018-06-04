package com.lzy.testproject.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.lzy.testproject.R
import kotlin.math.log

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        var school = School()
//        school.sayWord("woxihuanni ")
        School.teach()
        Student.ks()

//        Log.d("lzy",school.sayWord("sha"))
    }
}
