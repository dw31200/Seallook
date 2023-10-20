package com.seallook.androidx.ui.model

import com.seallook.androidx.domain.model.CounselorInfoModel

data class CounselorInfoUiModel(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
) {
    fun toDomainModel(): CounselorInfoModel {
        return CounselorInfoModel(
            id = id,
            name = name,
            description = description,
            imageUrl = imageUrl,
        )
    }

    companion object {
        operator fun invoke(counselorInfoModel: CounselorInfoModel): CounselorInfoUiModel {
            return CounselorInfoUiModel(
                id = counselorInfoModel.id,
                name = counselorInfoModel.name,
                description = counselorInfoModel.description,
                imageUrl = counselorInfoModel.imageUrl,
            )
        }
    }
}
