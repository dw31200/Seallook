package com.seallook.androidx.ui.model

import com.seallook.androidx.domain.model.ReservationModel

data class ReservationUiModel(
    val id: Int,
    val counselorEmail: String,
    val scheduleId: Int,
    val clientEmail: String,
    val confirm: Boolean,
) {
    fun toDomainModel(): ReservationModel {
        return ReservationModel(
            id = id,
            counselorEmail = counselorEmail,
            scheduleId = scheduleId,
            clientEmail = clientEmail,
            confirm = confirm,
        )
    }

    companion object {
        operator fun invoke(reservationModel: ReservationModel): ReservationUiModel {
            return ReservationUiModel(
                id = reservationModel.id,
                counselorEmail = reservationModel.counselorEmail,
                scheduleId = reservationModel.scheduleId,
                clientEmail = reservationModel.clientEmail,
                confirm = reservationModel.confirm,
            )
        }
    }
}
