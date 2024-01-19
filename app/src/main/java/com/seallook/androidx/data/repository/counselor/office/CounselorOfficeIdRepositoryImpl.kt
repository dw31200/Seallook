package com.seallook.androidx.data.repository.counselor.office

import com.seallook.androidx.data.local.CounselorOfficeIdDao
import com.seallook.androidx.data.model.CounselorOfficeId
import javax.inject.Inject

class CounselorOfficeIdRepositoryImpl @Inject constructor(
    private val counselorOfficeIdDao: CounselorOfficeIdDao,
) : CounselorOfficeIdRepository {
    override suspend fun setItem(counselorOfficeId: CounselorOfficeId) {
        counselorOfficeIdDao.insert(counselorOfficeId.toLocalModel())
    }

    override suspend fun getItem(email: String): CounselorOfficeId? {
        return counselorOfficeIdDao.getItem(email)?.let {
            CounselorOfficeId(it)
        }
    }
}
