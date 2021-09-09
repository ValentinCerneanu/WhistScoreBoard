package com.valentinc.whistscoreboard.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Bet(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "bet") val bet: Int,
    @ColumnInfo(name = "isPossible") var isPossible: Boolean
) {
    constructor(bet: Int, isPossible: Boolean) : this(0, bet, isPossible)
    constructor(bet: Int) : this(0, bet, true)
}