package com.lzy.testproject.rx

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.support.annotation.Size
import android.util.Log
import com.lzy.testproject.R
import com.lzy.testproject.utils.StatusBarUtil
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import kotlinx.android.synthetic.main.activity_example.*
import java.util.concurrent.TimeUnit

class ExampleActivity : Activity() {
    var compositeDisposable: CompositeDisposable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
        StatusBarUtil.setColor(this, Color.GREEN, 100)

        countDownTimer(4, arrayOf(1))

        compositeDisposable = CompositeDisposable()

        testCompositeDisposanle()
    }


    //计时器
    fun countDownTimer(time: Long, @Size(2) ss: Array<Int>) {
        Observable.interval(1, TimeUnit.SECONDS)
                .map { time - it }
                .doOnSubscribe { Log.e("lzy", "开始之前") }
                .take(time + 1)
                .subscribeWith(object : DisposableObserver<Long>() {
                    override fun onError(e: Throwable?) {
                    }

                    override fun onComplete() {
                        Log.e("lzy", "onComplete")
                    }

                    override fun onNext(value: Long?) {
                        Log.e("lzy", "onNext:$value")
                    }

                })
    }

    fun testCompositeDisposanle() {
        compositeDisposable?.add(Observable.interval(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<Long>() {
                    override fun onComplete() {
                    }

                    override fun onNext(value: Long?) {
                        Log.e("lzy", "tag:${value}")
                        tv_example.text = "onNext${value}"
                    }

                    override fun onError(e: Throwable?) {
                    }

                }))
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }

}
