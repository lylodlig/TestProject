package com.lzy.testproject.framework.jetpack.room.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * 班级表
 * Created by LiZhiyu on 2018/7/12.
 */
@Entity(tableName = "tb_class")
class ClassEntity {

    @PrimaryKey
    var id: Long = 0

}