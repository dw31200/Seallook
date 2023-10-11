package com.seallook.androidx.domain.model

import com.seallook.androidx.data.model.NaverSearchInfo

data class NaverSearchModel(
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
    fun toInfo(): NaverSearchInfo {
        return NaverSearchInfo(
            title = title,
            link = link,
            category = category,
            description = description,
            telephone = telephone,
            address = address,
            roadAddress = roadAddress,
            mapx = mapx,
            mapy = mapy,
        )
    }
    companion object {
        operator fun invoke(naverSearchInfo: NaverSearchInfo): NaverSearchModel {
            return NaverSearchModel(
                title = naverSearchInfo.title,
                link = naverSearchInfo.link,
                category = naverSearchInfo.category,
                description = naverSearchInfo.description,
                telephone = naverSearchInfo.telephone,
                address = naverSearchInfo.address,
                roadAddress = naverSearchInfo.roadAddress,
                mapx = naverSearchInfo.mapx,
                mapy = naverSearchInfo.mapy,
            )
        }
    }
}
