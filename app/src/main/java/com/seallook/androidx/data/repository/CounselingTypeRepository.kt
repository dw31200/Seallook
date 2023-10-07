package com.seallook.androidx.data.repository

import com.seallook.androidx.data.model.CounselingType

interface CounselingTypeRepository {
    suspend fun setCounselingType(counselingType: CounselingType)

    suspend fun deleteCounselingType(counselingTypeId: Int)

    suspend fun getCounselingType(): List<CounselingType>
}
