package com.seallook.androidx.data.remote.counselor.reservation

import com.seallook.androidx.data.remote.model.ReservationResponse

interface ReservationApiService {
    suspend fun getClientList(email: String): List<ReservationResponse>

    suspend fun getCounselorList(email: String): List<ReservationResponse>

    suspend fun set(reservation: ReservationResponse)
}
