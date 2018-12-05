package com.lzy.testproject.framework.okhttp;

import com.blankj.utilcode.util.NetworkUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by LiZhiyu on 2018/12/4.
 */
public class CacheNetworkInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        return response.newBuilder().removeHeader("Pragma")
                .header("Cache-Control", "public,max-age=60")
                .build();
    }

    static class CacheInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Response resp;
            Request req;
            if (NetworkUtils.isConnected()) {
                //有网络,检查10秒内的缓存
                req = chain.request()
                        .newBuilder()
                        .cacheControl(new CacheControl
                                .Builder()
                                .maxAge(10, TimeUnit.SECONDS)
                                .build())
                        .build();
            } else {
                //无网络,检查30天内的缓存,即使是过期的缓存
                req = chain.request().newBuilder()
                        .cacheControl(new CacheControl.Builder()
                                .onlyIfCached()
                                .maxStale(30, TimeUnit.DAYS)
                                .build())
                        .build();
            }
            resp = chain.proceed(req);
            return resp.newBuilder().build();
        }
    }
}
