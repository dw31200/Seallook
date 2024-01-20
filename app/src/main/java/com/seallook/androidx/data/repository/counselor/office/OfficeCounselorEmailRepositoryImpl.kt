package com.seallook.androidx.data.repository.counselor.office

import com.seallook.androidx.data.model.OfficeCounselorEmail
import com.seallook.androidx.data.remote.counselor.office.OfficeCounselorEmailApiService
import javax.inject.Inject

class OfficeCounselorEmailRepositoryImpl @Inject constructor(
    private val officeCounselorEmailApiService: OfficeCounselorEmailApiService,
) : OfficeCounselorEmailRepository {
    override suspend fun getList(officeId: String): List<OfficeCounselorEmail> {
        return officeCounselorEmailApiService.getList(officeId).map {
            OfficeCounselorEmail(it)
        }
    }

    override suspend fun getList(officeId: String, counselorEmail: String): List<OfficeCounselorEmail> {
        return officeCounselorEmailApiService.getList(officeId, counselorEmail).map {
            OfficeCounselorEmail(it)
        }
    }

    override suspend fun set(officeCounselorEmail: OfficeCounselorEmail) {
        officeCounselorEmailApiService.set(officeCounselorEmail.toRemoteModel())
    }
}
