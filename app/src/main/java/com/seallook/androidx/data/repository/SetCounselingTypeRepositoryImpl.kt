package com.seallook.androidx.data.repository

import com.seallook.androidx.data.local.CounselingTypeDao
import com.seallook.androidx.data.model.CounselingType
import javax.inject.Inject

class SetCounselingTypeRepositoryImpl @Inject constructor(
    private val counselingTypeDao: CounselingTypeDao,
) : SetCounselingTypeRepository {
    override suspend fun setCounselingType(counselingType: CounselingType) {
        counselingTypeDao.insert(counselingType.toEntity())
    }
}
