package com.seallook.androidx.data.repository.counselor.schedule

import com.seallook.androidx.data.model.CounselingSchedule
import kotlinx.coroutines.flow.Flow
import java.time.DayOfWeek

interface CounselingScheduleRepository {
    fun getItem(id: String): Flow<CounselingSchedule?>

    suspend fun getAllFromFirebase(email: String): List<CounselingSchedule>

    suspend fun getAllFromLocal(email: String): List<CounselingSchedule>

    suspend fun getCounselingSchedulesOnDate(day: DayOfWeek): List<CounselingSchedule>

    suspend fun insert(counselingScheduleList: List<CounselingSchedule>)
}
