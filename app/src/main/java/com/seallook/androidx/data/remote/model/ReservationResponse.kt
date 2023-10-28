package com.seallook.androidx.data.remote.model

import com.google.firebase.firestore.DocumentSnapshot
import com.seallook.androidx.base.RemoteModel

data class ReservationResponse(
    val id: Int,
    val scheduleId: Int,
    val clientUid: String,
) : RemoteModel {

    companion object {
        operator fun invoke(snapshot: DocumentSnapshot): ReservationResponse? {
            return snapshot.toObject(ReservationResponse::class.java)
        }
    }
}
