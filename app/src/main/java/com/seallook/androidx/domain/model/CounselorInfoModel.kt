package com.seallook.androidx.domain.model

import com.seallook.androidx.data.model.CounselorInfo

data class CounselorInfoModel(
    val counselorId: String,
    val name: String,
    val createdAt: Long,
    val description: String,
    val imageUrl: String,
) {
    companion object {
        operator fun invoke(counselorInfo: CounselorInfo): CounselorInfoModel {
            return CounselorInfoModel(
                counselorId = counselorInfo.counselorId,
                name = counselorInfo.name,
                createdAt = counselorInfo.createdAt,
                description = counselorInfo.description,
                imageUrl = counselorInfo.imageUrl,
            )
        }
    }
}
