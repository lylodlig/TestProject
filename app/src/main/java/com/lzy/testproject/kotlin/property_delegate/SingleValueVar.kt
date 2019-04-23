package com.lzy.testproject.kotlin.property_delegate

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class SingleValueVar<T> : ReadWriteProperty<Any?, T> {

    private var value: T? = null

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        if (null != value && null == this.value) {
            this.value = value
        }
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value!!
    }

}