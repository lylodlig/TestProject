package com.lzy.testproject.kotlin

/**
 * Created by LiZhiyu on 2018/5/31.
 */
class Person(var name: String) {
    var age: Int = 2
    val sex: String
        get() = if (name == "呵呵") "男" else "女"


    constructor(name: String, age: Int) : this(name) {

    }
}