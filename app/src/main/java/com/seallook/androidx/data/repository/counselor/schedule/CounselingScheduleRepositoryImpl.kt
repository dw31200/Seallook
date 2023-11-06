package com.seallook.androidx.data.repository.counselor.schedule

import com.seallook.androidx.data.model.CounselingSchedule
import com.seallook.androidx.data.remote.counselor.schedule.CounselingScheduleApiService
import javax.inject.Inject

class CounselingScheduleRepositoryImpl @Inject constructor(
    private val counselingScheduleApiService: CounselingScheduleApiService,
) : CounselingScheduleRepository {
    override suspend fun getAll(email: String): List<CounselingSchedule> {
        return counselingScheduleApiService.getAll(email).map {
            CounselingSchedule(it)
        }
    }
}
