package com.seallook.androidx.data.model

import com.seallook.androidx.data.local.model.ReservationEntity
import com.seallook.androidx.data.remote.model.ReservationResponse

data class Reservation(
    val id: Int,
    val counselorEmail: String,
    val scheduleId: Int,
    val clientEmail: String,
    val confirm: Boolean,
) {
    fun toLocalModel(): ReservationEntity {
        return ReservationEntity(
            id = id,
            counselorEmail = counselorEmail,
            scheduleId = scheduleId,
            clientEmail = clientEmail,
            confirm = confirm,
        )
    }

    fun toRemoteModel(): ReservationResponse {
        return ReservationResponse(
            id = id,
            counselorEmail = counselorEmail,
            scheduleId = scheduleId,
            clientEmail = clientEmail,
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
                confirm = reservationEntity.confirm,
            )
        }

        operator fun invoke(reservationResponse: ReservationResponse): Reservation {
            return Reservation(
                id = reservationResponse.id,
                counselorEmail = reservationResponse.counselorEmail,
                scheduleId = reservationResponse.scheduleId,
                clientEmail = reservationResponse.clientEmail,
                confirm = reservationResponse.confirm,
            )
        }
    }
}
