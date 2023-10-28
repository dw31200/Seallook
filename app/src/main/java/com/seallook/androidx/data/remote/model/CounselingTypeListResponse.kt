package com.seallook.androidx.data.remote.model

import com.google.gson.Gson
import com.seallook.androidx.base.RemoteModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CounselingTypeListResponse(
    val counselingList: String,
) : RemoteModel {
    companion object {
        operator fun invoke(list: List<CounselingTypeResponse>): CounselingTypeListResponse {
            return CounselingTypeListResponse(
                counselingList = Gson().toJson(list).toString(),
            )
        }
    }
}
