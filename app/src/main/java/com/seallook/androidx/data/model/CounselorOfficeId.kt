package com.seallook.androidx.data.model

import com.seallook.androidx.data.local.model.CounselorOfficeIdEntity
import com.seallook.androidx.data.remote.model.CounselorOfficeIdResponse

data class CounselorOfficeId(
    val email: String,
    val officeId: String,
) {
    fun toLocalModel(): CounselorOfficeIdEntity {
        return CounselorOfficeIdEntity(
            email = email,
            officeId = officeId,
        )
    }

    fun toRemoteModel(): CounselorOfficeIdResponse {
        return CounselorOfficeIdResponse(
            email = email,
            officeId = officeId,
        )
    }

    companion object {
        operator fun invoke(counselorOfficeIdEntity: CounselorOfficeIdEntity): CounselorOfficeId {
            return CounselorOfficeId(
                email = counselorOfficeIdEntity.email,
                officeId = counselorOfficeIdEntity.officeId,
            )
        }

        operator fun invoke(counselorOfficeIdResponse: CounselorOfficeIdResponse): CounselorOfficeId {
            return CounselorOfficeId(
                email = counselorOfficeIdResponse.email,
                officeId = counselorOfficeIdResponse.officeId,
            )
        }
    }
}
