package com.lzy.testproject.framework.jetpack.viewmodel

import androidx.lifecycle.ViewModel


/**
 * Created by LiZhiyu on 2018/11/19.
 */
class Model : ViewModel() {
    var text = ""
        get() = "$field：zhen"


}