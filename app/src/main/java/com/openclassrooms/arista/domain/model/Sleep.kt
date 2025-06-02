package com.openclassrooms.arista.domain.model

import com.openclassrooms.arista.data.entity.SleepDto
import java.time.LocalDateTime

data class Sleep(
    @JvmField var startTime: LocalDateTime,
    var duration: Int,
    var quality: Int
) {

    companion object {
        fun fromDto(dto: SleepDto): Sleep {
            return Sleep(
                startTime = dto.startTime,
                duration = dto.duration,
                quality = dto.quality
            )
        }
    }
}