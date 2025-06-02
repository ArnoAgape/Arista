package com.openclassrooms.arista.data.converter

import androidx.room.TypeConverter
import com.openclassrooms.arista.domain.model.ExerciseIntensity
import com.openclassrooms.arista.domain.model.ExerciseType
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

class Converters {

    @TypeConverter
    fun fromExerciseType(value: ExerciseType): String = value.name

    @TypeConverter
    fun toExerciseType(value: String): ExerciseType = ExerciseType.valueOf(value)

    @TypeConverter
    fun fromExerciseIntensity(value: ExerciseIntensity): String = value.name

    @TypeConverter
    fun toExerciseIntensity(value: String): ExerciseIntensity = ExerciseIntensity.valueOf(value)


    // LocalDateTime <-> Long
    @TypeConverter
    fun fromLocalDateTime(value: LocalDateTime): Long =
        value.atZone(ZoneOffset.UTC).toInstant().toEpochMilli()

    @TypeConverter
    fun toLocalDateTime(value: Long): LocalDateTime =
        Instant.ofEpochMilli(value).atZone(ZoneOffset.UTC).toLocalDateTime()
}
