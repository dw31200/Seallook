package com.seallook.androidx.data.repository.counselor.reservation

import com.seallook.androidx.data.model.Reservation
import com.seallook.androidx.data.remote.counselor.reservation.ReservationApiService
import javax.inject.Inject

class ReservationRepositoryImpl @Inject constructor(
    private val reservationApiService: ReservationApiService,
) : ReservationRepository {
    override suspend fun getList(email: String): List<Reservation> {
        return reservationApiService.getList(email).map {
            Reservation(it)
        }
    }

    override suspend fun set(reservation: Reservation) {
        reservationApiService.set(reservation.toRemoteModel())
    }
}
