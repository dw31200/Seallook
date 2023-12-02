package com.seallook.androidx.data.remote.model

import com.google.firebase.firestore.DocumentSnapshot
import java.util.Date

data class CounselingScheduleResponse(
    val email: String,
    val id: String,
    val date: Date,
    val typeId: String,
    val reservation: Boolean,
) {
    constructor() : this("", "", Date(), "", false)

    companion object {
        operator fun invoke(snapshot: DocumentSnapshot): CounselingScheduleResponse? {
            return snapshot.toObject(CounselingScheduleResponse::class.java)
        }
    }
}
