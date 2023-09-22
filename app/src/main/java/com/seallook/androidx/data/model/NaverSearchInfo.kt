package com.seallook.androidx.data.model

import com.seallook.androidx.data.remote.model.NaverSearchApiResponse

data class NaverSearchInfo(
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
        operator fun invoke(naverSearchApiResponse: NaverSearchApiResponse): NaverSearchInfo {
            return NaverSearchInfo(
                title = naverSearchApiResponse.title,
                link = naverSearchApiResponse.link,
                category = naverSearchApiResponse.category,
                description = naverSearchApiResponse.description,
                telephone = naverSearchApiResponse.telephone,
                address = naverSearchApiResponse.address,
                roadAddress = naverSearchApiResponse.roadAddress,
                mapx = naverSearchApiResponse.mapx,
                mapy = naverSearchApiResponse.mapy,
            )
        }
    }
}
