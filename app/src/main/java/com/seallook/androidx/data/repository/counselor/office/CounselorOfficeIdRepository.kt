package com.seallook.androidx.data.repository.counselor.office

import com.seallook.androidx.data.model.CounselorOfficeId
import kotlinx.coroutines.flow.Flow

interface CounselorOfficeIdRepository {
    suspend fun setItem(counselorOfficeId: CounselorOfficeId)

    fun getItem(email: String): Flow<CounselorOfficeId?>

    suspend fun get(email: String): CounselorOfficeId?

    suspend fun update(counselorOfficeId: CounselorOfficeId)
}
