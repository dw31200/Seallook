package com.seallook.androidx.domain.model

import com.seallook.androidx.data.model.Reservation

data class ReservationModel(
    val id: String,
    val counselorEmail: String,
    val scheduleId: String,
    val clientEmail: String,
    val date: String,
    val confirm: Boolean,
) {
    fun toDataModel(): Reservation {
        return Reservation(
            id = id,
            counselorEmail = counselorEmail,
            scheduleId = scheduleId,
            clientEmail = clientEmail,
            date = date,
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
                date = reservation.date,
                confirm = reservation.confirm,
            )
        }
    }
}
