package com.lzy.testproject.framework.arouter.inter

import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route

/**
 * Created by LiZhiyu on 2018/8/23.
 */
@Route(path = "/statistics/utils/buriedpoint")
class RouterImp : IRouter {
    override fun name() {
        Log.e("lzy", "测试")
    }
}