package com.seallook.androidx.data.remote.model

import com.google.firebase.firestore.DocumentSnapshot

data class CounselingTypeResponse(
    val id: Int,
    val title: String,
    val count: Int,
    val time: Int,
    val pay: Int,
) {
    companion object {
        operator fun invoke(snapshot: DocumentSnapshot): CounselingTypeResponse {
            return CounselingTypeResponse(
                id = snapshot.getLong("id")?.toInt() ?: 0,
                title = snapshot.getString("name") ?: "",
                count = snapshot.getLong("createdAt")?.toInt() ?: 0,
                time = snapshot.getLong("description")?.toInt() ?: 0,
                pay = snapshot.getLong("imageUrl")?.toInt() ?: 0,
            )
        }
    }
}
