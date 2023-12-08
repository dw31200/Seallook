package com.seallook.androidx.data.repository.counselor.reservation

import com.seallook.androidx.data.model.Reservation
import com.seallook.androidx.data.remote.counselor.reservation.ReservationApiService
import javax.inject.Inject

class ReservationRepositoryImpl @Inject constructor(
    private val reservationApiService: ReservationApiService,
) : ReservationRepository {
    override suspend fun getClientList(email: String): List<Reservation> {
        return reservationApiService.getClientList(email).map {
            Reservation(it)
        }
    }

    override suspend fun getCounselingList(email: String): List<Reservation> {
        return reservationApiService.getCounselingList(email).map {
            Reservation(it)
        }
    }

    override suspend fun set(reservation: Reservation) {
        reservationApiService.set(reservation.toRemoteModel())
    }

    override suspend fun update(id: String, confirm: Boolean) {
        reservationApiService.update(id, confirm)
    }
}
