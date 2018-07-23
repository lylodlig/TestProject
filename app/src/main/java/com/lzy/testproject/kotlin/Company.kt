package com.lzy.testproject.kotlin

/**
 * Created by LiZhiyu on 2018/7/9.
 */
class Company {
    var name: String = ""
        private set

    var boss = "L"
        get() = field
        set(value) {
            field = value
        }

    var address: String = ""
        get() = if (field.length > 0) "" else "大家好看"
        set(value) {
            field = if (value.length > 0) "" else value
        }

}