package com.seallook.androidx.data.repository.counselor.counselingtype

import com.seallook.androidx.data.model.CounselingType

interface CounselingTypeRepository {
    suspend fun insert(counselingType: CounselingType)

    suspend fun insert(counselingTypeList: List<CounselingType>)

    suspend fun deleteItem(counselingTypeId: Int)

    suspend fun getAll(): List<CounselingType>

    suspend fun getAll(uid: String): List<CounselingType>

    suspend fun updateList(uid: String, type: List<CounselingType>)
}
