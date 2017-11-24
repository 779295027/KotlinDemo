package com.sss.kotlin.demo.db

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Room
import android.content.Context
import android.os.AsyncTask
import android.util.Log


import com.sss.kotlin.demo.db.entity.UserEntity

import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by sss on 2017/9/19.
 * 用于创建Database的工具类
 */
class DatabaseCreator {
    /**
     * 是否已经初始化
     */
    private val aBoolean = AtomicBoolean(true)
    /**
     * 数据库创建是否完成
     */
    private val mIsDatabaseCreated = MutableLiveData<Boolean>()
    var database: AppDatabase? = null
        private set

    fun getmIsDatabaseCreated(): MutableLiveData<Boolean> {
        return mIsDatabaseCreated
    }

    /**
     * 创建数据库实例，即AppDatabase的实例
     *
     * @param context 上下文
     */
    fun create(context: Context) {
        Log.e("DatabaseCreator", "开始运行创建数据库方法")

        // 如果aBoolean为true,那么赋值为false，并返回true，表示没有初始化数据库
        if (!aBoolean.compareAndSet(true, false)) {
            return  // 表示已经初始化过数据库
        }

        Log.e("DatabaseCreator", "给mIsDatabaseCreated赋值，数据库还未创建成功（主要做初始化这个对象用）")
        mIsDatabaseCreated.value = false
        object : AsyncTask<Context, Void, Void>() {
            override fun doInBackground(vararg contexts: Context): Void? {
                Log.e("DatabaseCreator", "启动Background，开始创建数据库")
                val context1 = contexts[0].applicationContext
                // 重置数据库，删除之前的数据库
                context1.deleteDatabase(DATABASE_NAME)
                // 创建数据库
                database = Room.databaseBuilder(context1.applicationContext,
                        AppDatabase::class.java, DATABASE_NAME).build()
                try {
                    // 沉睡4秒，显得创建非常耗时，实际开发中此行无用
                    Thread.sleep(2000)
                } catch (ignored: Exception) {
                }

                // 往数据库中添加假数据
                database = initData(database)
                return null
            }

            override fun onPostExecute(aVoid: Void?) {
                Log.e("DatabaseCreator", "给mIsDatabaseCreated赋值，数据库创建成功")
                // 数据库已创建,通知页面可以调用数据
                mIsDatabaseCreated.value = true
            }
        }.execute(context.applicationContext)

    }


    private fun initData(appDatabase: AppDatabase?): AppDatabase {
        val userEntity = UserEntity(0, "名", "姓")
        val userEntity2 = UserEntity(1, "三", "张")
        val userEntity3 = UserEntity(2, "四", "李")
        appDatabase!!.beginTransaction()
        try {
            appDatabase.userDao().insertAll(userEntity, userEntity2, userEntity3)
            appDatabase.setTransactionSuccessful()
        } finally {
            appDatabase.endTransaction()
        }
        return appDatabase
    }

    companion object {
        /**
         * 数据库名称
         */
        private val DATABASE_NAME = "kotlin-data-demo-db2"
        private var sInstance: DatabaseCreator? = null


        /**
         * 用于创建单例，锁住这个对象，防止线程征用
         */
        private val LOCK = Any()

        val instance: DatabaseCreator?
            @Synchronized get() {
                if (sInstance == null) {
                    synchronized(LOCK) {
                        if (sInstance == null) {
                            sInstance = DatabaseCreator()
                        }
                    }
                }
                return sInstance
            }
    }

}
