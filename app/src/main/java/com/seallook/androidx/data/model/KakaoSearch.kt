package com.seallook.androidx.data.model

import com.seallook.androidx.data.remote.model.KakaoSearchResponse

data class KakaoSearch(
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
    fun toRemoteModel(): KakaoSearchResponse {
        return KakaoSearchResponse(
            id = id,
            place_name = place_name,
            phone = phone,
            address_name = address_name,
            road_address_name = road_address_name,
            x = x,
            y = y,
            place_url = place_url,
            distance = distance,
        )
    }

    companion object {
        operator fun invoke(kakaoSearchResponse: KakaoSearchResponse): KakaoSearch {
            return KakaoSearch(
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
