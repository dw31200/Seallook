package com.seallook.androidx.data.model

import com.seallook.androidx.data.remote.model.CounselorInfoResponse

data class CounselorInfo(
    val counselorId: String,
    val name: String,
    val createdAt: Long,
    val description: String,
    val imageUrl: String,
) {
    fun toResponse(): CounselorInfoResponse {
        return CounselorInfoResponse(
            counselorId = counselorId,
            name = name,
            createdAt = createdAt,
            description = description,
            imageUrl = imageUrl,
        )
    }
    companion object {
        operator fun invoke(counselorInfoResponse: CounselorInfoResponse): CounselorInfo {
            return CounselorInfo(
                counselorId = counselorInfoResponse.counselorId,
                name = counselorInfoResponse.name,
                createdAt = counselorInfoResponse.createdAt,
                description = counselorInfoResponse.description,
                imageUrl = counselorInfoResponse.imageUrl,
            )
        }
    }
}
