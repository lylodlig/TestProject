package com.lzy.testproject.test

import android.content.Intent
import android.database.Observable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.joe.jetpackdemo.ui.activity.LoginActivity
import com.lzy.testproject.R
import kotlinx.android.synthetic.main.activity_test_k.*
import java.math.BigDecimal
import kotlin.math.pow

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
//            var f = et1.text.toString().toLong()
//            tv1.text = "${f.toFloat()}"
            startActivity(Intent(this@TestKActivity,LoginActivity::class.java))
        }
    }

    /**
     * scale:保留几位小数
     */
    fun formateMeihuan(f: Float,scale:Int): String {
        var bigDecimal = BigDecimal(f.toString())
        var s = bigDecimal.setScale(scale+3, BigDecimal.ROUND_DOWN).toString()
        var s1 = "${s.split(".")[1]}"

        var s2 = s1.substring(scale, s1.length-1)
        var t= 10.0.pow(-scale.toDouble())
        return if (s2.toInt() > 50) {
            BigDecimal(f + t).setScale(scale, BigDecimal.ROUND_DOWN).toString()
        } else {
            BigDecimal(f.toDouble()).setScale(scale, BigDecimal.ROUND_DOWN).toString()
        }
    }


    fun formateFloat(f: Float): String {
        var bigDecimal = BigDecimal(f.toDouble())
        var s = bigDecimal.setScale(4, BigDecimal.ROUND_DOWN).toString()
        var s1 = "${s.split(".")[1]}"

        var s2 = s1.substring(1, s1.length-1)

        return if (s2.toInt() > 50) {
            ""
        } else {
            BigDecimal(f.toDouble()).setScale(1, BigDecimal.ROUND_DOWN).toString()
        }
    }

    fun formateInt(f: Float): String {
        var bigDecimal = BigDecimal(f.toDouble())
        var s = bigDecimal.setScale(3, BigDecimal.ROUND_DOWN).toString()
        var s1 = "${s.split(".")[1]}"
        var s2 = s1.substring(0, s1.length-1)

        return if (s2.toInt() > 50) {
            BigDecimal((f + 1).toDouble()).setScale(0, BigDecimal.ROUND_DOWN).toString()
        } else {
            BigDecimal(f.toDouble()).setScale(0, BigDecimal.ROUND_DOWN).toString()
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
