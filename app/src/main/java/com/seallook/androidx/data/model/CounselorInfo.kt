package com.seallook.androidx.data.model

import com.seallook.androidx.data.local.model.CounselorInfoEntity
import com.seallook.androidx.data.remote.model.CounselorInfoResponse

data class CounselorInfo(
    val email: String,
    val name: String,
    val description: String,
    val thumbnail: String,
) {
    fun toLocalModel(): CounselorInfoEntity {
        return CounselorInfoEntity(
            email = email,
            name = name,
            description = description,
            thumbnail = thumbnail,
        )
    }

    fun toRemoteModel(): CounselorInfoResponse {
        return CounselorInfoResponse(
            email = email,
            name = name,
            description = description,
            thumbnail = thumbnail,
        )
    }

    companion object {
        operator fun invoke(counselorInfoEntity: CounselorInfoEntity): CounselorInfo {
            return CounselorInfo(
                email = counselorInfoEntity.email,
                name = counselorInfoEntity.name,
                description = counselorInfoEntity.description,
                thumbnail = counselorInfoEntity.thumbnail,
            )
        }

        operator fun invoke(counselorInfoResponse: CounselorInfoResponse): CounselorInfo {
            return CounselorInfo(
                email = counselorInfoResponse.email,
                name = counselorInfoResponse.name,
                description = counselorInfoResponse.description,
                thumbnail = counselorInfoResponse.thumbnail,
            )
        }
    }
}
