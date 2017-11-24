package com.sss.kotlin.demo.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.sss.kotlin.demo.model.User

/**
 * Created by sss on 2017/11/23.
 */
@Entity(tableName = "user")
class UserEntity(
        @PrimaryKey
        override var id: Int,
        @ColumnInfo(name = "first_name")
        override var firstName: String,
        @ColumnInfo(name = "last_name")
        override var lastName: String) : User {

}