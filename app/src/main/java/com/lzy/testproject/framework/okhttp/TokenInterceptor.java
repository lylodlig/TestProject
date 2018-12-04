package com.lzy.testproject.framework.okhttp;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by LiZhiyu on 2018/12/4.
 */
public class TokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        builder.addHeader("token", "65478d11-b3f2-4c9d-8672-60a764457ec0");
        Response proceed = chain.proceed(builder.build());
        return proceed;
    }
}
