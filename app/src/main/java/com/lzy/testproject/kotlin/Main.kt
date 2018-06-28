package com.lzy.testproject.kotlin

/**
 * Created by LiZhiyu on 2018/5/31.
 */
class Main {

    fun main(args: Array<String>) {
        val name = if (args.isNotEmpty()) args[0] else "Kotlin"
        println("Hello $name")
    }
}