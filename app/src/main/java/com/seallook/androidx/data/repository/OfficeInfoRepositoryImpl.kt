package com.seallook.androidx.data.repository

import com.seallook.androidx.data.local.OfficeInfoDao
import com.seallook.androidx.data.model.OfficeInfo
import javax.inject.Inject

class OfficeInfoRepositoryImpl @Inject constructor(
    private val officeInfoDao: OfficeInfoDao,
) : OfficeInfoRepository {
    override suspend fun getOfficeInfo(id: Int): OfficeInfo? {
        return officeInfoDao.getInfo(id)?.let { OfficeInfo(it) }
    }

    override suspend fun setOfficeInfo(id: Int, info: OfficeInfo) {
        officeInfoDao.setInfo(info.toEntity(id))
    }
}
