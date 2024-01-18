package com.seallook.androidx.data.remote.counselor.basicinfo

import com.seallook.androidx.data.remote.model.CounselorInfoResponse

interface CounselorInfoApiService {
    suspend fun getItem(email: String): CounselorInfoResponse?

    suspend fun getAll(): List<CounselorInfoResponse>

    suspend fun setItem(info: CounselorInfoResponse)
}
