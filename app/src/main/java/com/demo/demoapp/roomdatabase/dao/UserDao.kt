package com.demo.demoapp.roomdatabase.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.demoapp.roomdatabase.model.UserInfoModel

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUsers(user: UserInfoModel)

    @Query(value = "Select * from UserInfoModel")
    fun getAllUsers() : LiveData<List<UserInfoModel>>
}