package com.seallook.androidx.data.model

import com.seallook.androidx.data.remote.model.CounselorInfoResponse

data class CounselorInfo(
    val name: String,
    val description: String,
    val imageUrl: String,
) {
    fun toResponse(): CounselorInfoResponse {
        return CounselorInfoResponse(
            name = name,
            description = description,
            imageUrl = imageUrl,
        )
    }
    companion object {
        operator fun invoke(counselorInfoResponse: CounselorInfoResponse): CounselorInfo {
            return CounselorInfo(
                name = counselorInfoResponse.name,
                description = counselorInfoResponse.description,
                imageUrl = counselorInfoResponse.imageUrl,
            )
        }
    }
}
