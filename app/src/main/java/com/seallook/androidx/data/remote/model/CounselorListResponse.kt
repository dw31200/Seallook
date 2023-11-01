package com.seallook.androidx.data.remote.model

import com.google.firebase.firestore.DocumentSnapshot

data class CounselorListResponse(
    val description: String,
    val name: String,
    val thumbnail: String,
) {
    constructor() : this("", "", "")

    companion object {
        operator fun invoke(snapshot: DocumentSnapshot): CounselorListResponse? {
            return snapshot.toObject(CounselorListResponse::class.java)
        }
    }
}
