package com.lzy.testproject.framework.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
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