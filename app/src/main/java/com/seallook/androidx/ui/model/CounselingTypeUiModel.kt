package com.seallook.androidx.ui.model

import com.seallook.androidx.domain.model.CounselingTypeModel

data class CounselingTypeUiModel(
    val id: String,
    val email: String,
    val title: String,
    val clientCount: Int,
    val time: Int,
    val price: Int,
) {
    fun toDomainModel(): CounselingTypeModel {
        return CounselingTypeModel(
            id = id,
            email = email,
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
                email = counselingTypeModel.email,
                title = counselingTypeModel.title,
                clientCount = counselingTypeModel.clientCount,
                time = counselingTypeModel.time,
                price = counselingTypeModel.price,
            )
        }
    }
}
