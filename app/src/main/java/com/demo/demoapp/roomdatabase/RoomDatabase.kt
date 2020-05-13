package com.demo.demoapp.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.demo.demoapp.dao.UserDao
import com.demo.demoapp.model.UserInfoModel

@Database(entities = [(UserInfoModel::class)],version = 1)
public abstract class AppDb : RoomDatabase() {

    abstract fun UserDao(): UserDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDb? = null

        fun getDatabase(context: Context): AppDb {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDb::class.java,
                    "AppDb"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
