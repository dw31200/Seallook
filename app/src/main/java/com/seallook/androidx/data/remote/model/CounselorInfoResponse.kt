package com.seallook.androidx.data.remote.model

import com.google.firebase.firestore.DocumentSnapshot
import com.seallook.androidx.base.RemoteModel

data class CounselorInfoResponse(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
) : RemoteModel {

    companion object {
        operator fun invoke(snapshot: DocumentSnapshot): CounselorInfoResponse? {
            return snapshot.toObject(CounselorInfoResponse::class.java)
        }
    }
}
