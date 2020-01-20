package com.lzy.testproject.utils

import android.util.Log
import com.blankj.utilcode.util.TimeUtils
import com.lzy.testproject.BuildConfig
import java.util.*

object Logger {
    var debug = BuildConfig.DEBUG
    private const val TAG: String = "lzy"


    fun e(text: String) {
        if (debug)
            Log.e(TAG, "${TimeUtils.date2String(Date())}----------$text")
    }

    fun i(text: String) {
        if (debug)
            Log.i(TAG, "${TimeUtils.date2String(Date())}----------$text")
    }

    fun d(text: String) {
        if (debug)
            Log.d(TAG,"${TimeUtils.date2String(Date())}----------$text")
    }

    fun v(text: String) {
        if (debug)
            Log.v(TAG, "${TimeUtils.date2String(Date())}----------$text")
    }
}