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
 * 有网络读取网络的数据，没有网络读取缓存。
 */
public class Cache1Interceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetworkUtils.isConnected()) {
            //没有网络强制使用缓存
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        Response response = chain.proceed(request);
        if (NetworkUtils.isConnected()) {
            return response.newBuilder().removeHeader("Pragma")
                    .header("Cache-Control", "public,only-if-cached,max-age=0")
                    .build();
        } else {
            //设置无网络缓存时间
            int max = 4 * 24 * 60 * 60;
            return response.newBuilder().removeHeader("Pragma")
                    .header("Cache-Control", "public,only-if-cached,max-stale=" + max)
                    .build();
        }

    }
}
