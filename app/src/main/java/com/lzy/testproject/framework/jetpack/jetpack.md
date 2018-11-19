## Lifecycle
Lifecycle是一个生命周期感知组件，可以监听到生命周期的变化，可以统一的处理，而不是全部在Activity中处理，实现了与Activity的解耦分离。  

### 1. 添加依赖  
`"android.arch.lifecycle:runtime:${dependVersion.jetpack_version}"`  

### 2. 创建Observer，注解标记相应的生命周期  
```
class MyLifeCycleObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Log.d("lzy", "onCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        Log.d("lzy", "onStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Log.d("lzy", "onResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Log.d("lzy", "onPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        Log.d("lzy", "onStop")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        Log.d("lzy", "onDestroy")
    }
}
```

### 3. 添加到需要监听的Activity或者Fragment中
 1. 如果是SupportActivity，本身已经处理了LifeCycle，直接调用添加即可  
 ```
class LifeCycleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle)

        lifecycle.addObserver(MyLifeCycleObserver())
    }
}
```

## ViewModel
### ViewModel概述

ViewModel用来管理UI交互的数据，它保存的数据不会因为Activity配置改变而重新生成，比如屏幕旋转会重新触发onCreate()，但是ViewModel保存的数据不会丢失。
当然这里我们可以使用onSaveInstanceState()来保存数据，但是如果数据量太大就很麻烦

ViewModel之所以能在Activity重建时保存并恢复数据，因为Activity初次创建时会初始化创建VIewModel，在Activity销毁时，ViewModel对象不会销毁  

因为ViewModel在Activity销毁时是不会重新创建的，这也意味者ViewModel中不可以引用Activity的对象，否则会有内存泄露的问题，那么当Model中需要Context呢？
Android为我们提供了AndroidViewModel，只需继承AndroidViewModel即可


### 使用
1. 创建ViewModel类继承系统的ViewModel
```java
class Model : ViewModel() {
    var text = ""
        get() = "$field：zhen"

}
```

2. 在使用的Activity、Fragment中获取ViewModel
`val model = ViewModelProviders.of(this)[Model::class.java]`  

3. 在ViewModel中存储数据，点击按钮改变，旋转屏幕Activity销毁，可以发现ViewModel中的值是改变后的  
```java
class ViewModelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)

        val model = ViewModelProviders.of(this)[Model::class.java]
        Log.d("lzy", model.text)

        btn.setOnClickListener {
            model.text = "测试"
            Log.d("lzy", model.text)
        }
    }
}

```

### 生命周期

![](https://img-blog.csdn.net/20180906153941718?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0FsZXh3bGw=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

