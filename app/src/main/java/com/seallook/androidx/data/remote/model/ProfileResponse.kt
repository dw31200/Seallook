package com.seallook.androidx.data.remote.model

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

@IgnoreExtraProperties
data class ProfileResponse(
    val id: Int,
    val email: String,
    val name: String?,
    val gender: Int?,
    val birth: Date?,
    @ServerTimestamp
    val timestamp: Date?,
    val userType: Int?,
) {
    constructor() : this(0, "", "", 0, Date(), Date(), 0)

    companion object {
        operator fun invoke(snapshot: DocumentSnapshot): ProfileResponse? {
            return snapshot.toObject(ProfileResponse::class.java)
        }
    }
}
