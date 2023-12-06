package com.seallook.androidx.data.model

import com.seallook.androidx.data.local.model.CounselingScheduleEntity
import com.seallook.androidx.data.remote.model.CounselingScheduleResponse
import java.time.DayOfWeek

data class CounselingSchedule(
    val id: String,
    val email: String,
    val title: String,
    val repeatedDay: DayOfWeek,
    val time: String,
    val currentTime: Int,
    val clientCount: Int,
    val price: Int,
) {
    fun toLocalModel(): CounselingScheduleEntity {
        return CounselingScheduleEntity(
            id = id,
            email = email,
            title = title,
            repeatedDay = repeatedDay,
            time = time,
            currentTime = currentTime,
            clientCount = clientCount,
            price = price,
        )
    }

    fun toRemoteModel(): CounselingScheduleResponse {
        return CounselingScheduleResponse(
            id = id,
            email = email,
            title = title,
            repeatedDay = repeatedDay,
            time = time,
            currentTime = currentTime,
            clientCount = clientCount,
            price = price,
        )
    }

    companion object {
        operator fun invoke(counselingScheduleEntity: CounselingScheduleEntity): CounselingSchedule {
            return CounselingSchedule(
                id = counselingScheduleEntity.id,
                email = counselingScheduleEntity.email,
                title = counselingScheduleEntity.title,
                repeatedDay = counselingScheduleEntity.repeatedDay,
                time = counselingScheduleEntity.time,
                currentTime = counselingScheduleEntity.currentTime,
                clientCount = counselingScheduleEntity.clientCount,
                price = counselingScheduleEntity.price,
            )
        }

        operator fun invoke(counselingScheduleResponse: CounselingScheduleResponse): CounselingSchedule {
            return CounselingSchedule(
                id = counselingScheduleResponse.id,
                email = counselingScheduleResponse.email,
                title = counselingScheduleResponse.title,
                repeatedDay = counselingScheduleResponse.repeatedDay,
                time = counselingScheduleResponse.time,
                currentTime = counselingScheduleResponse.currentTime,
                clientCount = counselingScheduleResponse.clientCount,
                price = counselingScheduleResponse.price,
            )
        }
    }
}
