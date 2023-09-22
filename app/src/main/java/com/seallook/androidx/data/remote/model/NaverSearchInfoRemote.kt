package com.seallook.androidx.data.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NaverSearchInfoRemote(
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<NaverSearchApiResponse>,
)
