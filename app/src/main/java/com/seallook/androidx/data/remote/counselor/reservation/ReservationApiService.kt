package com.seallook.androidx.data.remote.counselor.reservation

import com.seallook.androidx.data.remote.model.ReservationResponse
import com.seallook.androidx.share.UserTypeOption
import kotlinx.coroutines.flow.Flow

interface ReservationApiService {
    fun onListSnapshot(email: String, userType: UserTypeOption): Flow<List<ReservationResponse>>

    fun onCounselingListSnapshot(email: String): Flow<List<ReservationResponse>>

    fun onClientListSnapshot(email: String): Flow<List<ReservationResponse>>

    suspend fun set(reservation: ReservationResponse)

    suspend fun update(id: String, confirm: Boolean)
}
