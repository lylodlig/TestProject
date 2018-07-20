package com.lzy.testproject.framework.room

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.lzy.testproject.R
import com.lzy.testproject.framework.room.database.RoomDemoDB

class RoomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        //创建数据库实例
        val database = Room.databaseBuilder(
                applicationContext,
                RoomDemoDB::class.java,
                "database_name"
        )
        database.addCallback(object : RoomDatabase.Callback() {
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

    }
}
