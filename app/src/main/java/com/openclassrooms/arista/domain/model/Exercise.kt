package com.openclassrooms.arista.domain.model

import com.openclassrooms.arista.data.entity.ExerciseDto
import java.time.LocalDateTime

data class Exercise(
    val id: Long?,
    var startTime: LocalDateTime,
    var duration: Int,
    var type: ExerciseType,
    var intensity: ExerciseIntensity
) {

    fun toDto(): ExerciseDto {
        return ExerciseDto(
            id = id,
            startTime = startTime,
            duration = duration,
            type = type,
            intensity = intensity
        )
    }

    companion object {
        fun fromDto(dto: ExerciseDto): Exercise {
            return Exercise(
                id = dto.id,
                startTime = dto.startTime,
                duration = dto.duration,
                type = dto.type,
                intensity = dto.intensity
            )
        }
    }
}