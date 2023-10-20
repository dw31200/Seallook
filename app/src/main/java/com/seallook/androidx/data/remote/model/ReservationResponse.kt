package com.seallook.androidx.data.remote.model

import com.google.firebase.firestore.DocumentSnapshot

data class ReservationResponse(
    val id: Int,
    val scheduleId: Int,
    val clientUid: String,
) {
    constructor() : this(0, 0, "")

    companion object {
        operator fun invoke(snapshot: DocumentSnapshot): ReservationResponse? {
            return snapshot.toObject(ReservationResponse::class.java)
        }
    }
}
