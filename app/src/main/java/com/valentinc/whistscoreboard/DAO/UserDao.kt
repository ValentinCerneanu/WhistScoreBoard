package com.valentinc.whistscoreboard.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.valentinc.whistscoreboard.models.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): LiveData<List<User>>

    @Query("SELECT * FROM user")
    suspend fun getAllAsList(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): User

    @Query("DELETE FROM user")
    fun clearTable()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<User>)

    @Insert
    @JvmSuppressWildcards
    fun createAll(objects: List<User>)

    @Delete
    fun delete(user: User)
}
