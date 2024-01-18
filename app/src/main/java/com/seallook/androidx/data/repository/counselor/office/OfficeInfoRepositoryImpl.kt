package com.seallook.androidx.data.repository.counselor.office

import com.seallook.androidx.data.local.OfficeInfoDao
import com.seallook.androidx.data.model.OfficeInfo
import com.seallook.androidx.data.remote.counselor.office.OfficeInfoApiService
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

    override suspend fun getItem(id: String): OfficeInfo? {
        return officeInfoDao.getItem(id)?.let { OfficeInfo(it) }
    }

    override suspend fun updateItem(info: OfficeInfo) {
        officeInfoApiService.updateItem(info.toRemoteModel())
    }
}
