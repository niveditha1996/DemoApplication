package com.demo.demoapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.demoapp.model.UserInfoModel

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUsers(user: UserInfoModel)

    @Query(value = "Select * from UserInfoModel")
    fun getAllUsers() : LiveData<List<UserInfoModel>>
}