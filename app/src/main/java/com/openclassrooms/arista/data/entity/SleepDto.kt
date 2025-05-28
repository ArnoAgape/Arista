package com.openclassrooms.arista.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "sleeping")
data class SleepDto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "sleeping_id")
    var sleepingId: Int = 0,


    @ColumnInfo(name = "timestamp_sleeping")
    var startTime: Date = Date(),

    @ColumnInfo(name = "duration")
    var duration: Int,

    @ColumnInfo(name = "quality")
    var quality: Int
)
