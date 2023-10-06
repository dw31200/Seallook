package com.seallook.androidx.data.remote.model

import com.google.gson.Gson

data class CounselingTypeListResponse(
    val counselingList: String,
) {
    companion object {
        operator fun invoke(list: List<CounselingTypeResponse>): CounselingTypeListResponse {
            return CounselingTypeListResponse(
                counselingList = Gson().toJson(list).toString(),
            )
        }
    }
}
