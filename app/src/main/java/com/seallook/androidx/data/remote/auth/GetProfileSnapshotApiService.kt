package com.seallook.androidx.data.remote.auth

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.remote.model.ProfileResponse
import kotlinx.coroutines.flow.Flow

interface GetProfileSnapshotApiService {
    fun getProfile(user: FirebaseUser?): Flow<ProfileResponse?>
}
