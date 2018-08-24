package com.lzy.testproject.framework.arouter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.lzy.testproject.R
import kotlinx.android.synthetic.main.activity_router.*

class RouterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_router)

        val navigation = ARouter.getInstance().build("/statistics/utils/buriedpoint").navigation()
        Log.e("lzy", "msg:" + navigation as String?)

        btnRouter.setOnClickListener {
            ARouter.getInstance().build("/lzy/constraint/TestActivity").navigation()
        }
    }
}
