package com.seallook.androidx.data.remote.model

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

data class OfficeCounselorEmailResponse(
    val id: String,
    val officeId: String,
    val counselorEmail: String,
) {
    constructor() : this("", "", "")

    companion object {
        operator fun invoke(snapshot: DocumentSnapshot): OfficeCounselorEmailResponse? {
            return snapshot.toObject(OfficeCounselorEmailResponse::class.java)
        }

        operator fun invoke(snapshot: QuerySnapshot): List<OfficeCounselorEmailResponse> {
            return snapshot.toObjects(OfficeCounselorEmailResponse::class.java)
        }
    }
}
