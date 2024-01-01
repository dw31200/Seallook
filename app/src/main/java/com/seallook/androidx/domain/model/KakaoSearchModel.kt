package com.seallook.androidx.domain.model

import com.seallook.androidx.data.model.KakaoSearch

data class KakaoSearchModel(
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
    fun toDataModel(): KakaoSearch {
        return KakaoSearch(
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
        operator fun invoke(kakaoSearch: KakaoSearch): KakaoSearchModel {
            return KakaoSearchModel(
                id = kakaoSearch.id,
                place_name = kakaoSearch.place_name,
                phone = kakaoSearch.phone,
                address_name = kakaoSearch.address_name,
                road_address_name = kakaoSearch.road_address_name,
                x = kakaoSearch.x,
                y = kakaoSearch.y,
                place_url = kakaoSearch.place_url,
                distance = kakaoSearch.distance,
            )
        }
    }
}
