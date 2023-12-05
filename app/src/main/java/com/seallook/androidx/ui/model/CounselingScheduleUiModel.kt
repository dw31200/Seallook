package com.seallook.androidx.ui.model

import com.seallook.androidx.domain.model.CounselingScheduleModel
import java.time.DayOfWeek

data class CounselingScheduleUiModel(
    val id: String,
    val email: String,
    val title: String,
    val repeatedDay: DayOfWeek,
    val time: String,
    val currentTime: Int,
    val clientCount: Int,
    val price: Int,
) {
    fun toDomainModel(): CounselingScheduleModel {
        return CounselingScheduleModel(
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
        operator fun invoke(counselingScheduleModel: CounselingScheduleModel): CounselingScheduleUiModel {
            return CounselingScheduleUiModel(
                id = counselingScheduleModel.id,
                email = counselingScheduleModel.email,
                title = counselingScheduleModel.title,
                repeatedDay = counselingScheduleModel.repeatedDay,
                time = counselingScheduleModel.time,
                currentTime = counselingScheduleModel.currentTime,
                clientCount = counselingScheduleModel.clientCount,
                price = counselingScheduleModel.price,
            )
        }
    }
}
