package com.seallook.androidx.data.remote.model

import com.google.firebase.firestore.DocumentSnapshot
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NaverSearchResponse(
    val title: String,
    val link: String,
    val category: String,
    val description: String,
    val telephone: String,
    val address: String,
    val roadAddress: String,
    val mapx: Int,
    val mapy: Int,
) {
    constructor() : this("", "", "", "", "", "", "", 0, 0)

    companion object {
        operator fun invoke(snapshot: DocumentSnapshot): NaverSearchResponse? {
            return snapshot.toObject(NaverSearchResponse::class.java)
        }
    }
}
