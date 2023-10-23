package com.seallook.androidx.domain.model

import com.seallook.androidx.data.model.CounselingType

data class CounselingTypeModel(
    val id: Int,
    val title: String,
    val clientCount: Int,
    val time: Int,
    val price: Int,
) {
    fun toDataModel(): CounselingType {
        return CounselingType(
            id = id,
            title = title,
            clientCount = clientCount,
            time = time,
            price = price,
        )
    }
    companion object {
        operator fun invoke(counselingType: CounselingType): CounselingTypeModel {
            return CounselingTypeModel(
                id = counselingType.id,
                title = counselingType.title,
                clientCount = counselingType.clientCount,
                time = counselingType.time,
                price = counselingType.price,
            )
        }
    }
}
