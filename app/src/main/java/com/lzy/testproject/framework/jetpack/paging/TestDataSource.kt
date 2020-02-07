/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lzy.testproject.framework.jetpack.paging

import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.lzy.testproject.utils.Logger
import java.io.IOException
import java.util.concurrent.Executor

/**
 * A data source that uses the before/after keys returned in page requests.
 * <p>
 * See ItemKeyedSubredditDataSource
 */
class TestDataSource(
        private val retryExecutor: Executor) : PageKeyedDataSource<Int, Info>() {

    // keep a function reference for the retry event
    // 重试函数,无参返回值是Any
    private var retry: (() -> Any)? = null

    /**
     * There is no sync on the state because paging will always call loadInitial first then wait
     * for it to return some success value before calling loadAfter.
     */
    val networkState = MutableLiveData<NetworkState>()

    val initialLoad = MutableLiveData<NetworkState>()

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            retryExecutor.execute {
                it.invoke()
            }
        }
    }

    override fun loadBefore(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, Info>) {
        // ignored, since we only ever append to our initial load
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Info>) {
        networkState.postValue(NetworkState.LOADING)
        Logger.d("loadAfter ${params.requestedLoadSize}   ${params.key}    ${Looper.myLooper() != Looper.getMainLooper()}")
        Thread.sleep(2000)
        var list= mutableListOf<Info>()
        for (i in 1..20) {
            list.add(Info(i+1000))
        }
        retry = null
        // 加载成功的回调，第二个参数是下一页的key
        callback.onResult(list, 9)
        networkState.postValue(NetworkState.LOADED)
    }

    override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, Info>) {
        Logger.d("loadInitial  ${params.requestedLoadSize}   ${Looper.myLooper() != Looper.getMainLooper()}")
        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)

        // triggered by a refresh, we better execute sync
        try {
            var list= mutableListOf<Info>()
            for (i in 1..20) {
                list.add(Info(i))
            }
            retry = null
            networkState.postValue(NetworkState.LOADED)
            initialLoad.postValue(NetworkState.LOADED)
            callback.onResult(list, 3, 6)
        } catch (ioException: IOException) {
            retry = {
                loadInitial(params, callback)
            }
            val error = NetworkState.error(ioException.message ?: "unknown error")
            networkState.postValue(error)
            initialLoad.postValue(error)
        }
    }
}