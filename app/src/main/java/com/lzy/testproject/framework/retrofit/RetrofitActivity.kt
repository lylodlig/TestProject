package com.lzy.testproject.framework.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.lzy.testproject.R
import com.lzy.testproject.framework.retrofit.api.ApiService
import com.lzy.testproject.framework.retrofit.entity.Reception
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observer
import java.util.function.Consumer

class RetrofitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        val retrofit = Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        val request = retrofit.create(ApiService::class.java)
        val call = request.getCall()

        call.subscribe(object : Observer<Reception> {
            override fun onError(e: Throwable?) {

            }

            override fun onNext(t: Reception?) {
            }

            override fun onCompleted() {
            }
        })
        //异步执行
//        call.enqueue(object : Callback<Reception> {
//            override fun onFailure(call: Call<Reception>?, t: Throwable?) {
//
//            }
//
//            override fun onResponse(call: Call<Reception>?, response: Response<Reception>?) {
//
//            }
//        })
//        //同步执行
//        val response = call.execute()

    }
}
