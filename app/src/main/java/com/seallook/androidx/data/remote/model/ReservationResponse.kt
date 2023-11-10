package com.seallook.androidx.data.remote.model

import com.google.firebase.firestore.DocumentSnapshot

data class ReservationResponse(
    val id: Int,
    val counselorEmail: String,
    val scheduleId: Int,
    val clientEmail: String,
    val confirm: Boolean,
) {
    constructor() : this(-1, "", 0, "", false)

    companion object {
        operator fun invoke(snapshot: DocumentSnapshot): ReservationResponse? {
            return snapshot.toObject(ReservationResponse::class.java)
        }
    }
}
