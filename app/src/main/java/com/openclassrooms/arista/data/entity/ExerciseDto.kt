package com.openclassrooms.arista.data.entity

import com.openclassrooms.arista.data.entity.ExerciseDto.Entity

@Entity(tableName = "exercise")
data class ExerciseDto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0,


    @ColumnInfo(name = "start_time")
    var startTime: Long,


    @ColumnInfo(name = "duration")
    var duration: Int,


    @ColumnInfo(name = "category")
    var category: String,


    @ColumnInfo(name = "intensity")
    var intensity: Int
) {
    annotation class Entity(val tableName: String)
    annotation class PrimaryKey(val autoGenerate: Boolean)
    annotation class ColumnInfo(val name: String)
}
