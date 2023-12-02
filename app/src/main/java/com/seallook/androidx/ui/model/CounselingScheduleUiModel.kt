package com.seallook.androidx.ui.model

import com.seallook.androidx.domain.model.CounselingScheduleModel
import java.util.Date

data class CounselingScheduleUiModel(
    val email: String,
    val id: String,
    val date: Date,
    val typeId: String,
    val reservation: Boolean,
) {
    fun toDomainModel(): CounselingScheduleModel {
        return CounselingScheduleModel(
            email = email,
            id = id,
            date = date,
            typeId = typeId,
            reservation = reservation,
        )
    }

    companion object {
        operator fun invoke(counselingScheduleModel: CounselingScheduleModel): CounselingScheduleUiModel {
            return CounselingScheduleUiModel(
                email = counselingScheduleModel.email,
                id = counselingScheduleModel.id,
                date = counselingScheduleModel.date,
                typeId = counselingScheduleModel.typeId,
                reservation = counselingScheduleModel.reservation,
            )
        }
    }
}
