package com.seallook.androidx.domain.model

import com.seallook.androidx.data.model.OfficeCounselorEmail

data class OfficeCounselorEmailModel(
    val id: String,
    val officeId: String,
    val counselorEmail: String,
) {
    fun toDataModel(): OfficeCounselorEmail {
        return OfficeCounselorEmail(
            id = id,
            officeId = officeId,
            counselorEmail = counselorEmail,
        )
    }

    companion object {
        operator fun invoke(officeCounselorEmail: OfficeCounselorEmail): OfficeCounselorEmailModel {
            return OfficeCounselorEmailModel(
                id = officeCounselorEmail.id,
                officeId = officeCounselorEmail.officeId,
                counselorEmail = officeCounselorEmail.counselorEmail,
            )
        }
    }
}
