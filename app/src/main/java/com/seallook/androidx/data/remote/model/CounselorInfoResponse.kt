package com.seallook.androidx.data.remote.model

import com.google.firebase.firestore.DocumentSnapshot

data class CounselorInfoResponse(
    val counselorId: String,
    val name: String,
    val createdAt: Long,
    val description: String,
    val imageUrl: String,
) {
    companion object {
        operator fun invoke(snapshot: DocumentSnapshot): CounselorInfoResponse {
            return CounselorInfoResponse(
                counselorId = snapshot.id,
                name = snapshot.getString("name") ?: "",
                createdAt = snapshot.getLong("createdAt") ?: 0,
                description = snapshot.getString("description") ?: "",
                imageUrl = snapshot.getString("imageUrl") ?: "",
            )
        }
    }
}
