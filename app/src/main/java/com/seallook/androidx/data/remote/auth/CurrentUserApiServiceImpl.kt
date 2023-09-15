package com.seallook.androidx.data.remote.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class CurrentUserApiServiceImpl @Inject constructor(
    private val auth: FirebaseAuth,
) : CurrentUserApiService {
    override suspend fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }
}
