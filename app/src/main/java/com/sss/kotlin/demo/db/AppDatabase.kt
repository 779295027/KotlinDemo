package com.sss.kotlin.demo.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

import com.sss.kotlin.demo.db.dao.UserDao
import com.sss.kotlin.demo.db.entity.UserEntity

/**
 * Created by sss on 2017/11/23.
 */
@Database(entities = arrayOf(UserEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
