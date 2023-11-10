package com.seallook.androidx.data.repository.counselor.reservation

import com.seallook.androidx.data.model.Reservation

interface ReservationRepository {
    suspend fun getList(email: String): List<Reservation>

    suspend fun set(reservation: Reservation)
}
