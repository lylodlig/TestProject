package com.lzy.testproject.ui.calendar

import java.util.*

/**
 * Created by LiZhiyu on 2018/12/24.
 */
class DateInfo {
    companion object {
        val PRE_MONTH = 1
        val CURRENT_MONTH = PRE_MONTH + 1
        val AFTER_MONTH = CURRENT_MONTH + 1
        val WEEK_TITLE = AFTER_MONTH + 1

        val STATUS_NORMAL = 0
        val STATUS_STRAT = 1
        val STATUS_END = 2
        val STATUS_ING = 3
    }

    var text = ""
    var date: Date? = null
    var type = 0
    var status = STATUS_NORMAL

}