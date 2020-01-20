package com.lzy.testproject.framework.jetpack.databinding

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableInt

class Animal : BaseObservable() {

    var name = "默认"
        set(value) {
            field = value
            notifyChange()
        }

    var age = ObservableInt()
        set(value) {
            field = value
            notifyChange()
        }
}