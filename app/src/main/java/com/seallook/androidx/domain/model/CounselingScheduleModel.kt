package com.seallook.androidx.domain.model

import com.seallook.androidx.data.model.CounselingSchedule
import java.util.Date

data class CounselingScheduleModel(
    val email: String,
    val id: String,
    val date: Date,
    val typeId: String,
    val reservation: Boolean,
) {
    fun toDataModel(): CounselingSchedule {
        return CounselingSchedule(
            email = email,
            id = id,
            date = date,
            typeId = typeId,
            reservation = reservation,
        )
    }

    companion object {
        operator fun invoke(counselingSchedule: CounselingSchedule): CounselingScheduleModel {
            return CounselingScheduleModel(
                email = counselingSchedule.email,
                id = counselingSchedule.id,
                date = counselingSchedule.date,
                typeId = counselingSchedule.typeId,
                reservation = counselingSchedule.reservation,
            )
        }
    }
}
