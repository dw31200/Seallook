package com.seallook.androidx.data.remote.model

import com.seallook.androidx.data.local.model.CounselingTypeEntity

data class CounselingType(
    val id: Int,
    val title: String,
    val count: Int,
    val time: Int,
    val pay: Int,
) {
    companion object {
        operator fun invoke(counselingTypeEntity: CounselingTypeEntity): CounselingType {
            return CounselingType(
                id = counselingTypeEntity.id,
                title = counselingTypeEntity.title,
                count = counselingTypeEntity.count,
                time = counselingTypeEntity.time,
                pay = counselingTypeEntity.pay,
            )
        }
    }
}
