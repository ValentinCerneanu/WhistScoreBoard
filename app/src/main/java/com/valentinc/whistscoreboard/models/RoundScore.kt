package com.valentinc.whistscoreboard.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoundScore(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "bet") var bet: Int?,
    @ColumnInfo(name = "score") var score: Int?
) {
    constructor(bet: Int, score: Int) : this(0, bet, score)
}