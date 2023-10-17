package com.seallook.androidx.ui.model

import com.seallook.androidx.domain.model.CounselingTypeModel

data class CounselingTypeUiModel(
    val id: Int,
    val title: String,
    val count: Int,
    val time: Int,
    val pay: Int,
) {
    fun toDomainModel(): CounselingTypeModel {
        return CounselingTypeModel(
            id = id,
            title = title,
            count = count,
            time = time,
            pay = pay,
        )
    }

    companion object {
        operator fun invoke(counselingTypeModel: CounselingTypeModel): CounselingTypeUiModel {
            return CounselingTypeUiModel(
                id = counselingTypeModel.id,
                title = counselingTypeModel.title,
                count = counselingTypeModel.count,
                time = counselingTypeModel.time,
                pay = counselingTypeModel.pay,
            )
        }
    }
}
