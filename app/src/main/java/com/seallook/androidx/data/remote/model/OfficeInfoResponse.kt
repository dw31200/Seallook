package com.seallook.androidx.data.remote.model

import com.google.firebase.firestore.DocumentSnapshot
import com.seallook.androidx.base.RemoteModel

data class OfficeInfoResponse(
    val id: Int,
    val title: String,
    val link: String,
    val category: String,
    val description: String,
    val telephone: String,
    val address: String,
    val roadAddress: String,
    val mapx: Int,
    val mapy: Int,
) : RemoteModel {

    companion object {
        operator fun invoke(snapshot: DocumentSnapshot): OfficeInfoResponse? {
            return snapshot.toObject(OfficeInfoResponse::class.java)
        }

        operator fun invoke(id: Int, naverSearchResponse: NaverSearchResponse): OfficeInfoResponse {
            return OfficeInfoResponse(
                id = id,
                title = naverSearchResponse.title,
                link = naverSearchResponse.link,
                category = naverSearchResponse.category,
                description = naverSearchResponse.description,
                telephone = naverSearchResponse.telephone,
                address = naverSearchResponse.address,
                roadAddress = naverSearchResponse.roadAddress,
                mapx = naverSearchResponse.mapx,
                mapy = naverSearchResponse.mapy,
            )
        }
    }
}
