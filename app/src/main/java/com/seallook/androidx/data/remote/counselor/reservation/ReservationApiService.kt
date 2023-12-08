package com.seallook.androidx.data.remote.counselor.reservation

import com.seallook.androidx.data.remote.model.ReservationResponse
import kotlinx.coroutines.flow.Flow

interface ReservationApiService {
    suspend fun getClientList(email: String): List<ReservationResponse>

    suspend fun getCounselingList(email: String): List<ReservationResponse>

    fun onCounselingListSnapshot(email: String): Flow<List<ReservationResponse>>

    fun onClientListSnapshot(email: String): Flow<List<ReservationResponse>>

    suspend fun set(reservation: ReservationResponse)

    suspend fun update(id: String, confirm: Boolean)
}
