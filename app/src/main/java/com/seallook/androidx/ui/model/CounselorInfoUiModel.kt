package com.seallook.androidx.ui.model

import com.seallook.androidx.domain.model.CounselorInfoModel

data class CounselorInfoUiModel(
    val email: String,
    val name: String,
    val description: String,
    val thumbnail: String,
) {
    fun toDomainModel(): CounselorInfoModel {
        return CounselorInfoModel(
            email = email,
            name = name,
            description = description,
            thumbnail = thumbnail,
        )
    }

    companion object {
        operator fun invoke(counselorInfoModel: CounselorInfoModel): CounselorInfoUiModel {
            return CounselorInfoUiModel(
                email = counselorInfoModel.email,
                name = counselorInfoModel.name,
                description = counselorInfoModel.description,
                thumbnail = counselorInfoModel.thumbnail,
            )
        }
    }
}
