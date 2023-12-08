package com.seallook.androidx.data.remote.counselor.reservation

import com.seallook.androidx.data.remote.model.ReservationResponse

interface ReservationApiService {
    suspend fun getClientList(email: String): List<ReservationResponse>

    suspend fun getCounselingList(email: String): List<ReservationResponse>

    suspend fun set(reservation: ReservationResponse)

    suspend fun update(id: String, confirm: Boolean)
}
