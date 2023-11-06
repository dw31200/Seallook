package com.seallook.androidx.domain.model

import com.seallook.androidx.data.model.CounselingSchedule
import java.util.Date

data class CounselingScheduleModel(
    val email: String,
    val id: Int,
    val date: Date,
    val typeId: Int,
) {
    fun toDataModel(): CounselingSchedule {
        return CounselingSchedule(
            email = email,
            id = id,
            date = date,
            typeId = typeId,
        )
    }

    companion object {
        operator fun invoke(counselingSchedule: CounselingSchedule): CounselingScheduleModel {
            return CounselingScheduleModel(
                email = counselingSchedule.email,
                id = counselingSchedule.id,
                date = counselingSchedule.date,
                typeId = counselingSchedule.typeId,
            )
        }
    }
}
