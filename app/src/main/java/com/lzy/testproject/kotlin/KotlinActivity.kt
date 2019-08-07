package com.lzy.testproject.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.lzy.testproject.R
import kotlinx.android.synthetic.main.activity_kotlin.*

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        var school = School()
//        school.sayWord("woxihuanni ")
        School.teach()
//        Student.ks()

//        Log.d("lzy",school.sayWord("sha"))

        //调用
        OutClass.NestedClass().log()
        OutClass().InnerClass().log()

        var company = Company()
        company.name
        //整型数组
        var aInt: IntArray = intArrayOf(1, 2, 3)
        //char数组
        var aChar: CharArray = charArrayOf('2', 'd', 's')
        //字符串数组（对象）
        var aString = arrayOf<String>("safjkl", "书法家路口")
        //空数组
        var aEmpty = emptyArray<Int>()
        val arrayOfNulls = arrayOfNulls<Int>(10) //空数组
        val initArray = Array(5, { i -> (i * i).toString() }) //构造函数init

//        val iterator = aInt.iterator()
//        while (iterator.hasNext()) {
//            print("" + iterator.next())
//        }
//        //forEach
//        aInt.iterator().forEach { Log.e("lzy",it) }
//        //for-
//        for (it in aInt/*.iterator()*/) {
//            print(it)
//        }
//        //下标索引
//        aInt.forEachIndexed { index, i -> println("$index = $i") }
//
//        aInt.forEach { i: Int -> print(i) }
        //调用
        Log.e("lzy",3.sum("2首付款后"))
    }

    val sum = fun Int.(other: String): String= this.toString() + other

}
