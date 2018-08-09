# 简介
我们原生的跳转一般是使用显式intent和隐式Intent实现的，在显示Intent中会直接依赖类，耦合很严重；而隐式Intent需要在Manifest中配置
，管理起来不方便，扩展性也差，所以使用Arouter就非常方便,并且可以配合组件化开发，地址也可以动态的配置跳转。

# ARouter的配置
```
android {
    defaultConfig {
    ...
    javaCompileOptions {
        annotationProcessorOptions {
        arguments = [ moduleName : project.getName() ]
        }
    }
    }
}

dependencies {
    compile 'com.alibaba:arouter-api:1.2.1.1'
    annotationProcessor 'com.alibaba:arouter-compiler:1.1.2.1'
}
```

# 初始化
```
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.init( this ); // 尽可能早，推荐在Application中初始化
    }
} 
```

# 使用
## 简单使用
目标Activity加上注解  
```
@Route(path = "/lzy/constraint")
public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
    }
}
```
跳转：`ARouter.getInstance().build("/lzy/constraint").navigation()``


