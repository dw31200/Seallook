package com.seallook.androidx.domain.model

import com.seallook.androidx.data.model.Reservation

data class ReservationModel(
    val id: Int,
    val counselorEmail: String,
    val scheduleId: Int,
    val clientEmail: String,
    val confirm: Boolean,
) {
    fun toDataModel(): Reservation {
        return Reservation(
            id = id,
            counselorEmail = counselorEmail,
            scheduleId = scheduleId,
            clientEmail = clientEmail,
            confirm = confirm,
        )
    }

    companion object {
        operator fun invoke(reservation: Reservation): ReservationModel {
            return ReservationModel(
                id = reservation.id,
                counselorEmail = reservation.counselorEmail,
                scheduleId = reservation.scheduleId,
                clientEmail = reservation.clientEmail,
                confirm = reservation.confirm,
            )
        }
    }
}
