package com.lzy.testproject.framework.okhttp;

import android.util.Log;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by LiZhiyu on 2018/12/4.
 */
public class TestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl httpUrl = request.url().newBuilder().addEncodedQueryParameter("test", "111").build();
        Request request1 = request.newBuilder().url(httpUrl).build();
        Response response = chain.proceed(request1);
        return response;
    }
}
