package com.seallook.androidx.data.remote.counselor.office

import com.seallook.androidx.data.remote.model.CounselorOfficeIdResponse

interface CounselorOfficeIdApiService {
    suspend fun get(email: String): CounselorOfficeIdResponse?

    suspend fun update(counselorOfficeIdResponse: CounselorOfficeIdResponse)
}
