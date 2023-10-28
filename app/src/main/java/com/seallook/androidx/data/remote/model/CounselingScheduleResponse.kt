package com.seallook.androidx.data.remote.model

import com.google.firebase.firestore.DocumentSnapshot
import com.seallook.androidx.base.RemoteModel
import java.util.Date

data class CounselingScheduleResponse(
    val id: Int,
    val date: Date,
    val typeId: Int,
) : RemoteModel {

    companion object {
        operator fun invoke(snapshot: DocumentSnapshot): CounselingScheduleResponse? {
            return snapshot.toObject(CounselingScheduleResponse::class.java)
        }
    }
}
