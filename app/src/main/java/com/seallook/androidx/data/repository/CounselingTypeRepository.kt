package com.seallook.androidx.data.repository

import com.seallook.androidx.data.model.CounselingType
import kotlinx.coroutines.flow.Flow

interface CounselingTypeRepository {
    suspend fun setCounselingType(counselingType: CounselingType)

    suspend fun deleteCounselingType(counselingTypeId: Int)

    fun getCounselingType(): Flow<List<CounselingType>>
}
