package com.seallook.androidx.data.remote.model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

@IgnoreExtraProperties
data class Profile(
    @get: Exclude
    val id: Int,
    val email: String,
    val name: String,
    val gender: Int,
    val birth: Date,
    @ServerTimestamp
    val timestamp: Date,
)