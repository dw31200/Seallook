package com.seallook.androidx.data.repository

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.model.Profile
import kotlinx.coroutines.flow.Flow

interface GetProfileSnapshotRepository {
    fun getProfile(user: FirebaseUser?): Flow<Profile?>
}
