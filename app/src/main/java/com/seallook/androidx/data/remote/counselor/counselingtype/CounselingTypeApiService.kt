package com.seallook.androidx.data.remote.counselor.counselingtype

import com.seallook.androidx.data.remote.model.CounselingTypeResponse

interface CounselingTypeApiService {
    suspend fun getAll(email: String): List<CounselingTypeResponse>

    suspend fun updateList(email: String, type: List<CounselingTypeResponse>)
}
