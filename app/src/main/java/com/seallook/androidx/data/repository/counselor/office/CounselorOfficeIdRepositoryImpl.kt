package com.seallook.androidx.data.repository.counselor.office

import com.seallook.androidx.data.local.CounselorOfficeIdDao
import com.seallook.androidx.data.model.CounselorOfficeId
import com.seallook.androidx.data.remote.counselor.office.CounselorOfficeIdApiService
import javax.inject.Inject

class CounselorOfficeIdRepositoryImpl @Inject constructor(
    private val counselorOfficeIdDao: CounselorOfficeIdDao,
    private val counselorOfficeIdApiService: CounselorOfficeIdApiService,
) : CounselorOfficeIdRepository {
    override suspend fun setItem(counselorOfficeId: CounselorOfficeId) {
        counselorOfficeIdDao.insert(counselorOfficeId.toLocalModel())
    }

    override suspend fun getItem(email: String): CounselorOfficeId? {
        return counselorOfficeIdDao.getItem(email)?.let {
            CounselorOfficeId(it)
        }
    }

    override suspend fun get(email: String): CounselorOfficeId? {
        return counselorOfficeIdApiService.get(email)?.let {
            CounselorOfficeId(it)
        }
    }

    override suspend fun update(counselorOfficeId: CounselorOfficeId) {
        counselorOfficeIdApiService.update(counselorOfficeId.toRemoteModel())
    }
}
