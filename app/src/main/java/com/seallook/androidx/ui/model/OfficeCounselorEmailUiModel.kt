package com.seallook.androidx.ui.model

import com.seallook.androidx.domain.model.OfficeCounselorEmailModel

data class OfficeCounselorEmailUiModel(
    val id: String,
    val officeId: String,
    val counselorEmail: String,
) {
    fun toDomainModel(): OfficeCounselorEmailModel {
        return OfficeCounselorEmailModel(
            id = id,
            officeId = officeId,
            counselorEmail = counselorEmail,
        )
    }

    companion object {
        operator fun invoke(officeCounselorEmail: OfficeCounselorEmailModel): OfficeCounselorEmailUiModel {
            return OfficeCounselorEmailUiModel(
                id = officeCounselorEmail.id,
                officeId = officeCounselorEmail.officeId,
                counselorEmail = officeCounselorEmail.counselorEmail,
            )
        }
    }
}
