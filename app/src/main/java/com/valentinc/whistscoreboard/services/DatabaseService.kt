package com.valentinc.whistscoreboard.services

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.valentinc.whistscoreboard.DAO.UserDao
import com.valentinc.whistscoreboard.models.User

@Database(entities = arrayOf(User::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}

class DatabaseService() {

    fun getInstance(applicationContext: Context): AppDatabase{
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "WhistScoreBoard"
        ).build()
    }
}