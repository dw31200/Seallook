package com.seallook.androidx.data.repository.counselor.schedule

import com.seallook.androidx.data.local.CounselingScheduleDao
import com.seallook.androidx.data.model.CounselingSchedule
import com.seallook.androidx.data.remote.counselor.schedule.CounselingScheduleApiService
import java.time.DayOfWeek
import javax.inject.Inject

class CounselingScheduleRepositoryImpl @Inject constructor(
    private val counselingScheduleApiService: CounselingScheduleApiService,
    private val counselingScheduleDao: CounselingScheduleDao,
) : CounselingScheduleRepository {
    override suspend fun getAllFromFirebase(email: String): List<CounselingSchedule> {
        return counselingScheduleApiService.getAll(email).map {
            CounselingSchedule(it)
        }
    }

    override suspend fun getAllFromLocal(email: String): List<CounselingSchedule> {
        return counselingScheduleDao.getAll(email).map {
            CounselingSchedule(it)
        }
    }

    override suspend fun getCounselingSchedulesOnDate(day: DayOfWeek): List<CounselingSchedule> {
        return counselingScheduleDao.getCounselingSchedulesOnDate(day).map {
            CounselingSchedule(it)
        }
    }

    override suspend fun insert(counselingScheduleList: List<CounselingSchedule>) {
        counselingScheduleDao.insert(
            counselingScheduleList.map {
                it.toLocalModel()
            },
        )
    }
}
