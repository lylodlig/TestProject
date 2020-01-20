package com.lzy.testproject.framework.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.lzy.testproject.R
import com.lzy.testproject.databinding.ActivityNavigationBinding

class NavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityNavigationBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_navigation)
    }
}
