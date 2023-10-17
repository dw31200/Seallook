package com.seallook.androidx.data.remote.model

import com.google.firebase.firestore.DocumentSnapshot
import com.squareup.moshi.JsonClass

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
        operator fun invoke(snapshot: DocumentSnapshot): OfficeInfoResponse {
            return OfficeInfoResponse(
                title = snapshot.getString("title") ?: "",
                link = snapshot.getString("link") ?: "",
                category = snapshot.getString("category") ?: "",
                description = snapshot.getString("description") ?: "",
                telephone = snapshot.getString("telephone") ?: "",
                address = snapshot.getString("address") ?: "",
                roadAddress = snapshot.getString("roadAddress") ?: "",
                mapx = snapshot.getLong("mapx")?.toInt() ?: 0,
                mapy = snapshot.getLong("mapy")?.toInt() ?: 0,
            )
        }
    }
}
