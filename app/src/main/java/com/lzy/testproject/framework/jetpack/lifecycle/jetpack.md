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

