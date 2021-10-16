package com.valentinc.whistscoreboard.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(indices = [Index(value = ["name", "gameId"], unique = true)])
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "gameId") var gameId: UUID?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "score") var score: Int
) {
    constructor(name: String) : this(0,null, name, 0)
    constructor(name: String, gameId: UUID) : this(0,gameId, name, 0)
}