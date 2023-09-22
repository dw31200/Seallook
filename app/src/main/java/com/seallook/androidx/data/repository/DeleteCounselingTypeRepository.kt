package com.seallook.androidx.data.repository

interface DeleteCounselingTypeRepository {
    suspend fun deleteCounselingType(counselingTypeId: Int)
}
