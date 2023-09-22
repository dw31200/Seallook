package com.seallook.androidx.data.repository

import com.seallook.androidx.data.local.CounselingTypeDao
import javax.inject.Inject

class DeleteCounselingTypeRepositoryImpl @Inject constructor(
    private val counselingTypeDao: CounselingTypeDao,
) : DeleteCounselingTypeRepository {
    override suspend fun deleteCounselingType(counselingTypeId: Int) {
        counselingTypeDao.deleteItem(counselingTypeId)
    }
}
