package com.seallook.androidx.data.repository

import com.seallook.androidx.data.local.OfficeInfoDao
import com.seallook.androidx.data.model.OfficeInfo
import com.seallook.androidx.data.remote.FirebaseFirestoreApiService
import com.seallook.androidx.data.remote.auth.FirebaseAuthApiService
import javax.inject.Inject

class OfficeInfoRepositoryImpl @Inject constructor(
    private val officeInfoDao: OfficeInfoDao,
    private val firebaseFirestoreApiService: FirebaseFirestoreApiService,
    private val firebaseAuthApiService: FirebaseAuthApiService,
) : OfficeInfoRepository {
    override suspend fun getOfficeInfo(id: Int): OfficeInfo? {
//        val remote = firebaseFirestoreApiService.getOfficeInfo(firebaseAuthApiService.getCurrentUser())
//        if (remote != null) {
//            officeInfoDao.setInfo(OfficeInfo(remote).toEntity(id))
//            return officeInfoDao.getInfo(id)?.let { OfficeInfo(it) }
//        } else {
//            return officeInfoDao.getInfo(id)?.let { OfficeInfo(it) }
//        }
        return officeInfoDao.getInfo(id)?.let { OfficeInfo(it) }
    }

    override suspend fun setOfficeInfo(id: Int, info: OfficeInfo) {
        officeInfoDao.setInfo(info.toEntity(id))
    }

    override suspend fun updateOfficeInfo(info: OfficeInfo) {
        firebaseFirestoreApiService.setOfficeInfo(firebaseAuthApiService.getCurrentUser(), info.toResponse())
    }
}
