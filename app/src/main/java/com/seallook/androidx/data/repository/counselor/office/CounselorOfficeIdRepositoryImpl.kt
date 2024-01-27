package com.seallook.androidx.data.repository.counselor.office

import com.seallook.androidx.data.local.CounselorOfficeIdDao
import com.seallook.androidx.data.model.CounselorOfficeId
import com.seallook.androidx.data.remote.counselor.office.CounselorOfficeIdApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CounselorOfficeIdRepositoryImpl @Inject constructor(
    private val counselorOfficeIdDao: CounselorOfficeIdDao,
    private val counselorOfficeIdApiService: CounselorOfficeIdApiService,
) : CounselorOfficeIdRepository {
    override suspend fun setItem(counselorOfficeId: CounselorOfficeId) {
        counselorOfficeIdDao.insert(counselorOfficeId.toLocalModel())
    }

    override fun getItem(email: String): Flow<CounselorOfficeId?> {
        return flow {
            val local = counselorOfficeIdDao.getItem(email)?.let {
                CounselorOfficeId(it)
            }
            if (local != null) {
                emit(local)
            } else {
                val remote = counselorOfficeIdApiService.get(email)?.let {
                    CounselorOfficeId(it)
                }
                remote?.let {
                    emit(it)
                    counselorOfficeIdDao.insert(it.toLocalModel())
                }
            }
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
