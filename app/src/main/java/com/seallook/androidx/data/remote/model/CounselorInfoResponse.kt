package com.seallook.androidx.data.remote.model

import com.google.firebase.firestore.DocumentSnapshot

data class CounselorInfoResponse(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
) {
    constructor() : this(0, "", "", "")

    companion object {
        operator fun invoke(snapshot: DocumentSnapshot): CounselorInfoResponse? {
            return snapshot.toObject(CounselorInfoResponse::class.java)
        }
    }
}
