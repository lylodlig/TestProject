package com.lzy.testproject.kotlin

import android.util.Log

object Student {
    val TAG = "lzy"
    @JvmOverloads
    fun ks(tag:String="meihuan",text:String) {
        Log.d(TAG, "我是object类实现的静态方法")
    }
}