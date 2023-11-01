package com.seallook.androidx.data.model

import com.seallook.androidx.data.remote.model.CounselorListResponse

data class CounselorList(
    val description: String,
    val name: String,
    val thumbnail: String,
) {
    fun toRemoteModel(): CounselorListResponse {
        return CounselorListResponse(
            description = description,
            name = name,
            thumbnail = thumbnail,
        )
    }

    companion object {
        operator fun invoke(counselorListResponse: CounselorListResponse): CounselorList {
            return CounselorList(
                description = counselorListResponse.description,
                name = counselorListResponse.name,
                thumbnail = counselorListResponse.thumbnail,
            )
        }
    }
}
