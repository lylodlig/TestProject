package com.lzy.testproject.framework.jetpack.paging

import androidx.lifecycle.switchMap
import androidx.paging.toLiveData
import com.lzy.testproject.utils.Logger
import java.util.concurrent.Executor

class TestRespository(private val networkExecutor: Executor) {

    fun getData(pageSize: Int): Listing<Info> {
        Logger.d("getData")
        var factory = TestDataSourceFactory(networkExecutor)
        val pagedList=factory.toLiveData(
                pageSize = pageSize,
                initialLoadKey = 22,
                fetchExecutor = networkExecutor
        )
        return Listing(
                pagedList = pagedList,
                networkState = factory.sourceLiveData.switchMap {
                    it.networkState
                },
                retry = {
                    factory.sourceLiveData.value?.retryAllFailed()
                },
                refresh = {
                    factory.sourceLiveData.value?.invalidate()
                },
                refreshState = factory.sourceLiveData.switchMap {
                    it.initialLoad
                }
        )
    }
}