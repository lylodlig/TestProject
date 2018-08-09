package com.lzy.testproject.test

import android.database.Observable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.lzy.testproject.R
import kotlinx.android.synthetic.main.activity_test_k.*

class TestKActivity : AppCompatActivity() {
    var mBooleanThreadLocal: ThreadLocal<Boolean>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_k)

        var a = """
           safdhkj
            asfj
            sds
        """
        tv.text = a

        init()

        tv.setOnClickListener {
            ARouter.getInstance().build("/lzy/constraint/TestActivity").navigation()
        }
    }

    fun init() {
        mBooleanThreadLocal = ThreadLocal()

        mBooleanThreadLocal?.set(true)
        printThreadLocal()

        Thread({
            mBooleanThreadLocal?.set(false)
            printThreadLocal()
        }, "thread#1").start()

        Thread(object : Runnable {
            override fun run() {
                printThreadLocal()
            }
        }, "thread#2").start()
    }

    fun printThreadLocal() {
        Log.d("lzy", "[Thread#" + Thread.currentThread().getName() + "#" + Thread.currentThread().getId() + "]mBooleanThreadLocal=" + mBooleanThreadLocal?.get())
    }
}
