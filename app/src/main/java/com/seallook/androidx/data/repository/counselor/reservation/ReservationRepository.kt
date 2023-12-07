package com.seallook.androidx.data.repository.counselor.reservation

import com.seallook.androidx.data.model.Reservation

interface ReservationRepository {
    suspend fun getClientList(email: String): List<Reservation>

    suspend fun getCounselorList(email: String): List<Reservation>

    suspend fun set(reservation: Reservation)
}
