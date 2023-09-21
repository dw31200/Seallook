package com.seallook.androidx.data.remote.model

import com.google.firebase.firestore.DocumentSnapshot

data class UserTypeResponse(
    val usertype: Int,
) {
    companion object {
        operator fun invoke(snapshot: DocumentSnapshot): UserTypeResponse {
            return UserTypeResponse(
                usertype = snapshot.getLong("usertype")?.toInt() ?: 0,
            )
        }
    }
}
