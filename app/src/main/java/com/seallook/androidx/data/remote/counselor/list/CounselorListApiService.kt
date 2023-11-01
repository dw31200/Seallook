package com.seallook.androidx.data.remote.counselor.list

import com.seallook.androidx.data.remote.model.CounselorListResponse

interface CounselorListApiService {
    suspend fun getAll(): List<CounselorListResponse>
}
