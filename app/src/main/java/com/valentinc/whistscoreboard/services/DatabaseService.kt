package com.valentinc.whistscoreboard.services

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.valentinc.whistscoreboard.DAO.GameDao
import com.valentinc.whistscoreboard.DAO.RoundScoreDao
import com.valentinc.whistscoreboard.DAO.UserDao
import com.valentinc.whistscoreboard.converters.DateConverter
import com.valentinc.whistscoreboard.converters.UUIDConverter
import com.valentinc.whistscoreboard.models.Game
import com.valentinc.whistscoreboard.models.RoundScore
import com.valentinc.whistscoreboard.models.User

@Database(entities = arrayOf(User::class, RoundScore::class, Game::class), version = 1)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun roundScoreDao(): RoundScoreDao
    abstract fun gameDao(): GameDao
}

class DatabaseService() {

    fun getInstance(applicationContext: Context): AppDatabase{
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "WhistScoreBoard"
        ).build()
    }
}