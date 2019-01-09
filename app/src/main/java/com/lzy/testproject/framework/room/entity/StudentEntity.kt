package com.lzy.testproject.framework.room.entity

import android.arch.persistence.room.*
import com.lzy.testproject.framework.room.entity.ClassEntity


/**
 * Created by LiZhiyu on 2018/7/12.
 */
@Entity(tableName = "tb_student"//定义表名
        ,indices = [(Index(value = arrayOf("name", "sex"), unique = true))]//定义索引
//        ,foreignKeys = [(ForeignKey(entity = ClassEntity::class, parentColumns = arrayOf("id"), childColumns = arrayOf("class_id")))]//定义外键
)
class StudentEntity {
    @PrimaryKey //定义主键
    var id: Long = 0
    @ColumnInfo(name = "name")//定义数据表中的字段名
    var name: String? = null
    @ColumnInfo(name = "sex")
    var sex: Int = 0
    @Ignore//指示Room需要忽略的字段或方法
    var ignoreText: String? = null
    @ColumnInfo(name = "class_id")
    var class_id: String? = null
}