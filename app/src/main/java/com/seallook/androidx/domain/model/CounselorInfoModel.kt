package com.seallook.androidx.domain.model

import com.seallook.androidx.data.model.CounselorInfo

data class CounselorInfoModel(
    val name: String,
    val description: String,
    val imageUrl: String,
) {
    fun toInfo(): CounselorInfo {
        return CounselorInfo(
            name = name,
            description = description,
            imageUrl = imageUrl,
        )
    }

    companion object {
        operator fun invoke(counselorInfo: CounselorInfo): CounselorInfoModel {
            return CounselorInfoModel(
                name = counselorInfo.name,
                description = counselorInfo.description,
                imageUrl = counselorInfo.imageUrl,
            )
        }
    }
}
