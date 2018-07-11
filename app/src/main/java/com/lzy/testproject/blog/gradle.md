# Gradle文件体系
Android工程通过gradle文件管理各项配置，gradle文件利用DSL（Domain Specific Language）语言描述配置，
并使用Groovy语言处理编译逻辑。一个典型的Android工程结构如下：
![](https://upload-images.jianshu.io/upload_images/2839011-0ba953a3e93d0d19.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/474)

在这里gradle文件分布在几个不同的层级，Project层级以及Module层级。

# Project层级
## settings.gradle
这个文件是描述Project所依赖的Module
```
include ":"
include ":CordovaLib"
include ":app"
```

## build.gradle
