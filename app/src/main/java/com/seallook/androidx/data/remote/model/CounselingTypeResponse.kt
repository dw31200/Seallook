package com.seallook.androidx.data.remote.model

import com.google.firebase.firestore.DocumentSnapshot

data class CounselingTypeResponse(
    val id: Int,
    val title: String,
    val clientCount: Int,
    val time: Int,
    val price: Int,
) {
    constructor() : this(0, "", 0, 0, 0)
    companion object {
        operator fun invoke(snapshot: DocumentSnapshot): CounselingTypeResponse? {
            return snapshot.toObject(CounselingTypeResponse::class.java)
        }
    }
}
