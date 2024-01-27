package com.seallook.androidx.data.repository.counselor.office

import com.seallook.androidx.data.local.OfficeInfoDao
import com.seallook.androidx.data.model.OfficeInfo
import com.seallook.androidx.data.remote.counselor.office.OfficeInfoApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OfficeInfoRepositoryImpl @Inject constructor(
    private val officeInfoApiService: OfficeInfoApiService,
    private val officeInfoDao: OfficeInfoDao,
) : OfficeInfoRepository {
    override suspend fun setItem(info: OfficeInfo) {
        officeInfoDao.insert(info.toLocalModel())
    }

    override suspend fun getAll(): List<OfficeInfo> {
        return officeInfoDao.getAll().map { OfficeInfo(it) }
    }

    override fun getItem(id: String): Flow<OfficeInfo?> {
        return flow {
            val local = officeInfoDao.getItem(id)?.let {
                OfficeInfo(it)
            }
            if (local != null) {
                emit(local)
            } else {
                val remote = officeInfoApiService.getItem(id)?.let {
                    OfficeInfo(it)
                }
                remote?.let {
                    emit(it)
                    officeInfoDao.insert(it.toLocalModel())
                }
            }
        }
    }

    override suspend fun updateItem(info: OfficeInfo) {
        officeInfoApiService.updateItem(info.toRemoteModel())
    }
}
