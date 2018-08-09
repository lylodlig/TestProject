package com.lzy.testproject

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import tech.linjiang.pandora.Pandora

/**
 * Created by LiZhiyu on 2018/7/20.
 */
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Pandora.init(this).enableShakeOpen()

        ARouter.openLog()     // 打印日志
        ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.init( this ) // 尽可能早，推荐在Application中初始化
    }
}