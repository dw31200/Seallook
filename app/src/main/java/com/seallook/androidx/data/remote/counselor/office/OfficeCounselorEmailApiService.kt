package com.seallook.androidx.data.remote.counselor.office

import com.seallook.androidx.data.remote.model.OfficeCounselorEmailResponse

interface OfficeCounselorEmailApiService {
    suspend fun getList(officeId: String): List<OfficeCounselorEmailResponse>

    suspend fun getList(officeId: String, email: String): List<OfficeCounselorEmailResponse>

    suspend fun set(officeCounselorEmailResponse: OfficeCounselorEmailResponse)
}
