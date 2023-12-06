package com.seallook.androidx.data.model

import com.seallook.androidx.data.local.model.CounselingTypeEntity
import com.seallook.androidx.data.remote.model.CounselingTypeResponse

data class CounselingType(
    val id: String,
    val email: String,
    val title: String,
    val clientCount: Int,
    val time: Int,
    val price: Int,
) {
    fun toLocalModel(): CounselingTypeEntity {
        return CounselingTypeEntity(
            id = id,
            email = email,
            title = title,
            clientCount = clientCount,
            time = time,
            price = price,
        )
    }

    fun toRemoteModel(): CounselingTypeResponse {
        return CounselingTypeResponse(
            id = id,
            email = email,
            title = title,
            clientCount = clientCount,
            time = time,
            price = price,
        )
    }

    companion object {
        operator fun invoke(counselingTypeEntity: CounselingTypeEntity): CounselingType {
            return CounselingType(
                id = counselingTypeEntity.id,
                email = counselingTypeEntity.email,
                title = counselingTypeEntity.title,
                clientCount = counselingTypeEntity.clientCount,
                time = counselingTypeEntity.time,
                price = counselingTypeEntity.price,
            )
        }

        operator fun invoke(counselingTypeResponse: CounselingTypeResponse): CounselingType {
            return CounselingType(
                id = counselingTypeResponse.id,
                email = counselingTypeResponse.email,
                title = counselingTypeResponse.title,
                clientCount = counselingTypeResponse.clientCount,
                time = counselingTypeResponse.time,
                price = counselingTypeResponse.price,
            )
        }
    }
}
