package com.seallook.androidx.data.repository.counselor.schedule

import com.seallook.androidx.data.local.CounselingScheduleDao
import com.seallook.androidx.data.model.CounselingSchedule
import com.seallook.androidx.data.remote.counselor.schedule.CounselingScheduleApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.DayOfWeek
import javax.inject.Inject

class CounselingScheduleRepositoryImpl @Inject constructor(
    private val counselingScheduleApiService: CounselingScheduleApiService,
    private val counselingScheduleDao: CounselingScheduleDao,
) : CounselingScheduleRepository {
    override fun getItem(id: String): Flow<CounselingSchedule?> {
        return counselingScheduleDao.getItem(id).map {
            it?.let {
                CounselingSchedule(it)
            }
        }
    }

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

    override suspend fun deleteAll() {
        counselingScheduleDao.deleteAll()
    }
}
