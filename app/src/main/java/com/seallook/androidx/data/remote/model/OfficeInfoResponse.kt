package com.seallook.androidx.data.remote.model

import com.google.firebase.firestore.DocumentSnapshot

data class OfficeInfoResponse(
    val id: String,
    val place_name: String,
    val phone: String,
    val address_name: String,
    val road_address_name: String,
    val x: String,
    val y: String,
    val place_url: String,
    val distance: String,
) {
    constructor() : this("", "", "", "", "", "", "", "", "")

    companion object {
        operator fun invoke(snapshot: DocumentSnapshot): OfficeInfoResponse? {
            return snapshot.toObject(OfficeInfoResponse::class.java)
        }

        operator fun invoke(kakaoSearchResponse: KakaoSearchResponse): OfficeInfoResponse {
            return OfficeInfoResponse(
                id = kakaoSearchResponse.id,
                place_name = kakaoSearchResponse.place_name,
                phone = kakaoSearchResponse.phone,
                address_name = kakaoSearchResponse.address_name,
                road_address_name = kakaoSearchResponse.road_address_name,
                x = kakaoSearchResponse.x,
                y = kakaoSearchResponse.y,
                place_url = kakaoSearchResponse.place_url,
                distance = kakaoSearchResponse.distance,
            )
        }
    }
}
