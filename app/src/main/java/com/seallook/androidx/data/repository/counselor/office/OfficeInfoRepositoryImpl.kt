package com.seallook.androidx.data.repository.counselor.office

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.local.OfficeInfoDao
import com.seallook.androidx.data.model.OfficeInfo
import com.seallook.androidx.data.remote.counselor.office.OfficeInfoApiService
import javax.inject.Inject

class OfficeInfoRepositoryImpl @Inject constructor(
    private val officeInfoApiService: OfficeInfoApiService,
    private val officeInfoDao: OfficeInfoDao,
) : OfficeInfoRepository {
    override suspend fun getList(type: String, query: String): List<OfficeInfo> {
        return officeInfoApiService.getList(type, query).map {
            OfficeInfo(it)
        }
    }

    override suspend fun setItem(id: Int, info: OfficeInfo) {
        officeInfoDao.insertItem(info.toEntity(id))
    }

    override suspend fun getItem(id: Int): OfficeInfo? {
        return officeInfoDao.getItem(id)?.let { OfficeInfo(it) }
    }

    override suspend fun updateItem(user: FirebaseUser, info: OfficeInfo) {
        officeInfoApiService.updateItem(user, info.toResponse())
    }
}
