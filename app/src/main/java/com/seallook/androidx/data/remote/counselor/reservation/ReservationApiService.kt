package com.seallook.androidx.data.remote.counselor.reservation

import com.seallook.androidx.data.remote.model.ReservationResponse

interface ReservationApiService {
    suspend fun getList(email: String): List<ReservationResponse>

    suspend fun set(reservation: ReservationResponse)
}
