package com.seallook.androidx.data.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class KakaoSearchListResponse(
    val documents: List<KakaoSearchResponse>,
)
