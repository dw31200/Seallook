package com.seallook.androidx.domain.model

import com.seallook.androidx.data.model.CounselingSchedule
import java.time.DayOfWeek

data class CounselingScheduleModel(
    val id: String,
    val email: String,
    val title: String,
    val repeatedDay: DayOfWeek,
    val time: String,
    val currentTime: Int,
    val clientCount: Int,
    val price: Int,
) {
    fun toDataModel(): CounselingSchedule {
        return CounselingSchedule(
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
        operator fun invoke(counselingSchedule: CounselingSchedule): CounselingScheduleModel {
            return CounselingScheduleModel(
                id = counselingSchedule.id,
                email = counselingSchedule.email,
                title = counselingSchedule.title,
                repeatedDay = counselingSchedule.repeatedDay,
                time = counselingSchedule.time,
                currentTime = counselingSchedule.currentTime,
                clientCount = counselingSchedule.clientCount,
                price = counselingSchedule.price,
            )
        }
    }
}
