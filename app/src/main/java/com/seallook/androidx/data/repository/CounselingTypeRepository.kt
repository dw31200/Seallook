package com.seallook.androidx.data.repository

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.model.CounselingType

interface CounselingTypeRepository {
    suspend fun setCounselingType(counselingType: CounselingType)

    suspend fun deleteCounselingType(counselingTypeId: Int)

    suspend fun getCounselingType(): List<CounselingType>

    suspend fun initCounselingType(user: FirebaseUser?): List<CounselingType>?

    suspend fun updateCounselingType(user: FirebaseUser?, type: List<CounselingType>)
}
