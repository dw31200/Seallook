package com.seallook.androidx.data.remote.model

import com.google.firebase.firestore.DocumentSnapshot
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NaverSearchListResponse(
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<NaverSearchResponse>,
) {
    constructor() : this("", 0, 0, 0, emptyList())

    companion object {
        operator fun invoke(snapshot: DocumentSnapshot): NaverSearchListResponse? {
            return snapshot.toObject(NaverSearchListResponse::class.java)
        }
    }
}
