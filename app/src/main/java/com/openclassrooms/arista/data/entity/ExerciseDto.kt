package com.openclassrooms.arista.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.openclassrooms.arista.domain.model.ExerciseIntensity
import com.openclassrooms.arista.domain.model.ExerciseType
import java.time.LocalDateTime

@Entity(tableName = "exercise")
data class ExerciseDto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = 0,


    @ColumnInfo(name = "timestamp_start")
    var startTime: LocalDateTime,

    @ColumnInfo(name = "duration")
    var duration: Int,

    @ColumnInfo(name = "exercise_type")
    var type: ExerciseType,

    @ColumnInfo(name = "exercise_intensity")
    var intensity: ExerciseIntensity
    
)
