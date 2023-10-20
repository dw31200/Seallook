package com.seallook.androidx.data.repository.counselor.office

import com.seallook.androidx.data.local.OfficeInfoDao
import com.seallook.androidx.data.model.OfficeInfo
import com.seallook.androidx.data.remote.auth.FirebaseAuthApiService
import com.seallook.androidx.data.remote.counselor.office.OfficeInfoApiService
import javax.inject.Inject

class OfficeInfoRepositoryImpl @Inject constructor(
    private val firebaseAuthApiService: FirebaseAuthApiService,
    private val officeInfoApiService: OfficeInfoApiService,
    private val officeInfoDao: OfficeInfoDao,
) : OfficeInfoRepository {
    override suspend fun getList(type: String, query: String): List<OfficeInfo> {
        return officeInfoApiService.getList(type, query).map {
            OfficeInfo(it)
        }
    }

    override suspend fun setItem(info: OfficeInfo) {
        officeInfoDao.insert(info.toLocalModel())
    }

    override suspend fun getItem(id: Int): OfficeInfo? {
        return officeInfoDao.getItem(id)?.let { OfficeInfo(it) }
    }

    override suspend fun updateItem(info: OfficeInfo) {
        officeInfoApiService.updateItem(firebaseAuthApiService.getCurrentUser(), info.toRemoteModel())
    }
}
