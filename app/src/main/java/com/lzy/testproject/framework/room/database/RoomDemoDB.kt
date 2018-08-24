package com.lzy.testproject.framework.room.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.lzy.testproject.framework.room.dao.StudentDao
import com.lzy.testproject.framework.room.entity.StudentEntity

/**
 * Created by LiZhiyu on 2018/7/20.
 */
@Database(entities = [StudentEntity::class], version = 2, exportSchema = false)
abstract class RoomDemoDB : RoomDatabase() {
    abstract fun studentDao(): StudentDao
}