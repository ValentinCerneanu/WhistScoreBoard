package com.valentinc.whistscoreboard.converters

import androidx.room.TypeConverter
import java.util.*

object DateConverter {
    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return if (timestamp == null) null else Date(timestamp)
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }
}

object UUIDConverter {
    @TypeConverter
    fun fromUUID(uuid: UUID?): String? {
        if(uuid != null)
            return uuid.toString()
        else
            return null
    }

    @TypeConverter
    fun uuidFromString(string: String?): UUID?{
        if(string != null)
            return UUID.fromString(string)
        else
            return null
    }
}
