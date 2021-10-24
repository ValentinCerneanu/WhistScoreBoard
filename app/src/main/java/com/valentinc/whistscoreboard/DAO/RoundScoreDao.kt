package com.valentinc.whistscoreboard.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.valentinc.whistscoreboard.models.RoundScore
import java.util.*

@Dao
interface RoundScoreDao {
    @Query("SELECT * FROM roundScore")
    fun getAll(): LiveData<List<RoundScore>>

    @Query("SELECT * FROM roundScore WHERE gameId = :gameId")
    fun getRoundScoreByGame(gameId: UUID): LiveData<List<RoundScore>>

    @Query("DELETE FROM roundScore")
    fun clearTable()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(roundScores: List<RoundScore>)

    @Insert
    @JvmSuppressWildcards
    fun createAll(objects: List<RoundScore>)

    @Delete
    fun delete(roundScore: RoundScore)
}