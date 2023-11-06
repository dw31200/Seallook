package com.seallook.androidx.data.remote.counselor.schedule

import com.seallook.androidx.data.remote.model.CounselingScheduleResponse

interface CounselingScheduleApiService {
    suspend fun getAll(email: String): List<CounselingScheduleResponse>
}
