package com.seallook.androidx.data.repository

import com.seallook.androidx.data.local.CounselingTypeDao
import com.seallook.androidx.data.model.CounselingType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CounselingTypeRepositoryImpl @Inject constructor(
    private val counselingTypeDao: CounselingTypeDao,
) : CounselingTypeRepository {
    override suspend fun setCounselingType(counselingType: CounselingType) {
        counselingTypeDao.insert(counselingType.toEntity())
    }

    override suspend fun deleteCounselingType(counselingTypeId: Int) {
        counselingTypeDao.deleteItem(counselingTypeId)
    }

    override fun getCounselingType(): Flow<List<CounselingType>> {
        return counselingTypeDao.getAll().map {
            it.map { CounselingType(it) }
        }
    }
}
