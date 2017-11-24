package com.sss.kotlin.demo.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.sss.kotlin.demo.db.entity.UserEntity

/**
 * Created by sss on 2017/11/23.
 */
@Dao
abstract class UserDao {

    /**
     * 查询所有的用户
     */
    @Query("SELECT * FROM user")
    abstract fun getAll(): LiveData<List<UserEntity>>

    /**
     * 查询所表中第一条数据
     */
    @Query("SELECT * FROM user limit 0,1 ")
    abstract fun getTopOne(): LiveData<UserEntity>

    /**
     * 根据ID查询某个用户
     */
    @Query("SELECT * FROM user WHERE id = :id")
    abstract fun getUserByIds(id: Int): LiveData<UserEntity>

    /**
     * 根据ID查询某些用户
     *
     * @param userIds 用户ID
     * @return 用户
     */
    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    abstract fun loadAllByIds(userIds: IntArray): List<UserEntity>

    /**
     * 模糊查询某个用户
     *
     * @param first 名
     * @param last  姓
     * @return 用户
     */
    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " + "last_name LIKE :last LIMIT 1")
    abstract fun findByName(first: String, last: String): UserEntity

    /**
     * 批量添加用户
     *
     * @param userEntities 要添加用户
     */
    @Insert
    abstract fun insertAll(vararg userEntities: UserEntity)

    /**
     * 删除某个用户
     *
     * @param userEntity 用户
     */
    @Delete
    abstract fun delete(userEntity: UserEntity)
}