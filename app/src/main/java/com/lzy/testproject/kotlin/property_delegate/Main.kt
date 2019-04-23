package com.lzy.testproject.kotlin.property_delegate

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class Example {
    var p: String by Delegate()
}
class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}


val lazyValue: String by lazy {
    println("computed!")     // 第一次调用输出，第二次调用不执行
    "Hello"
}

class User {
    var name: String by Delegates.observable("<no name>") {
        prop, old, new ->
        println("$old -> $new")
    }
}

class Person{
    var address: String by Delegates.vetoable(initialValue = "NanJing", onChange = {property, oldValue, newValue ->
        println("property: ${property.name}  oldValue: $oldValue  newValue: $newValue")
        return@vetoable newValue == "BeiJing"
    })
}

fun main(args: Array<String>) {
//    val e = Example()
//    println(e.p)
//
//    println(lazyValue)   // 第一次执行
//    println(lazyValue)   // 第二次执行，只输出返回值
//
//    val user = User()
//    user.name = "first"
//    user.name = "second"
    val person = Person()
    println("address is ${person.address}")
    person.address = "BeiJing"
    println("address is ${person.address}")
    person.address = "ShangHai"
    println("address is ${person.address}")
    person.address = "GuangZhou"
    println("address is ${person.address}")
}