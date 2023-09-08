package com.seallook.androidx.data.remote.model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

@IgnoreExtraProperties
data class ProfileResponse(
    @get: Exclude
    val key: String,
    val email: String,
    val name: String,
    val gender: Int,
    val birth: Date,
    @ServerTimestamp
    val timestamp: Date,
)
