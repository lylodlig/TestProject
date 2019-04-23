import com.lzy.testproject.kotlin.property_delegate.SingleValueVar

class Example {
    var p: String by SingleValueVar()
}

class Test {
    companion object {
        /** 我是main入口函数 **/
        @JvmStatic
        fun main(args: Array<String>) {
            val e = Example()
            e.p = "fddf"
            println(e.p)
            e.p = "fddf222"
            println(e.p)
        }
    }
}