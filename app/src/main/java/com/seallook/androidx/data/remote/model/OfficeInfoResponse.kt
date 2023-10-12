package com.seallook.androidx.data.remote.model

import com.google.firebase.firestore.DocumentSnapshot

data class OfficeInfoResponse(
    val info: NaverSearchApiResponse,
) {
    companion object {
        operator fun invoke(snapshot: DocumentSnapshot): OfficeInfoResponse {
            return OfficeInfoResponse(
                info = NaverSearchApiResponse(
                    title = snapshot.getString("title") ?: "",
                    link = snapshot.getString("link") ?: "",
                    category = snapshot.getString("category") ?: "",
                    description = snapshot.getString("description") ?: "",
                    telephone = snapshot.getString("telephone") ?: "",
                    address = snapshot.getString("address") ?: "",
                    roadAddress = snapshot.getString("roadAddress") ?: "",
                    mapx = snapshot.getLong("mapx")?.toInt() ?: 0,
                    mapy = snapshot.getLong("mapy")?.toInt() ?: 0,
                ),
            )
        }
    }
}
