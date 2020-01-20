package com.lzy.testproject.framework.jetpack.viewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.lzy.testproject.R
import com.lzy.testproject.framework.jetpack.viewmodel.Model.Factory
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_view_model.*

class ViewModelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)

//        val model = ViewModelProviders.of(this)[Model::class.java]
        val model = ViewModelProviders.of(this, Factory("参数"))[Model::class.java]
        model.name?.observe(this, Observer {
            Log.d("lzy", "change $it")
            tv.text = it
        })

        btn.setOnClickListener {
            // 发送和接收在同一线程
            model.name?.value = "修改后"
            Observable.just("异步数据")
                    .observeOn(Schedulers.io())
                    .subscribe {
                        //在不同线程
                        model.name?.postValue(it)
                    }
        }
    }
}
