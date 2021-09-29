package com.valentinc.whistscoreboard.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["name"], unique = true)])
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "score") var score: Int
) {
    constructor(name: String) : this(0, name, 0)
}