package com.seallook.androidx.data.remote.auth

import com.google.firebase.auth.FirebaseUser

interface CurrentUserApiService {
    suspend fun getCurrentUser(): FirebaseUser?
}
