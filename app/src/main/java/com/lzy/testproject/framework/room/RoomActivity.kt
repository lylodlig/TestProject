package com.lzy.testproject.framework.room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.lzy.testproject.R
import com.lzy.testproject.framework.room.dao.StudentDao
import com.lzy.testproject.framework.room.database.RoomDemoDB
import com.lzy.testproject.framework.room.entity.StudentEntity
import com.lzy.testproject.ui.softinput.SoftInputActivity
import kotlinx.android.synthetic.main.activity_room.*
import tech.linjiang.pandora.Pandora

class RoomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        btn.setOnClickListener {
            startActivity(Intent(RoomActivity@ this, SoftInputActivity::class.java))
        }

        //创建数据库实例
        var database = Room.databaseBuilder(
                applicationContext,
                RoomDemoDB::class.java,
                "database_name"
        )
                .addCallback(object : RoomDatabase.Callback() {
                    //第一次创建数据库时调用，但是在创建所有表之后调用的
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Log.e("lzy", "DB onCreate")
                    }

                    //当数据库被打开时调用
                    override fun onOpen(db: SupportSQLiteDatabase) {
                        super.onOpen(db)
                        Log.e("lzy", "DB onOpen")
                    }
                })
                .allowMainThreadQueries()//允许主线程查询数据
                .build()

        var studentEntity = StudentEntity()
        studentEntity.id = 10L
        studentEntity.name = "测试"
        studentEntity.sex = 1
//        database.studentDao().insert(studentEntity)

        Pandora.get().open()

    }
}
