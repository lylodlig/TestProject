package com.lzy.testproject.test

import android.database.Observable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lzy.testproject.R
import kotlinx.android.synthetic.main.activity_test_k.*

class TestKActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_k)

        var a = """
           safdhkj
            asfj
            sds
        """
        tv.text=a


    }
}
