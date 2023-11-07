package com.seallook.androidx.data.repository.counselor.counselingtype

import com.seallook.androidx.data.model.CounselingType

interface CounselingTypeRepository {
    suspend fun insert(counselingType: CounselingType)

    suspend fun insert(counselingTypeList: List<CounselingType>)

    suspend fun deleteItem(counselingTypeId: Int)

    suspend fun getAll(): List<CounselingType>

    suspend fun getAll(email: String): List<CounselingType>

    suspend fun getList(email: String): List<CounselingType>

    suspend fun getItem(email: String, id: Int): CounselingType?

    suspend fun updateList(email: String, type: List<CounselingType>)
}
