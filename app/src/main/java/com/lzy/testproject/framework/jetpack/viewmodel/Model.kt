package com.lzy.testproject.framework.jetpack.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * Created by LiZhiyu on 2018/11/19.
 */
class Model(params: String?) : ViewModel() {
    var text = ""
        get() = "$field：zhen"

    var name: MutableLiveData<String>? = MutableLiveData(params!!)

    class Factory(private var params: String?) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return Model(params) as T
        }
    }
}