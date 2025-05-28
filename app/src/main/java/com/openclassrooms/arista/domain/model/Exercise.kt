package com.openclassrooms.arista.domain.model

import java.time.LocalDateTime

data class Exercise(
    val exerciseId: Long? = null,
    var startTime: LocalDateTime,
    var duration: Int,
    var type: ExerciseType,
    var intensity: Int
)