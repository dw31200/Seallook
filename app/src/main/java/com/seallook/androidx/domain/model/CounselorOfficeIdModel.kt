package com.seallook.androidx.domain.model

import com.seallook.androidx.data.model.CounselorOfficeId

data class CounselorOfficeIdModel(
    val email: String,
    val officeId: String,
) {
    fun toDataModel(): CounselorOfficeId {
        return CounselorOfficeId(
            email = email,
            officeId = officeId,
        )
    }

    companion object {
        operator fun invoke(counselorOfficeId: CounselorOfficeId): CounselorOfficeIdModel {
            return CounselorOfficeIdModel(
                email = counselorOfficeId.email,
                officeId = counselorOfficeId.officeId,
            )
        }
    }
}
