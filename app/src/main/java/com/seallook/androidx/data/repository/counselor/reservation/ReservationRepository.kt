package com.seallook.androidx.data.repository.counselor.reservation

import com.seallook.androidx.data.model.Reservation
import kotlinx.coroutines.flow.Flow

interface ReservationRepository {
    suspend fun getClientList(email: String): List<Reservation>

    suspend fun getCounselingList(email: String): List<Reservation>

    fun onCounselingListSnapshot(email: String): Flow<List<Reservation>>

    fun onClientListSnapshot(email: String): Flow<List<Reservation>>

    suspend fun set(reservation: Reservation)

    suspend fun update(id: String, confirm: Boolean)
}
