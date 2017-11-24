package com.sss.kotlin.demo.viewmodel

import android.app.Application
import android.arch.core.util.Function
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.Transformations
import android.util.Log
import com.sss.kotlin.demo.db.AppDatabase
import com.sss.kotlin.demo.db.DatabaseCreator
import com.sss.kotlin.demo.db.entity.UserEntity

/**
 * Created by sss on 2017/11/23.
 */
class UserViewModel(application: Application) : AndroidViewModel(application) {

    private var databaseCreato: DatabaseCreator? = null
    var userLiveData: LiveData<UserEntity>? = null
        private set

    init {
        databaseCreato = databaseCreato ?: DatabaseCreator.instance
        userLiveData = Transformations.switchMap(databaseCreato!!.getmIsDatabaseCreated(), { aBoolean ->
            Log.e("--->", "--->" + "数据库是否创建完成？")
            if (!aBoolean) {
                Log.e("--->", "--->" + "否")
                MediatorLiveData<UserEntity>()
            } else {
                Log.e("--->", "--->" + "是")
                // 数据库创建完，取库中第一条数据
                databaseCreato ?: Log.e("--->", "--->" + "null")
                databaseCreato!!.database ?: Log.e("--->", "appDatabase--->" + "null")
                var aa: AppDatabase? = databaseCreato!!.database
                aa!!.userDao().getUserByIds(0)
            }

        })
        databaseCreato!!.create(application)
    }
}