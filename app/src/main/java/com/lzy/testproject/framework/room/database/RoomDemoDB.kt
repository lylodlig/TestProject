package com.lzy.testproject.framework.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lzy.testproject.framework.room.dao.StudentDao
import com.lzy.testproject.framework.room.entity.StudentEntity

/**
 * Created by LiZhiyu on 2018/7/20.
 */
@Database(entities = [StudentEntity::class], version = 2, exportSchema = false)
abstract class RoomDemoDB : RoomDatabase() {
    abstract fun studentDao(): StudentDao
}