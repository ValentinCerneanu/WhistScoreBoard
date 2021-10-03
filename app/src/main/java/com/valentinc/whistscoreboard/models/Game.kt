package com.valentinc.whistscoreboard.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Game(
    @PrimaryKey @NonNull val uid: UUID,
    @ColumnInfo(name = "date") var date: Date
) {
    constructor(date:  Date) : this(UUID.randomUUID(), date)
}