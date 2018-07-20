package com.lzy.testproject

import android.app.Application
import tech.linjiang.pandora.Pandora

/**
 * Created by LiZhiyu on 2018/7/20.
 */
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Pandora.init(this).enableShakeOpen()
    }
}