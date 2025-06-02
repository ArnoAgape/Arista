package com.openclassrooms.arista.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "sleeping")
data class SleepDto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "timestamp_sleeping")
    var startTime: LocalDateTime,


    @ColumnInfo(name = "duration")
    var duration: Int,

    @ColumnInfo(name = "quality")
    var quality: Int
)
