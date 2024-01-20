package com.seallook.androidx.data.repository.counselor.office

import com.seallook.androidx.data.model.OfficeCounselorEmail

interface OfficeCounselorEmailRepository {
    suspend fun getList(officeId: String): List<OfficeCounselorEmail>

    suspend fun getList(officeId: String, counselorEmail: String): List<OfficeCounselorEmail>

    suspend fun set(officeCounselorEmail: OfficeCounselorEmail)
}
