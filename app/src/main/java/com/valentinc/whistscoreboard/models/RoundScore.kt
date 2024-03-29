package com.valentinc.whistscoreboard.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class RoundScore(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "gameId") var gameId: UUID?,
    @ColumnInfo(name = "bet") var bet: Int,
    @ColumnInfo(name = "score") var score: Int,
    @ColumnInfo(name = "actualBet") var actualBet: Int = 0,
    @ColumnInfo(name = "isBetTrue") var isBetTrue: Boolean = true
) {
    constructor(bet: Int, score: Int) : this(0, null, bet, score)
}