package com.lzy.testproject.kotlin

/**
 * Created by LiZhiyu on 2018/7/9.
 */
class Child(override var sex: String,name: String, age: String) : Parent(name,age), People {

    //必须实现
    override fun say() {
    }

    //可以不覆写，接口中有实现
    override fun run() {
        super.run()
    }
}