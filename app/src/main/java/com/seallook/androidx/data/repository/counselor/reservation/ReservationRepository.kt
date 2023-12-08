package com.seallook.androidx.data.repository.counselor.reservation

import com.seallook.androidx.data.model.Reservation

interface ReservationRepository {
    suspend fun getClientList(email: String): List<Reservation>

    suspend fun getCounselingList(email: String): List<Reservation>

    suspend fun set(reservation: Reservation)

    suspend fun update(id: String, confirm: Boolean)
}
