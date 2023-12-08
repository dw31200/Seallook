package com.seallook.androidx.ui.model

import com.seallook.androidx.domain.model.ReservationModel

data class ReservationUiModel(
    val id: String,
    val counselorEmail: String,
    val scheduleId: String,
    val clientEmail: String,
    val date: String,
    val confirm: Boolean,
) {
    fun toDomainModel(): ReservationModel {
        return ReservationModel(
            id = id,
            counselorEmail = counselorEmail,
            scheduleId = scheduleId,
            clientEmail = clientEmail,
            date = date,
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
                date = reservationModel.date,
                confirm = reservationModel.confirm,
            )
        }
    }
}
