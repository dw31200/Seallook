package com.seallook.androidx.data.repository.counselor.counselingtype

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.model.CounselingType

interface CounselingTypeRepository {
    suspend fun insertItem(counselingType: CounselingType)

    suspend fun insertList(counselingTypeList: List<CounselingType>)

    suspend fun deleteItem(counselingTypeId: Int)

    suspend fun getItemList(): List<CounselingType>

    suspend fun initList(user: FirebaseUser?): List<CounselingType>

    suspend fun updateList(user: FirebaseUser?, type: List<CounselingType>)
}
