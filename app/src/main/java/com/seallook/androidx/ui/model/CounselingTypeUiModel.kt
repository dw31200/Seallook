package com.seallook.androidx.ui.model

import com.seallook.androidx.domain.model.CounselingTypeModel

data class CounselingTypeUiModel(
    val id: Int,
    val title: String,
    val clientCount: Int,
    val time: Int,
    val price: Int,
) {
    fun toDomainModel(): CounselingTypeModel {
        return CounselingTypeModel(
            id = id,
            title = title,
            clientCount = clientCount,
            time = time,
            price = price,
        )
    }

    companion object {
        operator fun invoke(counselingTypeModel: CounselingTypeModel): CounselingTypeUiModel {
            return CounselingTypeUiModel(
                id = counselingTypeModel.id,
                title = counselingTypeModel.title,
                clientCount = counselingTypeModel.clientCount,
                time = counselingTypeModel.time,
                price = counselingTypeModel.price,
            )
        }
    }
}
