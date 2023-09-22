package com.seallook.androidx.data.repository

import com.seallook.androidx.data.model.CounselingType

interface SetCounselingTypeRepository {
    suspend fun setCounselingType(counselingType: CounselingType)
}
