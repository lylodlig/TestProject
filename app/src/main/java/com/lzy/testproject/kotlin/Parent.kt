package com.lzy.testproject.kotlin

import android.util.Log

/**
 * Created by LiZhiyu on 2018/7/9.
 */
open class Parent(var name: String) {

    constructor(name: String, age: String) : this(name) {
    }

    open fun log(name: String){
        Log.d("lzy","parent method")
    }
}