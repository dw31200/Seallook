package com.seallook.androidx.data.model

import com.seallook.androidx.data.local.model.CounselingTypeEntity
import com.seallook.androidx.data.remote.model.CounselingTypeResponse

data class CounselingType(
    val id: Int,
    val title: String,
    val count: Int,
    val time: Int,
    val pay: Int,
) {
    fun toEntity(): CounselingTypeEntity {
        return CounselingTypeEntity(
            id = id,
            title = title,
            count = count,
            time = time,
            pay = pay,
        )
    }

    fun toResponse(): CounselingTypeResponse {
        return CounselingTypeResponse(
            id = id,
            title = title,
            count = count,
            time = time,
            pay = pay,
        )
    }

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

        operator fun invoke(counselingTypeResponse: CounselingTypeResponse): CounselingType {
            return CounselingType(
                id = counselingTypeResponse.id,
                title = counselingTypeResponse.title,
                count = counselingTypeResponse.count,
                time = counselingTypeResponse.time,
                pay = counselingTypeResponse.pay,
            )
        }
    }
}
