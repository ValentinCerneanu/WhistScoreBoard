package com.valentinc.whistscoreboard.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.valentinc.whistscoreboard.models.Game

@Dao
interface GameDao {
    @Query("SELECT * FROM game")
    fun getAll(): LiveData<List<Game>>

    @Query("SELECT * FROM game")
    suspend fun getAllAsList(): List<Game>

    @Query("DELETE FROM game")
    fun clearTable()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(games: List<Game>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(games: Game)

    @Insert
    @JvmSuppressWildcards
    fun createAll(objects: List<Game>)

    @Delete
    fun delete(game: Game)
}