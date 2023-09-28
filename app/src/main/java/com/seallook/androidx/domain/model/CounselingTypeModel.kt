package com.seallook.androidx.domain.model

import com.seallook.androidx.data.model.CounselingType

data class CounselingTypeModel(
    val id: Int,
    val uid: String,
    val title: String,
    val count: Int,
    val time: Int,
    val pay: Int,
) {
    fun toType(): CounselingType {
        return CounselingType(
            id = id,
            uid = uid,
            title = title,
            count = count,
            time = time,
            pay = pay,
        )
    }
    companion object {
        operator fun invoke(counselingType: CounselingType): CounselingTypeModel {
            return CounselingTypeModel(
                id = counselingType.id,
                uid = counselingType.uid,
                title = counselingType.title,
                count = counselingType.count,
                time = counselingType.time,
                pay = counselingType.pay,
            )
        }
    }
}
