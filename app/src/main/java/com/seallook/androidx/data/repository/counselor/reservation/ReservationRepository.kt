package com.seallook.androidx.data.repository.counselor.reservation

import com.seallook.androidx.data.model.Reservation
import kotlinx.coroutines.flow.Flow

interface ReservationRepository {
    fun getItem(id: String): Flow<Reservation?>

    fun getClientList(email: String): Flow<List<Reservation>>

    fun getCounselingList(email: String): Flow<List<Reservation>>

    fun onCounselingListSnapshot(email: String): Flow<List<Reservation>>

    fun onClientListSnapshot(email: String): Flow<List<Reservation>>

    suspend fun insert(reservation: Reservation)

    suspend fun insert(reservationList: List<Reservation>)

    suspend fun set(reservation: Reservation)

    suspend fun update(id: String, confirm: Boolean)
}
