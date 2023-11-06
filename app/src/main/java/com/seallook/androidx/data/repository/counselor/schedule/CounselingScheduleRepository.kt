package com.seallook.androidx.data.repository.counselor.schedule

import com.seallook.androidx.data.model.CounselingSchedule

interface CounselingScheduleRepository {
    suspend fun getAll(email: String): List<CounselingSchedule>
}
