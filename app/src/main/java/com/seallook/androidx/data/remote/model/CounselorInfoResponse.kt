package com.seallook.androidx.data.remote.model

import com.google.firebase.firestore.DocumentSnapshot

data class CounselorInfoResponse(
    val name: String,
    val description: String,
    val imageUrl: String,
) {
    companion object {
        operator fun invoke(snapshot: DocumentSnapshot): CounselorInfoResponse {
            return CounselorInfoResponse(
                name = snapshot.getString("name") ?: "",
                description = snapshot.getString("description") ?: "",
                imageUrl = snapshot.getString("imageUrl") ?: "",
            )
        }
    }
}
