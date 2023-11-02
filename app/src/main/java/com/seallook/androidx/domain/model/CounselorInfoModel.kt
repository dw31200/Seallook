package com.seallook.androidx.domain.model

import com.seallook.androidx.data.model.CounselorInfo

data class CounselorInfoModel(
    val email: String,
    val name: String,
    val description: String,
    val thumbnail: String,
) {
    fun toDataModel(): CounselorInfo {
        return CounselorInfo(
            email = email,
            name = name,
            description = description,
            thumbnail = thumbnail,
        )
    }

    companion object {
        operator fun invoke(counselorInfo: CounselorInfo): CounselorInfoModel {
            return CounselorInfoModel(
                email = counselorInfo.email,
                name = counselorInfo.name,
                description = counselorInfo.description,
                thumbnail = counselorInfo.thumbnail,
            )
        }
    }
}
