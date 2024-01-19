package com.seallook.androidx.ui.model

import com.seallook.androidx.domain.model.CounselorOfficeIdModel

data class CounselorOfficeIdUiModel(
    val email: String,
    val officeId: String,
) {
    fun toDomainModel(): CounselorOfficeIdModel {
        return CounselorOfficeIdModel(
            email = email,
            officeId = officeId,
        )
    }

    companion object {
        operator fun invoke(counselorOfficeIdModel: CounselorOfficeIdModel): CounselorOfficeIdUiModel {
            return CounselorOfficeIdUiModel(
                email = counselorOfficeIdModel.email,
                officeId = counselorOfficeIdModel.officeId,
            )
        }
    }
}
