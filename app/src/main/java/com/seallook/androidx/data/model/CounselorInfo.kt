package com.seallook.androidx.data.model

import com.seallook.androidx.data.remote.model.CounselorInfoResponse

data class CounselorInfo(
    val counselorId: String,
    val name: String,
    val createdAt: Long,
    val description: String,
    val counselingType: CounselingType,
    val imageUrl: String,
) {
    companion object {
        operator fun invoke(counselorInfoResponse: CounselorInfoResponse): CounselorInfo {
            return CounselorInfo(
                counselorId = counselorInfoResponse.counselorId,
                name = counselorInfoResponse.name,
                createdAt = counselorInfoResponse.createdAt,
                description = counselorInfoResponse.description,
                counselingType = CounselingType(counselorInfoResponse.counselingType),
                imageUrl = counselorInfoResponse.imageUrl,
            )
        }
    }
}
