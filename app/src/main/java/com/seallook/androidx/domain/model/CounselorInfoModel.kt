package com.seallook.androidx.domain.model

import com.seallook.androidx.data.model.CounselorInfo

data class CounselorInfoModel(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
) {
    fun toInfo(): CounselorInfo {
        return CounselorInfo(
            id = id,
            name = name,
            description = description,
            imageUrl = imageUrl,
        )
    }

    companion object {
        operator fun invoke(counselorInfo: CounselorInfo): CounselorInfoModel {
            return CounselorInfoModel(
                id = counselorInfo.id,
                name = counselorInfo.name,
                description = counselorInfo.description,
                imageUrl = counselorInfo.imageUrl,
            )
        }
    }
}
