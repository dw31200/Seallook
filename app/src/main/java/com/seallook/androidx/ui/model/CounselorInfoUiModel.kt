package com.seallook.androidx.ui.model

import com.seallook.androidx.domain.model.CounselorInfoModel

data class CounselorInfoUiModel(
    val name: String,
    val description: String,
    val imageUrl: String,
) {
    fun toDomainModel(): CounselorInfoModel {
        return CounselorInfoModel(
            name = name,
            description = description,
            imageUrl = imageUrl,
        )
    }

    companion object {
        operator fun invoke(counselorInfoModel: CounselorInfoModel): CounselorInfoUiModel {
            return CounselorInfoUiModel(
                name = counselorInfoModel.name,
                description = counselorInfoModel.description,
                imageUrl = counselorInfoModel.imageUrl,
            )
        }
    }
}
