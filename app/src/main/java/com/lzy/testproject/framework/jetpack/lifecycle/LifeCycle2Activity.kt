package com.lzy.testproject.framework.jetpack.lifecycle

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.lzy.testproject.R
import kotlinx.android.synthetic.main.activity_life_cycle2.*

class LifeCycle2Activity : Activity(), LifecycleOwner {

    private var lifecycleRegistry = LifecycleRegistry(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle2)
        lifecycleRegistry.markState(Lifecycle.State.CREATED)

        lifecycle.addObserver(MyLifeCycleObserver())

        bt.setOnClickListener {
            startActivity(Intent(this, LifeCycleActivity::class.java))
        }
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

    override fun onResume() {
        super.onResume()
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    override fun onStart() {
        super.onStart()
        lifecycleRegistry.markState(Lifecycle.State.STARTED)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleRegistry.markState(Lifecycle.State.DESTROYED)
    }
}
