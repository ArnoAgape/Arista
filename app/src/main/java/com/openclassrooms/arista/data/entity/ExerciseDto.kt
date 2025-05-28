package com.openclassrooms.arista.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "exercise")
data class ExerciseDto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "exercise_id")
    var exerciseId: Int = 0,


    @ColumnInfo(name = "timestamp_start")
    var startTime: Date = Date(),


    @ColumnInfo(name = "duration")
    var duration: Int,


    @ColumnInfo(name = "exercise_type")
    var type: String,


    @ColumnInfo(name = "exercise_intensity")
    var intensity: String
)
