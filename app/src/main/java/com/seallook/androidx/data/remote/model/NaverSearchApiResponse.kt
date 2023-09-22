package com.seallook.androidx.data.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NaverSearchApiResponse(
    val title: String,
    val link: String,
    val category: String,
    val description: String,
    val telephone: String,
    val address: String,
    val roadAddress: String,
    val mapx: Int,
    val mapy: Int,
)
