package com.seallook.androidx.data.model

import com.seallook.androidx.data.local.model.CounselorInfoEntity
import com.seallook.androidx.data.remote.model.CounselorInfoResponse

data class CounselorInfo(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
) {
    fun toLocalModel(): CounselorInfoEntity {
        return CounselorInfoEntity(
            id = id,
            name = name,
            description = description,
            imageUrl = imageUrl,
        )
    }

    fun toRemoteModel(): CounselorInfoResponse {
        return CounselorInfoResponse(
            id = id,
            name = name,
            description = description,
            imageUrl = imageUrl,
        )
    }

    companion object {
        operator fun invoke(counselorInfoEntity: CounselorInfoEntity): CounselorInfo {
            return CounselorInfo(
                id = counselorInfoEntity.id,
                name = counselorInfoEntity.name,
                description = counselorInfoEntity.description,
                imageUrl = counselorInfoEntity.imageUrl,
            )
        }

        operator fun invoke(counselorInfoResponse: CounselorInfoResponse): CounselorInfo {
            return CounselorInfo(
                id = counselorInfoResponse.id,
                name = counselorInfoResponse.name,
                description = counselorInfoResponse.description,
                imageUrl = counselorInfoResponse.imageUrl,
            )
        }
    }
}
