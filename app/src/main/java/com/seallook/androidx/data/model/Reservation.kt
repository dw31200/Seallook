package com.seallook.androidx.data.model

import com.seallook.androidx.data.local.model.ReservationEntity
import com.seallook.androidx.data.remote.model.ReservationResponse
import java.util.Date

data class Reservation(
    val id: String,
    val counselorEmail: String,
    val scheduleId: String,
    val clientEmail: String,
    val date: Date,
    val confirm: Boolean,
) {
    fun toLocalModel(): ReservationEntity {
        return ReservationEntity(
            id = id,
            counselorEmail = counselorEmail,
            scheduleId = scheduleId,
            clientEmail = clientEmail,
            date = date,
            confirm = confirm,
        )
    }

    fun toRemoteModel(): ReservationResponse {
        return ReservationResponse(
            id = id,
            counselorEmail = counselorEmail,
            scheduleId = scheduleId,
            clientEmail = clientEmail,
            date = date,
            confirm = confirm,
        )
    }

    companion object {
        operator fun invoke(reservationEntity: ReservationEntity): Reservation {
            return Reservation(
                id = reservationEntity.id,
                counselorEmail = reservationEntity.counselorEmail,
                scheduleId = reservationEntity.scheduleId,
                clientEmail = reservationEntity.clientEmail,
                date = reservationEntity.date,
                confirm = reservationEntity.confirm,
            )
        }

        operator fun invoke(reservationResponse: ReservationResponse): Reservation {
            return Reservation(
                id = reservationResponse.id,
                counselorEmail = reservationResponse.counselorEmail,
                scheduleId = reservationResponse.scheduleId,
                clientEmail = reservationResponse.clientEmail,
                date = reservationResponse.date,
                confirm = reservationResponse.confirm,
            )
        }
    }
}
