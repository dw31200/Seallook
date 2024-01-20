package com.seallook.androidx.data.repository.counselor.office

import com.seallook.androidx.data.model.CounselorOfficeId

interface CounselorOfficeIdRepository {
    suspend fun setItem(counselorOfficeId: CounselorOfficeId)

    suspend fun getItem(email: String): CounselorOfficeId?

    suspend fun get(email: String): CounselorOfficeId?

    suspend fun update(counselorOfficeId: CounselorOfficeId)
}
