package com.seallook.androidx.data.repository

import com.seallook.androidx.data.local.CounselingTypeDao
import com.seallook.androidx.data.remote.model.CounselingType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCounselingTypeRepositoryImpl @Inject constructor(
    private val counselingTypeDao: CounselingTypeDao,
) : GetCounselingTypeRepository {
    override fun getCounselingType(): Flow<List<CounselingType>> {
        return counselingTypeDao.getAll().map {
            it.map { CounselingType(it) }
        }
    }
}
