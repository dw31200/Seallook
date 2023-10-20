package com.seallook.androidx.data.model

import com.seallook.androidx.data.local.model.ReservationEntity
import com.seallook.androidx.data.remote.model.ReservationResponse

data class Reservation(
    val id: Int,
    val scheduleId: Int,
    val clientUid: String,
) {
    fun toLocalModel(): ReservationEntity {
        return ReservationEntity(
            id = id,
            scheduleId = scheduleId,
            clientUid = clientUid,
        )
    }

    fun toRemoteModel(): ReservationResponse {
        return ReservationResponse(
            id = id,
            scheduleId = scheduleId,
            clientUid = clientUid,
        )
    }

    companion object {
        operator fun invoke(reservationEntity: ReservationEntity): Reservation {
            return Reservation(
                id = reservationEntity.id,
                scheduleId = reservationEntity.scheduleId,
                clientUid = reservationEntity.clientUid,
            )
        }

        operator fun invoke(reservationResponse: ReservationResponse): Reservation {
            return Reservation(
                id = reservationResponse.id,
                scheduleId = reservationResponse.scheduleId,
                clientUid = reservationResponse.clientUid,
            )
        }
    }
}
