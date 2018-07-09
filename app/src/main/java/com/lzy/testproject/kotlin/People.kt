package com.lzy.testproject.kotlin

import android.util.Log

/**
 * Created by LiZhiyu on 2018/7/9.
 */
interface People {
    //接口中的属性不能初始化
    var sex: String

    fun run() {
        Log.d("lzy", "接口中的实现")
    }

    fun say()
}