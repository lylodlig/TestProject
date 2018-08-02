package com.lzy.testproject.framework.retrofit.api

import com.lzy.testproject.framework.retrofit.entity.Reception
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by LiZhiyu on 2018/8/1.
 */
interface ApiService {

    @GET("openapi.do?keyfrom=Yanzhikai&key=2032414398&type=data&doctype=json&version=1.1&q=car")
    fun getCall(): Call<Reception>
}