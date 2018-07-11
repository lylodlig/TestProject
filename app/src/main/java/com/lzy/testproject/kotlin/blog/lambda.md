# 1.Lambda介绍
Lambda表达式的本质其实是匿名函数，因为在其底层实现中还是通过匿名函数来实现的。但是我们在用的时候不必关心起底层实现。

我们的点击事件一般是这样写的：
```
tv.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                //TODO
            }

        })
```
这样写，编译器会提示可以使用lambda表达式，优化就是这样了：
```
tv.setOnClickListener {
            //TODO
        }
```
这样就简介太多了

# 2.Lambda使用
## 2.1 Lambda表达式的特点
- Lambda表达式总是被大括号括着
- 其参数(如果存在)在 -> 之前声明(参数类型可以省略)
- 函数体(如果存在)在 -> 后面。

## 2.2 无参数情况
```
 //普通表达式
    fun test() {
        Log.e("lzy", "1")
    }
    //lambda表达式
    var test1 = {
        Log.e("lzy", "2")
    }

    //调用
    test()
    test1()
```

## 2.3 有参数的情况
```
    //普通表达式
    fun add(a: Int, b: Int): Int {
        return a + b
    }

    //lambda表达式
    var add: (Int, Int) -> Int = { a, b -> a + b }
    //或者
    var add = { a: Int, b: Int -> a + b }
```

## 2.4 lambda表达式作为参数
```
 // 普通表达式
  fun test(a : Int , b : Int) : Int{
      return a + b
  }

  fun sum(num1 : Int , num2 : Int) : Int{
      return num1 + num2
  }

  // 调用
  test(10,sum(3,5)) // 结果为：18


   //lambda
    fun test(a : Int , b : (num1 : Int , num2 : Int) -> Int) : Int{
        return a + b.invoke(3,5)
    }

    // 调用
    test(10,{ num1: Int, num2: Int ->  num1 + num2 })  // 结果为：18
```
当Lambda表达式作为其一个参数时，只为其表达式提供了参数类型与返回类型,所以，我们在调用此高阶函数的时候我们要为该Lambda表达式写出它的具体实现。

invoke()函数：表示为通过函数变量调用自身，因为上面例子中的变量b是一个匿名函数。

# 3.lambda一些约定
## 3.1 it
如果 Kotlin 可以自己计算出签名，它允许我们不声明唯一的参数，并且将隐含地为我们声明其名称为 it。
其实通常情况下它都可以自己计算出签名，也就是说，如果函数字面值只有一个参数， 那么它的声明可以省略（连同 ->），其名称是 it

```
    // lambda
    fun test(a: Int, b: (Int) -> Boolean): Int {
        return if (b.invoke(a)) a else 0
    }

            //调用
            test(10, { it > 0 })
```
上面test的第二个参数是lambda表达式，并且接受一个int的参数返回一个boolean，可以看到在调用的时候直接可以省略参数，用it表示

## 3.2 下划线_
如果哪一个参数没有用到，可以使用下划线代替，表示不处理这个参数，举个栗子：
```
val map = mapOf("key1" to "value1","key2" to "value2","key3" to "value3")

map.forEach{
     key , value -> println("$key \t $value")
}

// 不需要key的时候
map.forEach{
    _ , value -> println("$value")
}
```

## 3.3 如果函数的最后一个参数是一个函数，那么我们在用Lambda表达最后一个函数参数的时候，可以把它放在括号()外面
比如上面3.1的调用也可以这样写：
```
test(10) { it > 0 }
等价于
test(10, { it > 0 })
```

## 3.4 带接收者的函数字面值
Kotlin提供了指定的接收者调用函数字面值的功能。在函数字面值的函数体中，可以调用该接收者对象上的方法而无需任何额外的限定符。
```
val sum = fun Int.(other: String): String= this.toString() + other
```
可以这样调用
```
3.sum("2首付款后")
```

# 4.闭包
所谓闭包，即是函数中包含函数，这里的函数我们可以包含(Lambda表达式，匿名函数，局部函数，对象表达式)，闭包在Java中是不行的，
不能嵌套函数，如下所示：
```
private void test(){
        private void test(){        // 错误，因为Java中不支持函数包含函数

        }
    }


fun test1(){
    fun test2(){   // 正确，因为Kotlin中可以函数嵌套函数

    }
}
```