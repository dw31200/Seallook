package com.seallook.androidx.data.remote.counselor.counselingtype

import com.seallook.androidx.data.remote.model.CounselingTypeListResponse
import com.seallook.androidx.data.remote.model.CounselingTypeResponse

interface CounselingTypeApiService {
    suspend fun getAll(uid: String): List<CounselingTypeResponse>

    suspend fun updateList(uid: String, type: CounselingTypeListResponse)
}
