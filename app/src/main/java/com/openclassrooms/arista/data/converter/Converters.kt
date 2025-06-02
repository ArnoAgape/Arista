package com.openclassrooms.arista.data.converter

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

class Converters {

    // LocalDateTime <-> Long
    @TypeConverter
    fun fromLocalDateTime(value: LocalDateTime): Long =
        value.atZone(ZoneOffset.UTC).toInstant().toEpochMilli()

    @TypeConverter
    fun toLocalDateTime(value: Long): LocalDateTime =
        Instant.ofEpochMilli(value).atZone(ZoneOffset.UTC).toLocalDateTime()
}
