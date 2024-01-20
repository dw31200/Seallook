package com.seallook.androidx.data.model

import com.seallook.androidx.data.local.model.OfficeCounselorEmailEntity
import com.seallook.androidx.data.remote.model.OfficeCounselorEmailResponse

data class OfficeCounselorEmail(
    val id: String,
    val officeId: String,
    val counselorEmail: String,
) {
    fun toLocalModel(): OfficeCounselorEmailEntity {
        return OfficeCounselorEmailEntity(
            id = id,
            officeId = officeId,
            counselorEmail = counselorEmail,
        )
    }

    fun toRemoteModel(): OfficeCounselorEmailResponse {
        return OfficeCounselorEmailResponse(
            id = id,
            officeId = officeId,
            counselorEmail = counselorEmail,
        )
    }

    companion object {
        operator fun invoke(officeCounselorEmailEntity: OfficeCounselorEmailEntity): OfficeCounselorEmail {
            return OfficeCounselorEmail(
                id = officeCounselorEmailEntity.id,
                officeId = officeCounselorEmailEntity.officeId,
                counselorEmail = officeCounselorEmailEntity.counselorEmail,
            )
        }

        operator fun invoke(officeCounselorEmailResponse: OfficeCounselorEmailResponse): OfficeCounselorEmail {
            return OfficeCounselorEmail(
                id = officeCounselorEmailResponse.id,
                officeId = officeCounselorEmailResponse.officeId,
                counselorEmail = officeCounselorEmailResponse.counselorEmail,
            )
        }
    }
}
