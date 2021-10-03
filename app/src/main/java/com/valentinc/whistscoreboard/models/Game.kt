package com.valentinc.whistscoreboard.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Game(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "users") var users: List<User>
) {
    constructor(users:  List<User>) : this(0, users)
}