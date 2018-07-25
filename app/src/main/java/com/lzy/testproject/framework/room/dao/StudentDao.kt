package com.lzy.testproject.framework.room.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.lzy.testproject.framework.room.entity.StudentEntity


/**
 * Created by LiZhiyu on 2018/7/19.
 */
@Dao
interface StudentDao {

    @Query("SELECT * FROM tb_student")
    fun getAll(): List<StudentEntity>

    @Query("SELECT * FROM tb_student WHERE id IN (:ids)")
    fun getAllById(ids: LongArray): List<StudentEntity>

    @Insert
    fun insert(studentEntity: StudentEntity)

    @Delete
    fun delete(studentEntity: StudentEntity)
}