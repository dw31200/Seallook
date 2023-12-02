package com.seallook.androidx.data.repository.counselor.counselingtype

import com.seallook.androidx.data.local.CounselingTypeDao
import com.seallook.androidx.data.model.CounselingType
import com.seallook.androidx.data.remote.counselor.counselingtype.CounselingTypeApiService
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

    override suspend fun deleteItem(counselingTypeId: String) {
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

    override suspend fun getList(email: String): List<CounselingType> {
        return counselingTypeDao.getList(email).map {
            CounselingType(it)
        }
    }

    override suspend fun getItem(email: String, id: String): CounselingType? {
        return counselingTypeDao.getItem(email, id)?.let {
            CounselingType(it)
        }
    }

    override suspend fun updateList(email: String, type: List<CounselingType>) {
        counselingTypeApiService.updateList(
            email,
            type.map {
                it.toRemoteModel()
            },
        )
    }
}
