package com.seallook.androidx.data.repository.counselor.counselingtype

import com.seallook.androidx.data.local.CounselingTypeDao
import com.seallook.androidx.data.model.CounselingType
import com.seallook.androidx.data.remote.counselor.counselingtype.CounselingTypeApiService
import com.seallook.androidx.data.remote.model.CounselingTypeListResponse
import javax.inject.Inject

class CounselingTypeRepositoryImpl @Inject constructor(
    private val counselingTypeDao: CounselingTypeDao,
    private val counselingTypeApiService: CounselingTypeApiService,
) : CounselingTypeRepository {
    override suspend fun insert(counselingType: CounselingType) {
        counselingTypeDao.insert(counselingType.toLocalModel())
    }

    override suspend fun insert(counselingTypeList: List<CounselingType>) {
        counselingTypeDao.insert(counselingTypeList.map { it.toLocalModel() })
    }

    override suspend fun deleteItem(counselingTypeId: Int) {
        counselingTypeDao.deleteItem(counselingTypeId)
    }

    override suspend fun getAll(): List<CounselingType> {
        return counselingTypeDao.getAll().map {
            CounselingType(it)
        }
    }

    override suspend fun getAll(email: String): List<CounselingType> {
        return counselingTypeApiService.getAll(email).map {
            CounselingType(it)
        }
    }

    override suspend fun updateList(uid: String, type: List<CounselingType>) {
        counselingTypeApiService.updateList(
            uid,
            CounselingTypeListResponse(type.map { it.toRemoteModel() }),
        )
    }
}
