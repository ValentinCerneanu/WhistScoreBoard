package com.valentinc.whistscoreboard.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Answer(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "text") val text: String?,
    @ColumnInfo(name = "isPositive") var isPositive: Boolean?
) {
    constructor(text: String, isPositive: Boolean) : this(0, text, isPositive)
}