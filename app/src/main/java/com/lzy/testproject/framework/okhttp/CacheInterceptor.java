package com.lzy.testproject.framework.okhttp;

import android.text.TextUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by LiZhiyu on 2018/12/4.
 */
public class CacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
//        request = request.newBuilder()
//                .cacheControl(new CacheControl.Builder()
//                        .onlyIfCached()
//                        .maxAge(30, TimeUnit.SECONDS)
//                        .build()).build();

        Response response = chain.proceed(request);
        return response.newBuilder().removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .header("Cache-Control", "public,max-age=10")
                .build();
    }
}
