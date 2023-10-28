package com.seallook.androidx.data.remote.model

import com.google.firebase.firestore.DocumentSnapshot
import com.seallook.androidx.base.RemoteModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CounselingTypeResponse(
    val id: Int,
    val title: String,
    val clientCount: Int,
    val time: Int,
    val price: Int,
) : RemoteModel {

    companion object {
        operator fun invoke(snapshot: DocumentSnapshot): CounselingTypeResponse? {
            return snapshot.toObject(CounselingTypeResponse::class.java)
        }
    }
}
