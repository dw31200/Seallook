package com.seallook.androidx.data.remote.model

import com.google.firebase.firestore.DocumentSnapshot

data class CounselorInfoResponse(
    val email: String,
    val name: String,
    val description: String,
    val thumbnail: String,
) {
    constructor() : this("", "", "", "")

    companion object {
        operator fun invoke(snapshot: DocumentSnapshot): CounselorInfoResponse? {
            return snapshot.toObject(CounselorInfoResponse::class.java)
        }
    }
}
