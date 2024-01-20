package com.seallook.androidx.data.remote.model

import com.google.firebase.firestore.DocumentSnapshot

data class CounselorOfficeIdResponse(
    val email: String,
    val officeId: String,
) {
    constructor() : this("", "")

    companion object {
        operator fun invoke(snapshot: DocumentSnapshot): CounselorOfficeIdResponse? {
            return snapshot.toObject(CounselorOfficeIdResponse::class.java)
        }
    }
}
