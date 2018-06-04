package com.lzy.testproject.kotlin

import android.util.Log
import android.widget.Toast
import java.io.IOException

//Kotlin的静态方法
 class School {

    /**
     * 静态变量和静态方法
     */
    companion object {
        val TAG = "lzy"
        fun teach() {


            var result=try {
                2       //没有异常时的返回
            }catch (e:IOException){
                3       //发生异常的返回
            }
            Log.d(TAG, "我是companion中的静态方法  $result")
        }
    }

    fun sayWord(word: Char): String {
       return when(word){
            in 'a'..'z'-> "helloGet"
            in '0'..'7'->"love you"
            else-> "呵呵"
        }
    }




}