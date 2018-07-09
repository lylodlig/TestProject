package com.lzy.testproject.kotlin

import android.util.Log
import java.util.logging.Logger

/**
 * Created by LiZhiyu on 2018/7/9.
 */
class OutClass {
    var text="你好啊"

    //嵌套类
    class NestedClass{
        fun log(){
            Log.d("lzy","NestedClass ")
        }
    }
    //内部类
    inner class InnerClass{
        fun log(){
            Log.d("lzy","InnerClass  ${this@OutClass.text}")
        }
    }
}