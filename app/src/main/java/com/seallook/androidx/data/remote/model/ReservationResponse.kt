package com.seallook.androidx.data.remote.model

import com.google.firebase.firestore.DocumentSnapshot

data class ReservationResponse(
    val id: String,
    val counselorEmail: String,
    val scheduleId: String,
    val clientEmail: String,
    val date: String,
    val confirm: Boolean,
) {
    constructor() : this("", "", "", "", "", false)

    companion object {
        operator fun invoke(snapshot: DocumentSnapshot): ReservationResponse? {
            return snapshot.toObject(ReservationResponse::class.java)
        }
    }
}
