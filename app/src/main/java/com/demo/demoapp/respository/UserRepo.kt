package com.demo.demoapp.respository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.demo.demoapp.roomdatabase.AppDb
import com.demo.demoapp.dao.UserDao
import com.demo.demoapp.model.UserInfoModel

class UserRepo(application: Application) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.

    private var userDao: UserDao

    private var allUsers: LiveData<List<UserInfoModel>>

    init {
        val database: AppDb = AppDb.getDatabase(
            application.applicationContext
        )!!
        userDao = database.UserDao()
        allUsers = userDao.getAllUsers()
    }

    fun insert(user: UserInfoModel) {
        val insertUserAsyncTask = InsertUserAsyncTask(userDao).execute(user)
    }

    fun getAllUsers(): LiveData<List<UserInfoModel>> {
        return allUsers
    }

    private class InsertUserAsyncTask(userDao: UserDao) : AsyncTask<UserInfoModel, Unit, Unit>() {
        val user = userDao

        override fun doInBackground(vararg p0: UserInfoModel?) {
            user.insertUsers(p0[0]!!)
        }
    }
}