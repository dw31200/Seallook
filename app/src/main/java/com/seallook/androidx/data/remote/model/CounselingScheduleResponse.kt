package com.seallook.androidx.data.remote.model

import com.google.firebase.firestore.DocumentSnapshot
import java.util.Date

data class CounselingScheduleResponse(
    val email: String,
    val id: Int,
    val date: Date,
    val typeId: Int,
) {
    constructor() : this("", 0, Date(), 0)

    companion object {
        operator fun invoke(snapshot: DocumentSnapshot): CounselingScheduleResponse? {
            return snapshot.toObject(CounselingScheduleResponse::class.java)
        }
    }
}
