package com.seallook.androidx.data.repository.counselor.basicinfo

import com.seallook.androidx.data.local.CounselorInfoDao
import com.seallook.androidx.data.model.CounselorInfo
import com.seallook.androidx.data.remote.counselor.basicinfo.CounselorInfoApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CounselorInfoRepositoryImpl @Inject constructor(
    private val counselorInfoApiService: CounselorInfoApiService,
    private val counselorInfoDao: CounselorInfoDao,
) : CounselorInfoRepository {
    override suspend fun getItem(email: String): CounselorInfo? {
        return counselorInfoApiService.getItem(email)?.let { CounselorInfo(it) }
    }

    override fun getAll(): Flow<List<CounselorInfo>> {
        return counselorInfoDao.getAll().map {
            it.map {
                CounselorInfo(it)
            }
        }
    }

    override fun getList(query: String): Flow<List<CounselorInfo>> {
        return counselorInfoDao.getList(query).map {
            it.map {
                CounselorInfo(it)
            }
        }
    }

    override suspend fun setItem(info: CounselorInfo) {
        counselorInfoApiService.setItem(info.toRemoteModel())
    }

    override suspend fun refresh() {
        val remote = counselorInfoApiService.getAll().map {
            CounselorInfo(it)
        }
        counselorInfoDao.insert(
            remote.map {
                it.toLocalModel()
            },
        )
    }
}
