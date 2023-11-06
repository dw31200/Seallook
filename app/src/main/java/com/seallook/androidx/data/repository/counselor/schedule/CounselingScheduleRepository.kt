package com.seallook.androidx.data.repository.counselor.schedule

import com.seallook.androidx.data.model.CounselingSchedule

interface CounselingScheduleRepository {
    suspend fun getAllFromFirebase(email: String): List<CounselingSchedule>

    suspend fun getAllFromLocal(email: String): List<CounselingSchedule>

    suspend fun insert(counselingScheduleList: List<CounselingSchedule>)
}
