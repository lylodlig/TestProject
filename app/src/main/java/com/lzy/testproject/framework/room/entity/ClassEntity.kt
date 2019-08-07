package com.lzy.testproject.framework.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 班级表
 * Created by LiZhiyu on 2018/7/12.
 */
@Entity(tableName = "tb_class")
class ClassEntity {

    @PrimaryKey
    var id: Long = 0

}