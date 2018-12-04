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
        Request request1 = request.newBuilder()
                .cacheControl(new CacheControl.Builder()
                        .onlyIfCached()
                        .maxStale(30, TimeUnit.SECONDS)
                        .build()).build();

        Response response = chain.proceed(request1);
        return response;
//        String cache = request.cacheControl().toString();
//        if (TextUtils.isEmpty(cache)) {
//            cache = "public,max-age=60";
//        }
//        return response.newBuilder()
//                .header("Cache-Control", cache)
//                .removeHeader("Pragma")
//                .build();
    }
}
