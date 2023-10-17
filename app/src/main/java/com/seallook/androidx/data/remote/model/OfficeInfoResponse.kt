package com.seallook.androidx.data.remote.model

import com.google.firebase.firestore.DocumentSnapshot
import com.squareup.moshi.JsonClass

// DataModel Index
// Index : 기준점
@JsonClass(generateAdapter = true)
data class OfficeInfoResponse(
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
    companion object {
        operator fun invoke(snapshot: DocumentSnapshot): OfficeInfoResponse? {
            snapshot.toObject(OfficeInfoResponse::class.java)?.let {
                return it
            } ?: run {
                return null
            }
        }
    }
}
